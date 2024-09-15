package com.petcalcsalary.CalcSalaryProject.others.exeptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * Контроллер, обрабатывающий кастомные эксепшены, которые могут возникнуть в процессе работы приложения
 */
@ControllerAdvice
@Slf4j
public class HandlerExceptions {

    @ExceptionHandler(MainSalaryException.class)
    public ResponseEntity<String> handlerCustomExceptions(MainSalaryException mainException){
        log.error(mainException.getMessage(), mainException);
        return ResponseEntity.internalServerError().body(mainException.getCause().toString());
    }

}