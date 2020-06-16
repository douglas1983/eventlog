package com.codenation.desafio.desafio.repository;

import java.util.Optional;

import com.codenation.desafio.desafio.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
