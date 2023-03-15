package com.berat.rabbitmq.consumer;


import com.berat.rabbitmq.model.SaveAuthModel;
import com.berat.repository.entity.UserProfile;
import com.berat.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {

    private final UserProfileService userProfileService;
    /**
     * Buradaki metotlar void olur bu ve producerda
     */
    @RabbitListener(queues = "queue-auth")
    public void createUserFromHandleQueue(SaveAuthModel model){
        System.out.println("Gelen Data..: "+ model.getUsername());
        userProfileService.save(UserProfile.builder()
                        .authid(model.getAuthid())
                        .username(model.getUsername())
                        .username(model.getEmail())
                .build());
    }
}
