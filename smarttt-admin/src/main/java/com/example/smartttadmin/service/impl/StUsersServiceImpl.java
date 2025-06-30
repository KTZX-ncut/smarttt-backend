package com.example.smartttadmin.service.impl;

import com.example.smartttadmin.dto.*;
import com.example.smartttadmin.mapper.SmObsMapper;
import com.example.smartttadmin.mapper.StUsersMapper;
import com.example.smartttadmin.pojo.StRoleUser;
import com.example.smartttadmin.pojo.StUsers;
import com.example.smartttadmin.service.StUsersService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static com.example.smartttadmin.Utils.CommonFunctions.*;
import static com.example.smartttadmin.Utils.JacksonJsonUtil.jsonArrayToList;
import static com.example.smartttadmin.Utils.JacksonJsonUtil.listToJsonArray;
import static com.example.smartttadmin.Utils.JwtTokenUtils.getToken;

@Service
public class StUsersServiceImpl implements StUsersService {
    @Autowired
    private StUsersMapper stUsersMapper;
    @Autowired
    private SmObsMapper smObsMapper;

    @Override
    public Result createOneRP(StRoleUser stRoleUser) {
        try {
            stRoleUser.setId(generateEnhancedID("st_roleuser"));
            stRoleUser.setObsdeep(smObsMapper.getObsdeepByObsid(stRoleUser.getObsid()));
            stRoleUser.setCreatetime(LocalDate.now().toString());
            String currentTerm = stUsersMapper.getCurrentTerm();
            stRoleUser.setTermid(currentTerm);
            stUsersMapper.createOneRoleUser(stRoleUser);
        }catch (NullPointerException e)
        {
            return Result.error(404,"新建失败");
        }

        return Result.success();
    }

    public Result login(LoginReq loginReq) {
        StUsers stUsers = stUsersMapper.getStUsersByLoginNameAndPwdAndCatelog(loginReq);
        if(stUsers == null){
            return Result.error(400,"用户名或密码错误");
        }
        return Result.success(stUsers);
    }

    @Override
    public Result deleteUsersByIDs(List<String> ids) {
        stUsersMapper.deleteUsersByIDs(ids);
        return Result.success();
    }

    @Override
    public List<PersonnelRoster> importTeacherAndStudentExcel(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // 假设 Excel 文件中只有一个工作表
            DataFormatter dataFormatter = new DataFormatter();
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // 跳过标题行

            List<com.example.smartttadmin.dto.PersonnelRoster> personnelRosterList = new ArrayList<>();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String username = dataFormatter.formatCellValue(row.getCell(1));
                String loginname = dataFormatter.formatCellValue(row.getCell(2));
                String pwd = dataFormatter.formatCellValue(row.getCell(3));
                String personnelno = dataFormatter.formatCellValue(row.getCell(4));
                String phone = dataFormatter.formatCellValue(row.getCell(5));
                String catelog = dataFormatter.formatCellValue(row.getCell(6));
                String status = dataFormatter.formatCellValue(row.getCell(7));
                String obsname = dataFormatter.formatCellValue(row.getCell(8));
                String remark = dataFormatter.formatCellValue(row.getCell(9));
                if(Objects.equals(catelog, "教师")) catelog ="2";
                else catelog = "1";

                com.example.smartttadmin.dto.PersonnelRoster personnelRoster = new com.example.smartttadmin.dto.PersonnelRoster(null,null,username,loginname,pwd,phone,status,catelog,obsname,remark,null,personnelno);

                personnelRosterList.add(personnelRoster);

            }
            return personnelRosterList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 这里应该对用户传进来的信息进行校验真实性（修改
     * @param userInforReq
     * @return
     */
    @Override
    public Result getTeaInfor(UserInforReq userInforReq) {
        if(userInforReq.getTermid() == null)
            userInforReq.setTermid(stUsersMapper.getCurrentTerm());
        Token token = new Token(userInforReq.getId(), userInforReq.getRoleid(),
                userInforReq.getObsid(), userInforReq.getObsdeep(),userInforReq.getTermid());
        UserInfor userInfor = stUsersMapper.getAllUserInfor(userInforReq);
        try{
                userInfor.setCatelog(userInforReq.getCatelog());
        }catch (NullPointerException e){
            return Result.error("用户信息错误");
        }
        userInfor.setToken(getToken(token,TokenSK));
       return Result.success(userInfor);
    }

    @Override
    public Result deleteRP(StRoleUser stRoleUser) {
        stUsersMapper.deletePRByObsIDAndUserID(stRoleUser);
        return Result.success();
    }

