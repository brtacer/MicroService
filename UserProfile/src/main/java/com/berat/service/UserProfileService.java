package com.berat.service;

import com.berat.dto.request.UserProfileSaveRequestDto;
import com.berat.manager.IElasticServiceManager;
import com.berat.mapper.IUserProfileMapper;
import com.berat.rabbitmq.model.SaveAuthModel;
import com.berat.repository.IUserProfileRepository;
import com.berat.repository.entity.UserProfile;
import com.berat.utility.ServiceManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {
    private final IUserProfileRepository repository;
    private final IElasticServiceManager elasticServiceManager;

    public UserProfileService(IUserProfileRepository repository, IElasticServiceManager elasticServiceManager) {
        super(repository);
        this.repository = repository;
        this.elasticServiceManager = elasticServiceManager;
    }

    public Boolean saveDto(UserProfileSaveRequestDto dto) {
        save(IUserProfileMapper.INSTANCE.toUserProfile(dto));
        return true;
    }
    public void saveRabbit(SaveAuthModel model){
        UserProfile userProfile=IUserProfileMapper.INSTANCE.toUserProfile(model);
        save(userProfile);
//        elasticServiceManager.addUser(userProfile);

    }

    /**
     *  Bu uzun süren bir işlemi simüle etmek için Thread kullanılarak bekletilmiş bir methoddur.
     *
     */
    @Cacheable(value = "getUpperName")
    public String getUpper(String name){

        try {
            Thread.sleep(3000L);
        }catch (Exception exception){

        }
        return name.toUpperCase();
    }
    @CacheEvict(value = "getUpperName",allEntries = true)
    public void clearCache(){
        System.out.println("Tüm kayıtları temizledik");
    }
}
