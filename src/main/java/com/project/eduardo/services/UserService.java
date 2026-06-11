package com.project.eduardo.services;

import com.project.eduardo.dto.request.UserDTOrequest;
import com.project.eduardo.dto.response.UserDTOresponse;
import com.project.eduardo.entities.User;
import com.project.eduardo.repositories.UserRepository;
import com.project.eduardo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public UserDTOresponse FindById(Long id){
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado! ID:" + id));
        UserDTOresponse response = new UserDTOresponse();
        response.setName(user.getName());
        response.setPhone(user.getPhone());
        response.setEmail(user.getEmail());
        response.setId(user.getId());
        return response;
    }

    @Transactional
    public UserDTOresponse insertUser(UserDTOrequest obj){
        User user = new User();
        user.setName(obj.getName());
        user.setEmail(obj.getEmail());
        user.setPhone(obj.getPhone());
        user.setPassword(obj.getPassword());

        User usersaved = repository.save(user);

        UserDTOresponse response = new UserDTOresponse();
        response.setName(usersaved.getName());
        response.setEmail(usersaved.getEmail());
        response.setPhone(usersaved.getPhone());
        response.setId(usersaved.getId());
        return response;
    }
    public void deleteUser(Long id){
        User userDelete = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        repository.delete(userDelete);
    }
    @Transactional
    public UserDTOresponse updateUser(Long id, UserDTOrequest obj){
        User userfind = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        updateData(userfind,obj);
        User userSaved = repository.save(userfind);
        UserDTOresponse response = new UserDTOresponse();
        response.setId(userSaved.getId());
        response.setName(userSaved.getName());
        response.setEmail(userSaved.getEmail());
        response.setPhone(userSaved.getPhone());
        return response;

    }
    private void updateData(User userfind, UserDTOrequest obj) {
        if (obj.getName() != null) {
            userfind.setName(obj.getName());
        }
        if (obj.getEmail() != null) {
            userfind.setEmail(obj.getEmail());
        }
        if (obj.getPhone() != null) {
            userfind.setPhone(obj.getPhone());
        }
    }
}
