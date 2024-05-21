package com.rebims.renault.utils;


/**
 * @Author hugx
 * @Date 2022-10-23 8:57
 */
public class ResultUtil {

    public static<T> Result success(String code, String msg, T data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> Result success(T data) {
        Result result = success("200", "成功", data);
        return result;
    }
    public static<T> Result error(String code, String msg, T data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
