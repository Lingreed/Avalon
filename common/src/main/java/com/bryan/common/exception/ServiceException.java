package com.bryan.common.exception;
/**
 * ClassName: ServiceException  
 * Function: 业务异常
 */
public class ServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorParam = null;

	public ServiceException() {
		
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }
    
    public ServiceException(String message, String errorParam) {
        super(message);
        this.errorParam = errorParam;
    }

	public String getErrorParam() {
		return errorParam;
	}

	public void setErrorParam(String errorParam) {
		this.errorParam = errorParam;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}
