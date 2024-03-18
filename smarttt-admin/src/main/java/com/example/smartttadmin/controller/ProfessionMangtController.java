package com.example.smartttadmin.controller;

import com.example.smartttadmin.dto.LoginHomeReq;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.CmProfession;
import com.example.smartttadmin.pojo.SmObs;
import com.example.smartttadmin.service.SmObsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.smartttadmin.pojo.CommonFunctions.generateEnhancedID;

@RestController
@RequestMapping("/sysmangt/professionmangt")
public class ProfessionMangtController {
    @Autowired
    private SmObsService smObsService;
    @PostMapping("")
    public Result getProfessionList(@RequestBody LoginHomeReq loginHomeReq){
        return smObsService.getAllProfessionList(loginHomeReq);
    }

    /**
     * 写死，挂在计算机系，需要修改为token获取
     * @param cmProfession
     * @return
     */
    @PostMapping("/create")
    public Result createOneProfession(@RequestBody CmProfession cmProfession){

        String ID = generateEnhancedID("sm_obs");
        SmObs smObs = new SmObs(ID,"4355323",4,cmProfession.getProname(),cmProfession.getRemark());
        Result result = smObsService.createOneObs(smObs);
        if(result.getCode() != 200)return result;
        cmProfession.setObsid(ID);
        return smObsService.createOneProfession(cmProfession);
    }
    @PostMapping("/delete")
    public Result deleteProfessionByIDs(@RequestBody List<String> ids){
        return smObsService.deleteObssByIDS(ids);
    }

}
