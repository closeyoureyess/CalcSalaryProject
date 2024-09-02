package com.petcalcsalary.CalcSalaryProject.others.exeptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class HandlerExceptions {

    @ExceptionHandler(MainSalaryExсeption.class)
    public ResponseEntity<String> handlerCustomExceptions(MainSalaryExсeption mainException){
        log.error(mainException.getMessage(), mainException);
        return ResponseEntity.internalServerError().body(mainException.getCause().toString());
    }

}