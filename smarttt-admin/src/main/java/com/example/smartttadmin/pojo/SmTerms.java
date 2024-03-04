package com.example.smartttadmin.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;

/**
 * 学期管理信息列表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SmTerms {
    private String id;//学期的id
    private String termName;//学期名称
    private LocalDate startDate;//开始时间
    private LocalDate endDate;//结束时间
    private String remark;//备注
    private boolean isActive;//是否是当前学期
}