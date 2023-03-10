package com.berat.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {
    @NotBlank(message = "Kullanıcıadı boş geçilemez.")
    @Size(min = 3,max = 32)
    String username;
    @Email(message = "Lütfen geçerli bir email adresi giriniz.")
    String email;
    @NotBlank(message = "Şifre boş geçilemez")
    @Size(min = 8,max = 64)
    @Pattern(message = "Şifre en az 8 karakter olmalıdır.en az bir büyük harf ve özel karakter olmalıdır.!",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$")
    String password;
    String repassword;
}
