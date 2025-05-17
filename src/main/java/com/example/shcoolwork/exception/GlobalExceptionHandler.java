package com.example.shcoolwork.exception;


import com.example.shcoolwork.Entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public Result<Object> handlerException(Exception ex){
        ex.printStackTrace();
        return Result.error("操作失败");
    }
}
