package com.example.smartttadmin.controller;


import com.example.smartttadmin.service.impl.StUnitServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/sysmangt/units")
public class UnitMangtController {

    @Autowired
    private StUnitServicelmpl stUnitService;

}

