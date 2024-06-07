package com.rebims.renault.utils;



public class ResultUtil {

    public static<T> Result success(String code, String msg, T data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> Result success(T data) {
        Result result = success("200", "success", data);
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
