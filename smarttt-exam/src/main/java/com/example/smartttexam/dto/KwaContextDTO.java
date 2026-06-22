package com.example.smartttexam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 单个KWA上下文（关键字+能力组合），用于传递给AI出题
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KwaContextDTO {

    private String keywordId;
    private String keywordName;
    private String abilityId;
    private String abilityName;
    private String kwaId;
    private String kwaName;
}
