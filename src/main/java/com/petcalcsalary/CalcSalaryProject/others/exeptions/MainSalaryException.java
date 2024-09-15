package com.petcalcsalary.CalcSalaryProject.others.exeptions;

/**
 * Родительский экспешен, созданный для удобства обработки всех кастомных эксепшенов в хендлере-контроллере
 */
public class MainSalaryException extends Exception {

    public MainSalaryException(String message){
        super(message);
    }

    public MainSalaryException(String message, Throwable cause) {
        super(message, cause);
    }

    public MainSalaryException(Throwable cause) {
        super(cause);
    }

}
