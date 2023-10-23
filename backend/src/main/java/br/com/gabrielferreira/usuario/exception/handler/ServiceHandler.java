package br.com.gabrielferreira.usuario.exception.handler;
import br.com.gabrielferreira.usuario.exception.MsgException;
import br.com.gabrielferreira.usuario.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuario.exception.model.ErroFormulario;
import br.com.gabrielferreira.usuario.exception.model.ErroPadrao;
import br.com.gabrielferreira.usuario.exception.model.ErroValidacaoCampos;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.ZonedDateTime;

import static br.com.gabrielferreira.usuario.utils.DataUtils.*;

@ControllerAdvice
public class ServiceHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadrao> validacaoException(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErroValidacaoCampos erroValidacaoCampos = new ErroValidacaoCampos();
        erroValidacaoCampos.setMomento(ZonedDateTime.now(FUSO_HORARIO_PADRAO_SISTEMA));
        erroValidacaoCampos.setStatus(httpStatus.value());
        erroValidacaoCampos.setErro("Erro validação de campos");
        erroValidacaoCampos.setMensagem("Ocorreu um erro de validação nos campos");
        erroValidacaoCampos.setCaminhoUrl(request.getRequestURI());

        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            ErroFormulario erroFormulario = ErroFormulario.builder()
                    .campo(fieldError.getField())
                    .mensagem(fieldError.getDefaultMessage())
                    .build();

            erroValidacaoCampos.addErrosFormularios(erroFormulario);
        });

        return ResponseEntity.status(httpStatus).body(erroValidacaoCampos);
    }

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<ErroPadrao> naoEncontratoException(NaoEncontradoException e, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ErroPadrao erroPadrao = gerarErroPadrao(httpStatus, ZonedDateTime.now(FUSO_HORARIO_PADRAO_SISTEMA), "Não encontrado", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(erroPadrao);
    }

    @ExceptionHandler(MsgException.class)
    public ResponseEntity<ErroPadrao> msgException(MsgException e, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErroPadrao erroPadrao = gerarErroPadrao(httpStatus, ZonedDateTime.now(FUSO_HORARIO_PADRAO_SISTEMA), "Erro personalizado", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(erroPadrao);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroPadrao> exception(Exception e, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErroPadrao erroPadrao = gerarErroPadrao(httpStatus, ZonedDateTime.now(FUSO_HORARIO_PADRAO_SISTEMA), e.getMessage(), "Erro, tente mais tarde", request.getRequestURI());
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
