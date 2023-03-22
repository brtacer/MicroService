package com.berat.controller;

import static com.berat.constant.EndPoints.*;

import com.berat.dto.request.AddUserRequest;
import com.berat.dto.request.BaseRequestDto;
import com.berat.repository.entity.UserProfile;
import com.berat.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ELASTIC+USER)
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;

    @PostMapping(SAVE)
    public ResponseEntity<Void> addUser(@RequestBody AddUserRequest dto){
        userProfileService.saveDto(dto);
        return ResponseEntity.ok().build();
    }
    @GetMapping(GETALL)
    public ResponseEntity<Iterable<UserProfile>> findAll(){
        return ResponseEntity.ok(userProfileService.findAll());
    }
    @GetMapping(GETALL+"vip")
    @PreAuthorize("hasAuthority('VIP')")
    public ResponseEntity<Iterable<UserProfile>> findAllVip(){
        return ResponseEntity.ok(userProfileService.findAll());
    }
    @GetMapping(GETALL+"ben")
    @PreAuthorize("hasAuthority('BEN')")
    public ResponseEntity<Iterable<UserProfile>> findAllBen(){
        return ResponseEntity.ok(userProfileService.findAll());
    }
    @PostMapping(GETALLPAGE)
    public ResponseEntity<Page<UserProfile>> findAll(@RequestBody BaseRequestDto dto){
        return ResponseEntity.ok(userProfileService.findAll(dto));
    }
}
