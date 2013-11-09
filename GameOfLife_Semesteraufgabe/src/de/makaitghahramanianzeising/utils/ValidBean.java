package de.makaitghahramanianzeising.utils;

/**
 * Saves exceptions to enable a validation check
 * of the user's input at a later time.
 */

public class ValidBean {

    private boolean isValid;
    private Exception exceptionOnInvalid;

    public ValidBean(boolean isValid, Exception exceptionOnInvalid) {
        this.isValid = isValid;
        this.exceptionOnInvalid = exceptionOnInvalid;
    }

    public boolean isValid() {
        return isValid;
    }

    public Exception getExceptionOnInvalid() {
        return exceptionOnInvalid;
    }
}
