package com.codenation.desafio.desafio.endpoints;

import java.util.List;

import com.codenation.desafio.desafio.dto.UserUpdateDTO;
import com.codenation.desafio.desafio.entity.User;
import com.codenation.desafio.desafio.service.Impl.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/user")
@Tag(name = "Usuários", description = "EndPoints para o Usuário")
public class UserController {

    private UserService service;

    @Operation(summary = "Busca um Usuário por ID", description = "Busca um Usuário por ID", tags = { "Usuários" })
    @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json"))
    @GetMapping(path = { "/{id}" })

    public User findById(@PathVariable Long id) {
        return service.findById(id).orElse(null);
    }

    @Operation(summary = "Busca todos os usuários", description = "Busca todos os usuários", tags = { "Usuários" })
    @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json"))

    @GetMapping()
    public List<User> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Adiciona Novo Usuário", description = "Adiciona Novo Usuário", tags = { "Usuários" })
    @ApiResponse(description = "Successful Operation", responseCode = "201", content = @Content(mediaType = "application/json"))

    @PostMapping(consumes = { "application/json" })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> create(@RequestBody UserUpdateDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveDTO(user));
    }

    @Operation(summary = "Altera um Usuário por ID", description = "Altera um Usuário por ID", tags = { "Usuários" })
    @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json"))
    @PutMapping(value = "/{id}", consumes = { "application/json" })
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody User user) {
        return service.findById(id).map(record -> {
            record.setDataUpdate(user);
            User updated = service.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Excluí um Usuário por ID", description = "Excluí um Usuário por ID", tags = { "Usuários" })
    @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json"))
    @DeleteMapping(path = { "/{id}" })
    public ResponseEntity<?> delete(@PathVariable long id) {
        return service.findById(id).map(record -> {
            service.deleleById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

}
