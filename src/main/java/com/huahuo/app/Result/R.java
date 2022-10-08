package com.huahuo.app.Result;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.huahuo.app.constant.StatusCode.ALL_OK;
import static com.huahuo.app.constant.StatusCode.DEFAULT_ERROR_CODE;

/**
 * 通用返回结果，服务端响应的数据最终都会封装成此对象
 * @param <T>
 */
@Data
public class R<T> implements Serializable {

    private String code; //编码：200成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据
    private List<T> list;
    private Map map = new HashMap(); //动态数据

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = ALL_OK;
        return r;
    }
    public static <T> R<T> success(List<T> list) {
        R<T> r = new R<T>();
        r.code = ALL_OK;
        r.list=list;
        return r;
    }

    public static <T> R<T> success(T object,String msg) {
        R<T> r = new R<T>();
        r.msg=msg;
        r.data = object;
        r.code = ALL_OK;
        return r;
    }
    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = DEFAULT_ERROR_CODE;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}