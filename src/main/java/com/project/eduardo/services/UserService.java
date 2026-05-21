package com.project.eduardo.services;

import com.project.eduardo.entities.User;
import com.project.eduardo.repositories.UserRepository;
import com.project.eduardo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User FindById(Long id){
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insertUser(User obj){
        return repository.save(obj);
    }
    public void deleteUser(Long id){
        User userDelete = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        repository.delete(userDelete);
    }
    public User updateUser(Long id, User obj){

        User userfind = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        updateData(userfind,obj);
        return repository.save(userfind);

    }
    private void updateData(User userfind, User obj) {
        userfind.setName(obj.getName());
        userfind.setEmail(obj.getEmail());
        userfind.setPhone(obj.getPhone());
    }
}
