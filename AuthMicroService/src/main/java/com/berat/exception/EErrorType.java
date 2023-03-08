package com.berat.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EErrorType {

    REGISTER_ERROR_USERNAME(2000,"Bu kullanıcı adı daha önce alınmıştır",BAD_REQUEST),
    REGISTER_ERROR_PASSWORD_UNMATCH(2001,"Girilen parolalar uyuşmuyor",BAD_REQUEST),
    LOGIN_ERROR_USERNAME_PASSWORD(2002,"Kullanıcı adı ya da şifre hatalıdır",BAD_REQUEST),
    INVALID_TOKEN(2003,"Geçersiz token",BAD_REQUEST),

    INVALID_PARAMETER(3001,"Geçersiz parametre girişi yaptınız",BAD_REQUEST),
    METHOD_MIS_MATCH_ERROR(2004,"Giriş yaptığınız değer, istenilen değerle uyuşmamaktadır",BAD_REQUEST),
    METHOD_NOT_VALİD_ERROR(2005,"URL içinde eksik parametre gönderimi",BAD_REQUEST);

    private int code;
    private String message;
    private HttpStatus httpStatus;
}
