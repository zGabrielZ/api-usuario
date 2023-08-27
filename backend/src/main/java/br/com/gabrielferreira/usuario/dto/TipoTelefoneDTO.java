package br.com.gabrielferreira.usuario.dto;

import java.time.ZonedDateTime;

public record TipoTelefoneDTO(Long id, String descricao, String codigo, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
}