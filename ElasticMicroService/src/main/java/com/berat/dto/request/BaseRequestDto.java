package com.berat.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseRequestDto {
    String token;
    /**
     *  Her bir istekte göstermek istedğimiz kayıt adedi
     */
    Integer pageSize;
    /**
     *  Şuan bulunduğumuz, talep ettiğimiz sayfa nosu
     */
    Integer currentPage;
    /**
     *  Hangi alan a göre sıralama yapılacaksa o alanın adı
     */
    String sortParameter;
    /**
     *  Sıralamanın yönü, a...z ASC,DESC
     */
    String direction;

}
