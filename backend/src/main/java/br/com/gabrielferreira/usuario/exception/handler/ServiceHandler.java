package br.com.gabrielferreira.usuario.exception.handler;
import br.com.gabrielferreira.usuario.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuario.exception.model.ErroPadrao;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static br.com.gabrielferreira.usuario.utils.DataUtils.*;

@ControllerAdvice
public class ServiceHandler {

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<ErroPadrao> naoEncontratoException(NaoEncontradoException e, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ErroPadrao erroPadrao = gerarErroPadrao(httpStatus, ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO)), "Não encontrado", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(erroPadrao);
    }

    private ErroPadrao gerarErroPadrao(HttpStatus httpStatus, ZonedDateTime momento, String erro, String mensagem, String caminhoUrl){
        return ErroPadrao.builder()
                .momento(momento)
                .status(httpStatus.value())
                .erro(erro)
                .mensagem(mensagem)
                .caminhoUrl(caminhoUrl)
                .build();
    }
}