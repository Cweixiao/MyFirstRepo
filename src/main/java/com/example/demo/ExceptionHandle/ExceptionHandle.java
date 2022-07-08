package com.example.demo.ExceptionHandle;


import com.example.demo.common.ApiResponse.AjaxResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandle {


    //Get an @valid exception
    @ExceptionHandler
    @ResponseBody
    public AjaxResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        return handleBindingResult(e.getBindingResult());
    }

    private AjaxResult handleBindingResult(BindingResult result){
        //把异常处理为对外暴露的提示
        List<String> list = new ArrayList<>();
        if(result.hasErrors()){
            List<ObjectError> allErrors = result.getAllErrors();
            for(ObjectError objectError : allErrors){
                String defaultMessage = objectError.getDefaultMessage();
                list.add(defaultMessage);
            }
        }
        if(list.size() == 0){
            return AjaxResult.error("参数异常");
        }
        return AjaxResult.error(list.toString());
    }
}
