package com.berat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *  Konfigürasyon dosyası olarak springe bildirecğimiz sınıflara ekliyoruz.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ElasticSecurityConfig {

    @Bean
    JwtTokenFilter getTokenFilter(){
        return new JwtTokenFilter();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        /**
         * _csrf -> bunu kapatıyoruz (gelen forma uuid bir kod ataması yapar).Rest api de kapatılması zorunlu
         */
        httpSecurity.csrf().disable();
        /**
         *  Gelen bütün isteklere oturum açılmış mı kendini doğrulamış mı bak. !
         *  Eğer özel adreslere erişimi açmak istiyorsanız bunları belirterek izin vermelisiniz.
         *  Match("/{URLS}") için izin ver.
         */
        httpSecurity.authorizeRequests()
                .antMatchers("/mylogin.html","/elastic/user/**").permitAll()
                .anyRequest().authenticated();
        /**
         *  Yetkisiz girişlerde login formuna  yönlendirme ->
         */
        //httpSecurity.formLogin();
        /**
         *  Eğer kullanıcı kendi login formunuzu göstermek istiyorsanız. o zmana kendi formunuza
         *  izin vererek yönlendirme işlemi yapmalısınız.
         */
        //httpSecurity.formLogin().loginPage("/mylogin.html");
        /**
         *  Auth service üzerinden kendisini doğrulayan bir kişinin isteklerinin nasıl işleyeceğini
         *  çözmemiz gerekiyor.
         *  Kişinin elinde olan token bilgisi okunarak bu kişiye yetkileri dahilinde geçerli olan
         *  token üzerinden oturum izni verilecek, böylece kişi her seferinde kendini doğrulamak zorunda kalmayacak
         *  Bunu başarmak için öncelikle filter işleminin öncesine bir işlem adımı sokarak kişiyi doğrularız
         */
        httpSecurity.addFilterBefore(getTokenFilter(), UsernamePasswordAuthenticationFilter.class);



        return httpSecurity.build();
    }
}
