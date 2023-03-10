package com.berat.service;

import com.berat.dto.request.UserProfileSaveRequestDto;
import com.berat.mapper.IUserProfileMapper;
import com.berat.repository.IUserProfileRepository;
import com.berat.repository.entity.UserProfile;
import com.berat.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {
    private final IUserProfileRepository repository;

    public UserProfileService(IUserProfileRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Boolean saveDto(UserProfileSaveRequestDto dto) {
        save(IUserProfileMapper.INSTANCE.toUserProfile(dto));
        return true;
    }
}
