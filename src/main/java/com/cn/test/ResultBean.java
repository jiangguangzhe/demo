package com.cn.test;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/3/8.
 */
@Data
@Builder
public class ResultBean<T> implements Serializable{
    /**
     * 返回的数据
     */
    private T data;

    /**
     * 0:成功 1：失败
     */
    private Integer code;

    private String msg;

    public static <W> ResultBean<W> success(String msg,W data){
        return build(0,msg,data);
    }
    public static ResultBean<String> success(String msg){
        return success(msg,"");
    }

    public static <W> ResultBean<W> error(String msg,W data){
        return build(1,msg,data);
    }

    public static ResultBean<String> error(String msg){
        return error(msg,"");
    }

    public static <W> ResultBean<W> excute(int code,String msg,W data){
        return build(code,msg,data);
    }

    private static <W> ResultBean<W> build(int code ,String msg,W data){
        return (ResultBean<W>) ResultBean.build().code(code).msg(msg).data(data).build();
    }

}
