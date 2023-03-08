package com.berat.mapper;

import com.berat.dto.request.RegisterRequestDto;
import com.berat.repository.entity.Auth;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-08T11:04:57+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IAuthMapperImpl implements IAuthMapper {

    @Override
    public Auth toAuth(RegisterRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Auth.AuthBuilder auth = Auth.builder();

        auth.username( dto.getUsername() );
        auth.email( dto.getEmail() );
        auth.password( dto.getPassword() );

        return auth.build();
    }
}
