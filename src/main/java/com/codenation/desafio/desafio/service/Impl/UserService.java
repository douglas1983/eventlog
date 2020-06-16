package com.codenation.desafio.desafio.service.Impl;

import java.util.List;
import java.util.Optional;

import com.codenation.desafio.desafio.dto.UserUpdateDTO;
import com.codenation.desafio.desafio.entity.User;
import com.codenation.desafio.desafio.mappers.UserMapper;
import com.codenation.desafio.desafio.repository.UserRepository;
import com.codenation.desafio.desafio.service.UserServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserServiceInterface, UserDetailsService {

    private UserRepository userRepository;
    @Autowired
    private UserMapper mapper;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {

        return userRepository.findByEmail(email).get();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User saveDTO(UserUpdateDTO user) {
        User userSave = mapper.map(user);
        return userRepository.save(userSave);
    }

    public void deleleById(Long id) {
        userRepository.deleteById(id);
    }

}
