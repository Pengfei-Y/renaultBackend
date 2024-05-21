package com.rebims.renault.exception;

import com.rebims.renault.utils.Result;
import com.rebims.renault.utils.ResultUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RenaultExceptionHandler {
    @ExceptionHandler(RenaultException.class)
    public Result handlerException(RenaultException e) {
        return ResultUtil.error("500", e.getMessage(), null);
    }
}
