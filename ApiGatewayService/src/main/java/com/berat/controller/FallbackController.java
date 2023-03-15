package com.berat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fallback")
public class FallbackController {
    @GetMapping("/auth")
    public ResponseEntity<String> fallbackAuth(){
        return ResponseEntity.ok(
                "Auth servisi şuan hizmet vermemektedir.Lütfen daha sonra tekrar deneyin");

    }
    @GetMapping("/product")
    public ResponseEntity<String> fallbackProduct(){
        return ResponseEntity.ok(
                "Product servisi şuan hizmet vermemektedir.Lütfen daha sonra tekrar deneyin");

    }
    @GetMapping("/sales")
    public ResponseEntity<String> fallbackSale(){
        return ResponseEntity.ok(
                "Sale servisi şuan hizmet vermemektedir.Lütfen daha sonra tekrar deneyin");

    }
    @GetMapping("/user")
    public ResponseEntity<String> fallbackUser(){
        return ResponseEntity.ok(
                "User servisi şuan hizmet vermemektedir.Lütfen daha sonra tekrar deneyin");

    }
}
