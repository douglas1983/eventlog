package com.codenation.desafio.desafio.dto;

import java.util.Date;

import com.codenation.desafio.desafio.enuns.Level;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventLogUpdateDTO {

  private Level level;

  private String description;

  private String eventLog;

  private String origin;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date eventDate;

  private Long quantity;
}
