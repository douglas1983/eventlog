package com.codenation.desafio.desafio.mappers;

import org.mapstruct.Mapper;

import com.codenation.desafio.desafio.dto.UserUpdateDTO;
import com.codenation.desafio.desafio.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User map(UserUpdateDTO userDTO);

}
