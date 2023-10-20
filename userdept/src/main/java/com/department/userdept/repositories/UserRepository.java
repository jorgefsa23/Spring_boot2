package com.department.userdept.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.department.userdept.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
