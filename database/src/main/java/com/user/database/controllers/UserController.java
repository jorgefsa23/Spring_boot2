package com.user.database.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.user.database.entities.User;
import com.user.database.repositories.UserRepository;
import exceptions.ResourceNotFoundException;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	
	@Autowired
	private UserRepository repository;
	
	//método exibir todos os users
	@GetMapping
	public List<User> findAll(){
		return repository.findAll();
	}
	
	//método encontrar user por id
	@GetMapping(value = "/{id}")
	public User findAll(@PathVariable Long id) {
		var userToFind = repository.findById(id);
    	//if userToUpdate...
        if (userToFind.isEmpty()) {
        	//return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message not found")
        	throw new ResourceNotFoundException("BUSCA falhou!... Para User id: " + id);
        
		return repository.findById(id).get();
		//2 return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BUSCA falhou!... Para User id: " + id));
	}
	
	//método inserir novo user
	@PostMapping
	public User insert(@RequestBody User user) {
		return repository.save(user);
	}
	
	//método delete - apagar user por id
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") Long id){
		var userToDelete = repository.findById(id);
    	//if userToUpdate.
        if (userToDelete.isEmpty()) {
            throw new ResourceNotFoundException("DELETE falhou!... Para User id: " + id);
        }
		repository.deleteById(id);
        
	}
	
    @PutMapping("/{id}")
    public User updateById(@PathVariable Long id, @RequestBody User user) {       
    	var userToUpdate = repository.findById(id);
    	//if userToUpdate.
        if (userToUpdate.isEmpty()) {
            throw new ResourceNotFoundException("UPDATE falhou!. Para User id: " + id);
        }
    	user.setId(id);
        return repository.save(user);
    }
	
}
