package com.berat.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Tüm istisnaların üzerinden geçtiği bir method oluşturuyorum ve Hata mesajını burada
     * dönüyorum.
     */

    private ErrorMessage createErrorMessage(EErrorType errorType,Exception exception){

        return ErrorMessage.builder()
                .code(errorType.getCode())
                .message(errorType.getMessage())
                .build();
    }

    /**
     *  @ExceptionHandler -> Uygulama içinde oluşacak hatanın türünü bizden alarak
     *  onun yakalanasını sağlar. Böylece yakalanan istisnayı metoda geçer.
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleException(Exception exception){
        System.out.println("Tespit edilmeyen hata oluştu"+ exception.getMessage());//tespit edilmeyen hataları bulup aşağılara eklicez.
        return ResponseEntity.badRequest().body("Uygulama da beklenmeyen bir hata oluştu.."+exception.getMessage());
    }
    @ResponseBody
    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<ErrorMessage> handleSatisManagerException(UserServiceException exception){

        return new ResponseEntity<>(createErrorMessage(exception.getErrorType(),exception),exception.getErrorType().getHttpStatus());
    }

    @ResponseBody
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorMessage> handleInvalidFormatException(InvalidFormatException exception){
        EErrorType errorType=EErrorType.INVALID_PARAMETER;
        return new ResponseEntity<>(createErrorMessage(errorType,exception),errorType.getHttpStatus());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception){
        EErrorType errorType=EErrorType.METHOD_MIS_MATCH_ERROR;
        return new ResponseEntity<>(createErrorMessage(errorType,exception),errorType.getHttpStatus());
    }
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){
        EErrorType errorType=EErrorType.METHOD_MIS_MATCH_ERROR;
        return new ResponseEntity<>(createErrorMessage(errorType,exception),errorType.getHttpStatus());
    }
    /**
     * /urun/findbyid/234/sort/desc
     * /urun/findbyid/sort/desc
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        EErrorType errorType=EErrorType.METHOD_NOT_VALİD_ERROR;
        return new ResponseEntity<>(createErrorMessage(errorType,exception),errorType.getHttpStatus());
    }
}
