package com.example.smartttcourse.exception.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一响应格式
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Result", description = "课程模块统一响应对象")
public class Result {
    @ApiModelProperty(value = "业务状态码，200 表示成功，其他值表示失败或异常", example = "200")
    private Integer code;//响应码，1 代表成功; 0 代表失败
    @ApiModelProperty(value = "响应消息", example = "success")
    private String msg;  //响应信息 描述字符串
    @ApiModelProperty(value = "业务数据体，结构随接口不同而变化")
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
