package com.codenation.desafio.desafio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.codenation.desafio.desafio.dto.EventLogUpdateDTO;
import com.codenation.desafio.desafio.enuns.Level;
import com.google.common.base.Strings;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = "id")
@Table(name = "event_logs")
public class EventLog extends Auditable<String> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  @NotNull
  @Enumerated(EnumType.STRING)
  private Level level;

  @Column
  @NotNull
  @Size(max = 100)
  private String description;

  @Column
  @NotNull
  @Lob
  private String eventLog;

  @Column
  @NotNull
  @Size(max = 100)
  private String origin;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @Column(nullable = false, name = "event_date")
  private Date eventDate;

  @Column
  private Long quantity;

  public void setDataUpdate(EventLogUpdateDTO event) {
    if (!Strings.isNullOrEmpty(event.getEventLog())) {
      setEventLog(event.getEventLog());
    }
    if (event.getLevel() != null) {
      setLevel(event.getLevel());
    }
    if (!Strings.isNullOrEmpty(event.getDescription())) {
      setDescription(event.getDescription());
    }
    if (!Strings.isNullOrEmpty(event.getOrigin())) {
      setOrigin(event.getOrigin());
    }
    if (event.getEventDate() != null) {
      setEventDate(event.getEventDate());
    }
    if (event.getQuantity() != null) {
      setQuantity(event.getQuantity());
    }
  }

}