package com.example.smartttcourse.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmKnowledgeUnitKwa {
    String id;
    String unitid;
    String kwaid;
    String name;
    String keywordName;
    String abilityName;
    int status;
}
