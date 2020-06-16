package com.codenation.desafio.desafio.dto;

import java.util.Date;

import com.codenation.desafio.desafio.entity.Auditable;
import com.codenation.desafio.desafio.enuns.Level;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventLogDTO extends Auditable<String> {
  private Long id;

  private Level level;

  private String description;

  private String origin;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date eventDate;

  private Long quantity;
}
