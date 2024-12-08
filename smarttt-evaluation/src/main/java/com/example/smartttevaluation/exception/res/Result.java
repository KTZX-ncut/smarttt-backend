package com.example.smartttevaluation.exception.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一响应格式
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;//响应码，1 代表成功; 0 代表失败
    private String msg;  //响应信息 描述字符串
    private Object data; //返回的数据

    //增删改 成功响应
    public static Result success(){
        return new Result(200,"success",null);
    }
    //查询 成功响应
    public static Result success(Object data){
        return new Result(200,"success",data);
    }
    //失败响应
    public static Result error(String msg){
        return new Result(0,msg,null);
    }
    public static Result error(Integer code,String msg){
        return new Result(code,msg,null);
    }
    public static Result ok(){
        Result r = new Result();
        r.setCode(ResponseEnum.SUCCESS.getCode());
        r.setMsg(ResponseEnum.SUCCESS.getMessage());
        return r;
    }

    public static Result error(){
        Result r = new Result();
        r.setCode(ResponseEnum.ERROR.getCode());
        r.setMsg(ResponseEnum.ERROR.getMessage());
        return r;
    }

    // 定制化响应
    public Result code(Integer code){
        this.setCode(code);
        return this;
    }
    public Result msg(String msg){
        this.setMsg(msg);
        return this;
    }
    public Result data(Object o){
        this.setData(o);
        return this;
    }

    public static Result setResult(ResponseEnum result){
        Result r = new Result();
        r.setCode(result.getCode());
        r.setMsg(result.getMessage());
        return r;
    }
}