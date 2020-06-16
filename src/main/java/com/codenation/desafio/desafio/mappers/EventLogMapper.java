package com.codenation.desafio.desafio.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

import com.codenation.desafio.desafio.dto.EventLogDTO;
import com.codenation.desafio.desafio.dto.EventLogUpdateDTO;
import com.codenation.desafio.desafio.entity.EventLog;

@Mapper(componentModel = "spring")
public interface EventLogMapper {

    @Mappings({ @Mapping(source = "level", target = "level") })

    EventLogDTO map(EventLog eventlog);

    EventLog map(EventLogUpdateDTO eventlog);

    // List<EventLog> map(List<EventLogUpdateDTO> eventlogs);

    List<EventLogDTO> map(List<EventLog> eventlogs);

}
