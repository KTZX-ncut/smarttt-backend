package com.example.smartttadmin.controller;

import com.example.smartttadmin.Utils.AuthRequired;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.Token;
import com.example.smartttadmin.pojo.CmClass;
import com.example.smartttadmin.pojo.SmObs;
import com.example.smartttadmin.service.SmObsService;
import com.example.smartttadmin.service.StLevelService;
import com.example.smartttadmin.service.StUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.smartttadmin.Utils.AuthorizationAspect.getTokenFromContext;
import static com.example.smartttadmin.Utils.CommonFunctions.generateEnhancedID;

@RestController
@RequestMapping("/sysmangt/classmangt")
public class ClassMangtController {
    @Autowired
    SmObsService smObsService;
    @Autowired
    StUsersService stUsersService;
    @Autowired
    StLevelService stLevelService;

    /**
     *
     * @param request
     * @return
     */
    @GetMapping("")
    @AuthRequired(type = "admin",menu = "531500340-63929fcc-e8f9-11ee-934c-fa163efa1f90",isReadOnly = true)
    public Result getClassList(HttpServletRequest request){
        Token token = getTokenFromContext();
        return smObsService.getClassList(token.getObsid());
    }

    /**
     * 传入的id为专业id
     * @param cmClass
     * @return
     */
    @PostMapping("/create")
    @AuthRequired(type = "admin",menu = "531500340-63929fcc-e8f9-11ee-934c-fa163efa1f90")
    public Result createOneClass(@RequestBody CmClass cmClass,HttpServletRequest request){
        Token token = getTokenFromContext();
        String ID = generateEnhancedID("sm_obs");
        long deep = stLevelService.getObsLevel("105");
        SmObs smObs = new SmObs(ID, cmClass.getId(), deep,cmClass.getClassname(),cmClass.getRemark(),token.getTermid());
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

    @GetMapping("/student")
    public Result getStudentList(@RequestParam(name = "id")String id){//班级的Id
        return stUsersService.getStudentByClassID(id);
    }

}
