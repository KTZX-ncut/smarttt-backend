package com.example.smartttadmin.controller;

import com.example.smartttadmin.Utils.AuthRequired;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.Token;
import com.example.smartttadmin.pojo.SmObs;
import com.example.smartttadmin.pojo.StRoleUser;
import com.example.smartttadmin.service.SmObsService;
import com.example.smartttadmin.service.StUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.smartttadmin.Utils.AuthorizationAspect.getTokenFromContext;

@RestController
@RequestMapping("/sysmangt/collegemangt")
public class CollageMangtController {
    @Autowired
    private SmObsService smObsService;
    @Autowired
    private StUsersService stUsersService;
    @GetMapping
    public Result getCollegeList(){
        return smObsService.getAllCollageList();
    }

    /**
     * 新建一个学院，先调用新建机构方法
     * @param smObs 学院
     * @return ...
     * 后续需要把每个字段都补全（修改）
     */
    @PostMapping
    public Result createNewCollege(@RequestBody SmObs smObs){
        smObs.setObsdeep(2);
        smObs.setPid("237675254");
        return smObsService.createOneObs(smObs);
    }

    /**
     * 删除学院
     * @param ids id列表
     * @return Result
     */
    @PostMapping("/delete")
    public Result deleteCollegeByIDs(@RequestBody List<String> ids){
        return smObsService.deleteObssByIDS(ids);
    }
    @PostMapping("/update")
    public Result updateCollege(@RequestBody SmObs smObs) {
        return smObsService.updateOneObsByID(smObs);
    }

    @GetMapping("/collageRP")
    @AuthRequired(type = "admin",menu = "531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3",isReadOnly = true)
    public Result CollegeRPList(HttpServletRequest request){
        //低于系（当前配置的教师级别）,就回溯到有教师的级别,然后显示级别的所有数据
        Token token = getTokenFromContext();
        return smObsService.getObsRPList(token.getObsid());
    }

    /**
     * 删除负责人,角色写死，需修改
     * @param stRoleUser ...
     * @return ...
     */
    @PostMapping ("/collageRP/delete")
    public Result deleteCollageRP(@RequestBody StRoleUser stRoleUser){
        stRoleUser.setRoleid("516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4");
        return stUsersService.deleteRP(stRoleUser);
    }
    @PostMapping("/collageRP/create")
    public Result createCollageRP(@RequestBody StRoleUser stRoleUser){
        stRoleUser.setRoleid("516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4");
        return stUsersService.createOneRP(stRoleUser);
    }

}
