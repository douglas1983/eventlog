package com.codenation.desafio.desafio.service;

import java.util.Optional;

import com.codenation.desafio.desafio.dto.EventLogDTO;
import com.codenation.desafio.desafio.dto.EventLogUpdateDTO;
import com.codenation.desafio.desafio.entity.EventLog;
import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventLogServiceInterface {
  public Optional<EventLog> findById(Long id);

  public Page<EventLogDTO> findAll(Predicate predicate, Pageable pageable);

  public EventLog save(EventLog record);

  public EventLog saveDTO(EventLogUpdateDTO record);

  public void deleleById(Long id);
}
