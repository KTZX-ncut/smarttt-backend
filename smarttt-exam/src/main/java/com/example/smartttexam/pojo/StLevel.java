package com.example.smartttexam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StLevel {

  private long id;
  private String levelname;
  private long obsdeep;
  private String teacher;
  private String student;

}
