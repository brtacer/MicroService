package com.berat.mapper;

import com.berat.dto.request.AddUserRequest;
import com.berat.repository.entity.UserProfile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T10:47:43+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IUserProfileMapperImpl implements IUserProfileMapper {

    @Override
    public UserProfile toUserProfile(AddUserRequest dto) {
        if ( dto == null ) {
            return null;
        }

        UserProfile.UserProfileBuilder<?, ?> userProfile = UserProfile.builder();

        userProfile.userprofileid( dto.getId() );
        if ( dto.getId() != null ) {
            userProfile.id( String.valueOf( dto.getId() ) );
        }
        userProfile.authid( dto.getAuthid() );
        userProfile.username( dto.getUsername() );
        userProfile.email( dto.getEmail() );
        userProfile.ad( dto.getAd() );
        userProfile.adres( dto.getAdres() );
        userProfile.telefon( dto.getTelefon() );
        userProfile.avatar( dto.getAvatar() );

        return userProfile.build();
    }
}
