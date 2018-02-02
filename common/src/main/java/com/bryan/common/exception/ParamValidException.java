package com.bryan.common.exception;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/1/29
 */
public class ParamValidException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errorParam = null;

    public ParamValidException() {
    }

    public ParamValidException(Throwable cause) {
        super(cause);
    }

    public ParamValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamValidException(String message, String errorParam) {
        super(message);
        this.errorParam = errorParam;
    }

    public ParamValidException(String message) {
        super(message);
    }

    public String getErrorParam() {
        return errorParam;
    }

    public void setErrorParam(String errorParam) {
        this.errorParam = errorParam;
    }
}
