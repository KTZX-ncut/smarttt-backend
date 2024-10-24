package com.example.smartttadmin.converts;

import com.example.smartttadmin.dto.PersonnelRoster;
import com.example.smartttadmin.pojo.PersonnelExcel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lunSir
 * @create 2024-10-18 14:07
 */
@Mapper
public interface ExcelConvert {
    ExcelConvert INSTANCE = Mappers.getMapper(ExcelConvert.class);
    PersonnelRoster personnelExcelToPersonnelRoster(PersonnelExcel personnelExcel);
}
