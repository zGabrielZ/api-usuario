package br.com.gabrielferreira.usuario.domain;

import lombok.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static br.com.gabrielferreira.usuario.utils.MascaraUtils.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UsuarioDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = 4819830058841518744L;

    @EqualsAndHashCode.Include
    private Long id;

    private String nome;

    private String email;

    private String cpf;

    private BigDecimal renda;

    private LocalDate dataNascimento;

    private Integer quantidadeFilhos;

    private TelefoneDomain telefone;

    private DominioDomain genero;

    private List<AnotacaoDomain> anotacoes = new ArrayList<>();

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    public String getCpfFormatado(){
        return toCpfFormatado(this.cpf);
    }

    public String getRendaFormatada(){
        if(this.renda != null){
            return toValorMonetarioBrasil(this.renda);
        }
        return null;
    }
}
