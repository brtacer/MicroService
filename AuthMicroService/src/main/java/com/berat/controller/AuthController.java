package com.berat.controller;

import static com.berat.constants.EndPoints.*;

import com.berat.dto.request.DoLoginRequestDto;
import com.berat.dto.request.RegisterRequestDto;
import com.berat.exception.AuthServiceException;
import com.berat.exception.EErrorType;
import com.berat.repository.entity.Auth;
import com.berat.service.AuthService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(REGISTER)
    public ResponseEntity<Auth> register(@RequestBody @Valid RegisterRequestDto dto){
        if (!dto.getPassword().equals(dto.getRepassword()))
            throw  new AuthServiceException(EErrorType.REGISTER_ERROR_PASSWORD_UNMATCH);
        return ResponseEntity.ok(authService.register(dto));
    }
    @PostMapping(DOLOGIN)
    public ResponseEntity<String> doLogin(@RequestBody DoLoginRequestDto dto){
        return ResponseEntity.ok(authService.doLogin(dto));
    }
    @GetMapping(GETALL)
    public ResponseEntity<List<Auth>> findAll(String token){
        return ResponseEntity.ok(authService.findAll(token));
    }
}
