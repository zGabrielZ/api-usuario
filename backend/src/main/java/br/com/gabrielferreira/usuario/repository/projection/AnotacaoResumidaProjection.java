package br.com.gabrielferreira.usuario.repository.projection;

import java.time.ZonedDateTime;

public interface AnotacaoResumidaProjection {

    Long getId();

    String getDescricao();

    ZonedDateTime getCreatedAt();

    ZonedDateTime getUpdatedAt();
}