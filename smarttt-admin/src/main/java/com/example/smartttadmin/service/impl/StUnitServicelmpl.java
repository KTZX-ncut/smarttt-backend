package com.example.smartttadmin.service.impl;

import com.example.smartttadmin.dto.MenuTree;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.UnitTree;
import com.example.smartttadmin.mapper.StUnitMapper;
import com.example.smartttadmin.service.StUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
@Controller
public class StUnitServicelmpl implements StUnitService {
    @Override
    public Result getUnitTree(String rolecode) {
        return null;
    }
}

