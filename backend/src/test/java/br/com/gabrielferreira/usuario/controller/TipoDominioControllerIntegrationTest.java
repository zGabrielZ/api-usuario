package br.com.gabrielferreira.usuario.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TipoDominioControllerIntegrationTest {

    private static final String URL = "/tipo-dominios";
    private static final MediaType MEDIA_TYPE_JSON = MediaType.APPLICATION_JSON;

    @Autowired
    protected MockMvc mockMvc;

    private Long idTipoDominioExistente;

    private Long idTipoDominioInexistente;

    @BeforeEach
    void setUp(){
        idTipoDominioExistente = 1L;
        idTipoDominioInexistente = -1L;
    }

    @Test
    @DisplayName("Deve buscar tipo dominios")
    @Order(1)
    void deveBuscarTipoDominios() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve buscar tipo dominio quando existir")
    @Order(2)
    void deveBuscarTipoDominio() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/{id}"), idTipoDominioExistente)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.descricao").exists());
        resultActions.andExpect(jsonPath("$.codigo").exists());
    }

    @Test
    @DisplayName("Não deve buscar tipo dominio quando não existir")
    @Order(3)
    void naoDeveBuscarTipoDominio() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/{id}"), idTipoDominioInexistente)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.mensagem").value("Tipo Domínio não encontrado"));
    }

    @Test
    @DisplayName("Deve buscar tipo dominio quando informar o código")
    @Order(4)
    void deveBuscarTipoDominioQuandoInformarCodigo() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/buscar?codigo=GENERO"))
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.descricao").exists());
        resultActions.andExpect(jsonPath("$.codigo").exists());
    }

    @Test
    @DisplayName("Não deve buscar tipo dominio quando não informar o código")
    @Order(5)
    void naoDeveBuscarTipoDominioQuandoNaoInformarCodigo() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/buscar?codigo="))
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.mensagem").value("É necessário informar o código"));
    }

    @Test
    @DisplayName("Não deve buscar tipo dominio quando informar o código inválido")
    @Order(6)
    void naoDeveBuscarTipoDominioQuandoInformarCodigoInvalido() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/buscar?codigo=invalido"))
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.mensagem").value("Tipo Domínio não encontrado"));
    }
}
