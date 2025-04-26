package com.example.smartttevaluation.dto;

import lombok.Data;

import javax.annotation.Resource;
import java.util.List;


@Data
public class KwaBindReq {
    private String KwaId;
    List<KwaValueTagBindReq> kwaValueTagBindReqList;
}
