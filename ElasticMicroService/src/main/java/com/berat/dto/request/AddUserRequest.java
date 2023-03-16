package com.berat.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUserRequest {
    Long id;
    Long authid;
    String username;
    String email;
    String ad;
    String adres;
    String telefon;
    String avatar;
}
