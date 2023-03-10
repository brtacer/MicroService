package com.berat.repository;

import com.berat.repository.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserProfileRepository extends JpaRepository<UserProfile,Long> {
}
