package com.berat.mapper;

import com.berat.dto.request.AddUserRequest;
import com.berat.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserProfileMapper {
    IUserProfileMapper INSTANCE = Mappers.getMapper(IUserProfileMapper.class);

    @Mapping(target = "userprofileid", source = "id")//source parantez içi target dönüşecek nesne
    UserProfile toUserProfile(final AddUserRequest dto);
}
