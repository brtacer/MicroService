package com.berat.config;

import com.berat.exception.EErrorType;
import com.berat.exception.ElasticServiceException;
import com.berat.utility.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenManager jwtTokenManager;
    @Autowired
    JwtUserDetails jwtUserDetails;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String BearerToken = request.getHeader("Authorization");
        /**
         *  Bu kısım filtreye takılan tüm isteklerin inceleneceği yerdir. Bu nedenle
         *  buraya gelen tüm isteklerin Başlık(Header) kısmında Bearer token ı arıyoruz.
         *  Eğer Bearer token yok ise(null) hata fırlatıcaz.
         *  Eğer gelen değerin içinde token yok ise hata fırlatıcaz.
         */
        if (SecurityContextHolder.getContext().getAuthentication()==null) {
            if (BearerToken==null || !BearerToken.startsWith("Bearer "))
                throw new ElasticServiceException(EErrorType.INVALID_TOKEN);
            String token = BearerToken.substring(7);
            Optional<Long> authId = jwtTokenManager.getByIdFromToken(token);
            if (authId.isEmpty())
                throw new ElasticServiceException(EErrorType.INVALID_TOKEN);
            /**
             *  Eğer tüm koşullar doğru ise, buradan itibaren bilgileri kontrol eden
             *  kişiye ait Spring Security tarafından kullanılacak olan bir kimlik kartı hazrılamamız lazım
             */
            UserDetails userDetails = jwtUserDetails.getUserByAuthId(authId.get());
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request,response);//şimdilik

    }
}
