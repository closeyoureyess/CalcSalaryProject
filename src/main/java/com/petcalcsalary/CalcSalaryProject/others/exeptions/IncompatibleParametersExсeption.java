package com.petcalcsalary.CalcSalaryProject.others.exeptions;

public class IncompatibleParametersExсeption extends MainSalaryExсeption {

    public IncompatibleParametersExсeption(String message){
        super(message);
    }

    public IncompatibleParametersExсeption(String message, Throwable cause) {
        super(message, cause);
    }

    public IncompatibleParametersExсeption(Throwable cause) {
        super(cause);
    }

}
