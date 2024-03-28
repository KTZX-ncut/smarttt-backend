package com.example.smartttadmin.service.impl;

import com.example.smartttadmin.dto.*;
import com.example.smartttadmin.mapper.StUsersMapper;
import com.example.smartttadmin.pojo.StUsers;
import com.example.smartttadmin.service.StUsersService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static com.example.smartttadmin.Utils.CommonFunctions.TokenSK;
import static com.example.smartttadmin.Utils.JwtTokenUtils.getToken;

@Service
public class StUsersServiceImpl implements StUsersService {
    @Autowired
    private StUsersMapper stUsersMapper;
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

            List<PersonnelRoster> personnelRosterList = new ArrayList<>();
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

                PersonnelRoster personnelRoster = new PersonnelRoster(null,null,username,loginname,pwd,phone,status,catelog,obsname,remark,null,personnelno);

                personnelRosterList.add(personnelRoster);

            }
            return personnelRosterList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Result getObsRP(String obsid) {
        //获取该obsid的全部教师
        stUsersMapper.getTeachersByobsid(obsid);
        return null;
    }

    @Override
    public Result getUserInfor(TeaInforReq teaInforReq) {
        Token token = new Token(teaInforReq.getId(), teaInforReq.getRoleid(), teaInforReq.getObsid(), teaInforReq.getObsdeep());
        TeaUser teaUser = stUsersMapper.getAllUserInfor(teaInforReq);
        try{
                teaUser.setCatelog(teaInforReq.getCatelog());
        }catch (NullPointerException e){
            return Result.error("发生什么事情了");
        }

        teaUser.setToken(getToken(token,TokenSK));
       return Result.success(teaUser);
    }
}
