package com.mimdevelopment.iot.challenge.homeautomate.exception;

import com.mimdevelopment.iot.challenge.homeautomate.exception.ExceptionReasonCode;

/**
 * User: luther stanton
 * Date: 4/25/14
 * Time: 11:49 AM
 */

public class HomeAutomateException extends Exception {

    private int reasonCode;

    public int getReasonCode() {
        return reasonCode;
    }

    public HomeAutomateException() {
        super();
        reasonCode = ExceptionReasonCode.UNKNOWN;
    }

    public HomeAutomateException(String message){
        super(message);
        reasonCode = ExceptionReasonCode.UNKNOWN;
    }

    public HomeAutomateException(String message, Throwable cause){
        super(message, cause);
        reasonCode = ExceptionReasonCode.UNKNOWN;
    }

    public HomeAutomateException(Throwable cause){
        super(cause);
        reasonCode = ExceptionReasonCode.UNKNOWN;
    }

    public HomeAutomateException(int reasonCode) {
        super();
        this.reasonCode = reasonCode;
    }

    public HomeAutomateException(String message, int reasonCode){
        super(message);
        this.reasonCode = reasonCode;
    }

    public HomeAutomateException(String message, Throwable cause, int reasonCode){
        super(message, cause);
        this.reasonCode = reasonCode;
    }

    public HomeAutomateException(Throwable cause, int reasonCode){
        super(cause);
        this.reasonCode = reasonCode;
    }
}
