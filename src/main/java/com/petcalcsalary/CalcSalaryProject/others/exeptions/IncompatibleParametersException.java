package com.petcalcsalary.CalcSalaryProject.others.exeptions;

/**
 * Эксепшен, который выбрасывается в определенных частях программыв случае, если переданы несовместимые друг с другом данные
 */
public class IncompatibleParametersException extends MainSalaryException {

    public IncompatibleParametersException(String message){
        super(message);
    }

    public IncompatibleParametersException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncompatibleParametersException(Throwable cause) {
        super(cause);
    }

}
