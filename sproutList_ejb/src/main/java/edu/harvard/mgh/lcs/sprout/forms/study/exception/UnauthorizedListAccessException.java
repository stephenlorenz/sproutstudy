package edu.harvard.mgh.lcs.sprout.forms.study.exception;

/**
 * Created by slorenz on 3/31/15.
 */
public class UnauthorizedListAccessException extends Throwable {

private String message;

public UnauthorizedListAccessException(String message) {
        this.message = message;
        }

@Override
public String getMessage() {
        return message;
        }
        }
