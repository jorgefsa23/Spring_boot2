package com.user.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.database.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
