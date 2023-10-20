package com.user.database.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.user.database.entities.User;

import com.user.database.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public User findById(Long id){
		return repository.findById(id).get();
	}

}
