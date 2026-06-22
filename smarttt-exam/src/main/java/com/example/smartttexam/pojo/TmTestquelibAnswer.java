package com.example.smartttexam.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TmTestquelibAnswer {

    private String id;
    private String libId;
    private boolean isAnswer;
    private String itemPicture;
    private String itemOption;
    private String itemContent;
    private String itemAnalysis;
    private long sortVal;
}
