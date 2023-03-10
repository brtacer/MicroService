package com.berat.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenManager {

    @Value("${authservice.bunu-ben-yazdim.secret-key}")
    String secretKey;
    @Value("${authservice.bunu-ben-yazdim.issuer}")
    String issuer;
    public Optional<String> createToken(Long id){
        String token=null;
        Long exDate =1000L*60*5;//5 dakika
        try{
            /**
             *  DİKKAT !!!
             *  Claim objelerinin içine önemli ve herkes ile paylaşamayacağınız
             *  bilgileri koyamazsınız.
             *  email, usernam, password v.s. gibi öenmli bilgileri payload içine koyamazsınız.
             */
            token = JWT.create().withAudience()
                    .withClaim("id",id)
                    .withClaim("howtopage","AuthMicroService")
                    .withClaim("lastjoin",System.currentTimeMillis())
                    .withClaim("ders","Java JWT")
                    .withClaim("Hoca","Muhammet Hoca")
                    .withIssuer(issuer)// JWT nin sahibidir.
                    .withIssuedAt(new Date()) // token oluşturulma tarihi
                    .withExpiresAt(new Date(System.currentTimeMillis()+exDate)) // geçerlilik süresi
                    .sign(Algorithm.HMAC512(secretKey));
            return Optional.of(token);
        }catch (Exception exception){
            return Optional.empty();
        }

    }

    public Boolean verifyToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT==null)
                return false;
        }catch (Exception exception){
            return false;
        }
        return true;
    }

    public Optional<Long> getByIdFromToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT==null)
                return Optional.empty();
            Long id=decodedJWT.getClaim("id").asLong();
            return Optional.of(id);
        }catch (Exception exception){
            return Optional.empty();
        }

    }
}
