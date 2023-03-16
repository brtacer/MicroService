package com.berat.service;

import com.berat.dto.request.AddUserRequest;
import com.berat.mapper.IUserProfileMapper;
import com.berat.repository.IUserProfileRepository;
import com.berat.repository.entity.UserProfile;
import com.berat.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,String> {
    private final IUserProfileRepository repository;

    public UserProfileService(IUserProfileRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public void saveDto(AddUserRequest dto) {
        /**
         *  Aynı kullanıcı tekrar kaydedilmeye çalışıyor olabilir.
         */
            if (!repository.existsByUserprofileid(dto.getId()))
                save(IUserProfileMapper.INSTANCE.toUserProfile(dto));
    }
}
