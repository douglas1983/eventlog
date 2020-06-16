package com.codenation.desafio.desafio.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
// @Parameter(in = ParameterIn.QUERY, description = "Log de eventos ", name =
// "eventlog", content = @Content(schema = @Schema(type = "string")))
@Parameter(in = ParameterIn.QUERY, description = "Level do Erro (Error, Warning, Info )", name = "level", content = @Content(schema = @Schema(type = "string")))
@Parameter(in = ParameterIn.QUERY, description = "Descriçao do Erro", name = "description", content = @Content(schema = @Schema(type = "string")))
@Parameter(in = ParameterIn.QUERY, description = "Data do Evento", name = "eventDate", content = @Content(schema = @Schema(type = "string", format = "date-time")))
@Parameter(in = ParameterIn.QUERY, description = "Quantidade de vezes que o erro aconteceu", name = "quantity", content = @Content(schema = @Schema(type = "integer")))

public @interface PredicateAsQueryParamEventLog {

}