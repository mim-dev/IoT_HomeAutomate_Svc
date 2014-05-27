package com.mimdevelopment.iot.challenge.homeautomate.exception;

/**
 * User: luther stanton
 * Date: 4/29/14
 * Time: 3:59 PM
 */

public class HomeAutomatePropertyValidationException extends Exception {

    public HomeAutomatePropertyValidationException() {
        super();
    }

    public HomeAutomatePropertyValidationException(String message){
        super(message);
    }

    public HomeAutomatePropertyValidationException(String message, Throwable cause){
        super(message, cause);
    }

    public HomeAutomatePropertyValidationException(Throwable cause){
        super(cause);
    }
}
