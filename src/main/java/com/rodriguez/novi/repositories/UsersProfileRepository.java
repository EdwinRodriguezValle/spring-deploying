package com.rodriguez.novi.repositories;

import com.rodriguez.novi.entities.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersProfileRepository extends JpaRepository<UserProfileEntity, Long> {
}
