package com.example.smartttadmin.controller;

import com.example.smartttadmin.Utils.AuthRequired;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.Token;
import com.example.smartttadmin.pojo.CmProfession;
import com.example.smartttadmin.pojo.SmObs;
import com.example.smartttadmin.pojo.StRoleUser;
import com.example.smartttadmin.service.SmObsService;
import com.example.smartttadmin.service.StUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.smartttadmin.Utils.AuthorizationAspect.getTokenFromContext;
import static com.example.smartttadmin.Utils.CommonFunctions.generateEnhancedID;

@RestController
@RequestMapping("/sysmangt/professionmangt")
public class ProfessionMangtController {
    @Autowired
    private SmObsService smObsService;
    @Autowired
    private StUsersService stUsersService;
    @GetMapping("")
    @AuthRequired(type = "admin",menu = "531500340-910116aa-e8f8-11ee-934c-fa163efa1f90",isReadOnly = true)
    public Result getProfessionList(HttpServletRequest request){
        Token token = getTokenFromContext();
        return smObsService.getAllProfessionList(token.getObsid());
    }

    /**
     * @param cmProfession
     * @return
     */
    @PostMapping("/create")
    @AuthRequired(type = "admin", menu = "531500340-910116aa-e8f8-11ee-934c-fa163efa1f90")
    public Result createOneProfession(@RequestBody CmProfession cmProfession, HttpServletRequest request){
        String ID = generateEnhancedID("sm_obs");
        Token token = getTokenFromContext();
        SmObs smObs = new SmObs(ID, token.getObsid(), token.getObsdeep()+1,cmProfession.getProname(),cmProfession.getRemark());
        Result result = smObsService.createOneObs(smObs);
        if(result.getCode() != 200)return result;
        cmProfession.setObsid(ID);
        return smObsService.createOneProfession(cmProfession);
    }
    @PostMapping("/delete")
    public Result deleteProfessionByIDs(@RequestBody List<String> ids){
        return smObsService.deleteObssByIDS(ids);
    }

    @PostMapping("/update")
    public Result updateProfession(@RequestBody CmProfession cmProfession){
        return smObsService.updateOneProfession(cmProfession);
    }

    @GetMapping("/professionRP")
    @AuthRequired(type = "admin",menu = "531500340-910116aa-e8f8-11ee-934c-fa163efa1f90",isReadOnly = true)
    public Result CollegeRPList( HttpServletRequest request){
        //低于系（当前配置的教师级别）,就回溯到有教师的级别,然后显示级别的所有数据
        String obsID = smObsService.getSchoolObs();
        Token token = getTokenFromContext();
        return smObsService.getObsRPList(obsID);
    }
    @PostMapping ("/professionRP/delete")
    public Result deleteCollageRP(@RequestBody StRoleUser stRoleUser){
        stRoleUser.setRoleid("516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60");
        return stUsersService.deleteRP(stRoleUser);
    }
    @PostMapping("/professionRP/create")
    @AuthRequired(type = "admin",menu = "531500340-910116aa-e8f8-11ee-934c-fa163efa1f90")
    public Result createCollageRP(@RequestBody StRoleUser stRoleUser,HttpServletRequest request){
        Token token = getTokenFromContext();
        stRoleUser.setRoleid("516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60");
        stRoleUser.setTermid(token.getTermid());
        return stUsersService.createOneRP(stRoleUser);
    }
}