    @Override
    public Result updateOnePersonnelRoster(PersonnelRoster personnelRoster,String termid) throws JsonProcessingException {
        if(personnelRoster.getObsname()!=null){
            List<String> obsIDList = smObsMapper.getObsIDByObsName(personnelRoster.getObsname(),termid);
            if(obsIDList.isEmpty())
                return Result.error("所属院系/班级输入错误");
            personnelRoster.setObsid(obsIDList.get(0));
        }
        String loginName = personnelRoster.getLoginname();
        if(loginName != null) {
            List<String> stUsersList = stUsersMapper.getStUsersByloginName(loginName);
            if (!stUsersList.isEmpty()) return Result.error("登录名已存在");
        }

        stUsersMapper.updateUserByID(personnelRoster);
        if(Objects.equals(personnelRoster.getCatelog(), "1")){//学生
            stUsersMapper.updateStudentByID(changePersonHistoryObs(personnelRoster));
        }else{//老师
            stUsersMapper.updateTeacherByID(changePersonHistoryObs(personnelRoster));
        }

        return Result.success();
    }
    private  PersonnelRoster changePersonHistoryObs(PersonnelRoster personnelRoster) throws JsonProcessingException {
        String currentTermId = stUsersMapper.getCurrentTerm();
        if(personnelRoster.getObsid()!=null){

            String historyObsJson;
            if(Objects.equals(personnelRoster.getCatelog(), "1")){
                historyObsJson = smObsMapper.getStuHistoryObsByUserId(personnelRoster.getId());
            }
            else{
                historyObsJson = smObsMapper.getTeaHistoryObsByUserId(personnelRoster.getId());
            }

            List<HistoryObs> historyObsList = jsonArrayToList(historyObsJson, HistoryObs.class);
            Boolean isFind = false;
            for(HistoryObs historyObs : historyObsList){
                if(Objects.equals(historyObs.getTermId(), currentTermId)){
                    System.out.println(personnelRoster.getObsid()+"????????????");
                    historyObs.setObsId(personnelRoster.getObsid());
                    isFind = true;
                    break;
                }
            }
            if(!isFind){
                HistoryObs historyObs = new HistoryObs(currentTermId,personnelRoster.getObsid());
                historyObsList.add(historyObs);
            }
            historyObsJson = listToJsonArray(historyObsList);
            personnelRoster.setRemark(historyObsJson);
        }
        return personnelRoster;
    }
    @Override
    public Result getStudentByClassID(String id) {
        return Result.success(stUsersMapper.getStudentByID(id));
    }

    @Override
    public Result getStudentInfor(StUsers stUsers) {
        UserInfor userInfor = stUsersMapper.getStudentInfor(StuRoleID);
        userInfor.setCatelog("1");
        userInfor.setUsername(stUsers.getUsername());
        Token token = new Token(stUsers.getId(),StuRoleID,null,-1,stUsersMapper.getCurrentTerm());
        userInfor.setToken(getToken(token,TokenSK));
        return Result.success(userInfor);
    }
    @Transactional
    @Override
    public Result testTran()  {
        String s = "test2";
        stUsersMapper.testTran(s);
        return Result.error("我就是要错误");
    }

    @Override
    public List<String> getStUsersByloginName(String loginname) {
        return stUsersMapper.getStUsersByloginName(loginname);
    }

    @Override
    public void saveBach(List<PersonnelRoster> personnelRosterList) {
        stUsersMapper.saveBach(personnelRosterList);
    }

    @Override
    public Result teacherChangePwd(String currenPwd, String newPwd, String id) {
        String pwd = stUsersMapper.getPwd(id);
        if (!Objects.equals(pwd, currenPwd)) return Result.error("当前密码不正确");
        if(Objects.equals(pwd, newPwd)) return Result.error("新旧密码一致");

        stUsersMapper.updatePwd(id, newPwd);
        return Result.success();
    }

    @Override
    public Result studentChangePwd(String currenPwd, String newPwd, String id) {
        String pwd = stUsersMapper.getPwd(id);
        if (!Objects.equals(pwd, currenPwd)) return Result.error("当前密码不正确");
        if(Objects.equals(pwd, newPwd)) return Result.error("新旧密码一致");

        stUsersMapper.updatePwd(id, newPwd);
        return Result.success();
    }

    @Override
    public Result searchPerson(String inform, String catelog) {
        List<PersonnelRoster> personnelRosterList = new ArrayList<>();
        if(Objects.equals(catelog, "1")){//学生
            personnelRosterList = stUsersMapper.getStudentByInform(inform);
        }
        else{
            personnelRosterList = stUsersMapper.getTeacherByInform(inform);
        }
        return Result.success(personnelRosterList);
    }
}
