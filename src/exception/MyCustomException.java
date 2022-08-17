package com.ideas2it.exception;

public class MyCustomException extends Exception { 

    public MyCustomException() {  
    super();  
    }  
 
    public MyCustomException(String errorMessage) {  
    super(errorMessage);  
    }  
    
    public MyCustomException(Throwable error) {  
    super(error);
    }

    public MyCustomException(String errorMessage, Throwable error) {  
    super(errorMessage, error);  
    }    
}  