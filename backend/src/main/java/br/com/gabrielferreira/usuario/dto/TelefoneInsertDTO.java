package br.com.gabrielferreira.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneInsertDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -918570165334510853L;

    private String numero;

    private String ddd;

    private String descricao;

    private TipoTelefoneInsertDTO tipoTelefone;
}
