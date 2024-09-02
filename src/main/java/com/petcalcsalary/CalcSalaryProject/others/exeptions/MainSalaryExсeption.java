package com.petcalcsalary.CalcSalaryProject.others.exeptions;

public class MainSalaryExсeption extends Exception {

    public MainSalaryExсeption(String message){
        super(message);
    }

    public MainSalaryExсeption(String message, Throwable cause) {
        super(message, cause);
    }

    public MainSalaryExсeption(Throwable cause) {
        super(cause);
    }

}
