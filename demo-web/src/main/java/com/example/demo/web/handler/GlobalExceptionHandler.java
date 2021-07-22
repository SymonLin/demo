package com.example.demo.web.handler;

import com.example.demo.common.entity.Result;
import com.example.demo.common.error.DemoErrors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @author linjian
 * @date 2021/3/17
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<Boolean> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error(e.getMessage(), e);
        return Result.wrapErrorResult(DemoErrors.PARAM_NULL);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<Boolean> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error(e.getMessage(), e);
        return Result.wrapErrorResult(DemoErrors.PARAM_MISSING);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Boolean> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return Result.wrapErrorResult(DemoErrors.PARAM_ERROR.getCode(),
                Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public Result<Boolean> handleOtherException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.wrapErrorResult(DemoErrors.SYSTEM_ERROR);
    }
}
