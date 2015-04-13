package edu.harvard.mgh.lcs.sprout.forms.study.exception;

/**
 * Created by slorenz on 3/31/15.
 */
public class UnauthorizedActionException extends Throwable {

    private String message;

    public UnauthorizedActionException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
