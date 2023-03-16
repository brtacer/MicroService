package com.berat.exception;

import lombok.Getter;

/**
 * Model adı yani service adı yazılır
 * TwitterManagerException gibi
 * MVc de de service adı AuthManagerEXception gibi
 */
@Getter
public class ElasticServiceException extends RuntimeException{
    private final EErrorType errorType;
    public ElasticServiceException(EErrorType errorType){
        super(errorType.getMessage());
        this.errorType=errorType;
    }

    /**Bazı specifik mesaj gerektiren hatalar için alltaki metodu yazdık.*/
    public ElasticServiceException(EErrorType errorType, String message){
        super(message);
        this.errorType=errorType;
    }
}
