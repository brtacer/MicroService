package com.berat.service;

import com.berat.dto.request.DoLoginRequestDto;
import com.berat.dto.request.RegisterRequestDto;
import com.berat.exception.AuthServiceException;
import com.berat.exception.EErrorType;
import com.berat.manager.IUserProfileManager;
import com.berat.mapper.IAuthMapper;
import com.berat.rabbitmq.model.SaveAuthModel;
import com.berat.rabbitmq.producer.CreateUserProducer;
import com.berat.repository.IAuthRepository;
import com.berat.repository.entity.Auth;
import com.berat.utility.JwtTokenManager;
import com.berat.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository authRepository;
    private final JwtTokenManager tokenManager;
    private final IUserProfileManager iUserProfileManager;
    private final CreateUserProducer createUserProducer;

    public AuthService(IAuthRepository authRepository,JwtTokenManager tokenManager,
                       IUserProfileManager iUserProfileManager,CreateUserProducer createUserProducer) {
        super(authRepository);
        this.authRepository = authRepository;
        this.tokenManager=tokenManager;
        this.iUserProfileManager=iUserProfileManager;
        this.createUserProducer=createUserProducer;
    }

    public Auth register(RegisterRequestDto dto){
        if (authRepository.isUsername(dto.getUsername()))
            throw new AuthServiceException(EErrorType.REGISTER_ERROR_USERNAME);
        Auth auth=IAuthMapper.INSTANCE.toAuth(dto);
        save(auth);
        //iUserProfileManager.save(IAuthMapper.INSTANCE.fromAuth(auth));
        createUserProducer.convertAndSend(SaveAuthModel.builder()
                        .authid(auth.getId())
                        .email(auth.getEmail())
                        .username(auth.getUsername())
                .build());
        return auth;
    }

    public Optional<Auth> findOptionalByUsernameAndPassword(String username, String password){
        return authRepository.findOptionalByUsernameAndPassword(username, password);
    }

    public String doLogin(DoLoginRequestDto dto) {
        Optional<Auth> auth=authRepository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (auth.isEmpty())
            throw new AuthServiceException(EErrorType.LOGIN_ERROR_USERNAME_PASSWORD);
        return tokenManager.createToken(auth.get().getId()).get();
    }

    public List<Auth> findAll(String token){
        Optional<Long> id=tokenManager.getByIdFromToken(token);
        if (id.isEmpty())
            throw new AuthServiceException(EErrorType.INVALID_TOKEN);
        if ( findById(id.get()).isEmpty())
            throw new AuthServiceException(EErrorType.INVALID_TOKEN);
        return findAll();

    }
}
