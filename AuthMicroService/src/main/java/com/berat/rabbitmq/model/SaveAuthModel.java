package com.berat.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  ÖNEMLİ !!!
 *  Mutlaka modeller serileştirilmelidir.
 *  Ayrıca paket ismi dahil olmak üzere bu sınıfın aynısı iletilen service te de olmalıdır.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveAuthModel implements Serializable {
    Long authid;
    String username;
    String email;
}
