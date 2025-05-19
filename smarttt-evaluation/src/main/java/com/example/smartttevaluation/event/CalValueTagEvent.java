package com.example.smartttevaluation.event;

import com.example.smartttevaluation.dto.KwaBindReq;
import org.springframework.context.ApplicationEvent;

import java.util.List;


public class CalValueTagEvent extends ApplicationEvent {

    private final KwaBindReq kwaBindReq;
    private final String vTagListJson;

    public CalValueTagEvent(Object source, KwaBindReq kwaBindReq,String vTagListJson) {
        super(source);
        this.kwaBindReq = kwaBindReq;
        this.vTagListJson = vTagListJson;
    }

    public String getvTagListJson() {
        return vTagListJson;
    }

    public KwaBindReq getKwaBindReq() {
        return kwaBindReq;
    }
}
