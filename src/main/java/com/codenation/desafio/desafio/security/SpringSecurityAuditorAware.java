package com.codenation.desafio.desafio.security;

import java.util.Optional;

import com.codenation.desafio.desafio.entity.User;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

  @Override
  public Optional<String> getCurrentAuditor() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    return Optional.of(((User) auth.getPrincipal()).getEmail());
  }
}