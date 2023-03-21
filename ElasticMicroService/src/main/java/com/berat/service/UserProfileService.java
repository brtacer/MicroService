package com.berat.service;

import com.berat.dto.request.AddUserRequest;
import com.berat.dto.request.BaseRequestDto;
import com.berat.mapper.IUserProfileMapper;
import com.berat.repository.IUserProfileRepository;
import com.berat.repository.entity.UserProfile;
import com.berat.utility.ServiceManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Optional<UserProfile> findByAuthId(Long authId){
        return repository.findOptionalByAuthid(authId);
    }
    public void saveDto(AddUserRequest dto) {
        /**
         *  Aynı kullanıcı tekrar kaydedilmeye çalışıyor olabilir.
         */
            if (!repository.existsByUserprofileid(dto.getId()))
                save(IUserProfileMapper.INSTANCE.toUserProfile(dto));
    }
    /**
     * Sayfalama işlemleri belli ilgiler olmadan işlem yapmak mümkün olmayacaktır.
     *  -> Liste gelmeli
     *  -> Total kayıt miktarı
     *  -> Hangi sayfadayım
     *  -> Kaç sayfa var
     *  -> Sonraki sayfa var mı?
     *  -> Son sayfada mıyım?
     */
    public Page<UserProfile> findAll(BaseRequestDto dto){
        /**
         *  Sıralama ve Sayfalama için bize nesneler ve ayarlamalar gerekli.
         */
        Pageable pageable=null;
        Sort sort=null;
        /**
         *  Eğer kişi sıralama istediği alanı yazmamış ise sıralama yapmak istemiyordur.
         */
        if (dto.getSortParameter()!=null){
            String direction= dto.getDirection()==null ? "ASC" : dto.getDirection();
            sort = sort.by(Sort.Direction.fromString(direction),dto.getSortParameter());
        }
        /**
         * 1. durum -> sıralama yapmak ister ve sayfalama yapmak ister
         * 2. durum -> sıralama yapmak istemiyor ama sayfalamak yapmak istiyor
         * 3. durum -> sıralama yapmak istemiyor ve sayfalama isteğinde bulunmuyor.
         */
        Integer pageSize= dto.getPageSize()==null ? 10 :
                          dto.getPageSize() < 1 ? 10 : dto.getPageSize();
        if (sort!=null && dto.getCurrentPage()!=null){
            pageable = PageRequest.of(dto.getCurrentPage(),pageSize,sort);
        }else if (sort==null && dto.getCurrentPage()!=null){
            pageable = PageRequest.of(dto.getCurrentPage(),pageSize);
        }else {
            pageable =PageRequest.of(0,pageSize);
        }
        return repository.findAll(pageable);

    }
}
