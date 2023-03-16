package com.berat.manager;

import com.berat.repository.entity.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.berat.constants.EndPoints.SAVE;

@FeignClient(name = "elastic-service-manager",url = "http://localhost:9100/elastic/user",decode404 = true)
public interface IElasticServiceManager {

    @PostMapping(SAVE)
    public ResponseEntity<Void> addUser(@RequestBody UserProfile dto);
}
