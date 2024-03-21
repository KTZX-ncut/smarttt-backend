package com.example.smartttadmin.controller;

import com.example.smartttadmin.dto.LoginHomeReq;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.CmClass;
import com.example.smartttadmin.pojo.SmObs;
import com.example.smartttadmin.service.SmObsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.smartttadmin.Utils.CommonFunctions.generateEnhancedID;

@RestController
@RequestMapping("/sysmangt/classmangt")
public class ClassMangtController {
    @Autowired
    SmObsService smObsService;
    @PostMapping("")
    public Result getClassList(@RequestBody LoginHomeReq loginHomeReq){
        return smObsService.getClassList(loginHomeReq);
    }

    /**
     * 传入的id为专业id
     * @param cmClass
     * @return
     */
    @PostMapping("/create")
    public Result createOneClass(@RequestBody CmClass cmClass){
        String ID = generateEnhancedID("sm_obs");
        SmObs smObs = new SmObs(ID, cmClass.getId(), 5,cmClass.getClassname(),cmClass.getRemark());
        Result result = smObsService.createOneObs(smObs);
        if(result.getCode()!=200)return result;
        cmClass.setObsid(ID);
        return smObsService.createOneClass(cmClass);
    }
    @PostMapping("/delete")
    public Result deleteClassesByIDs(@RequestBody List<String> ids){
        return smObsService.deleteObssByIDS(ids);
    }
    @PostMapping("/update")
    public Result updateClass(@RequestBody CmClass cmClass){
        return smObsService.updateClass(cmClass);
    }

}
