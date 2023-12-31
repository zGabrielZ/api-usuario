package br.com.gabrielferreira.usuario.controller;

import br.com.gabrielferreira.usuario.dto.request.UsuarioCreateRequestDTO;
import br.com.gabrielferreira.usuario.dto.request.UsuarioUpdateRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

import static br.com.gabrielferreira.usuario.tests.Factory.*;
import static br.com.gabrielferreira.usuario.tests.Factory.criarUsuarioInsert;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsuarioControllerIntegrationTest {

    private static final String URL = "/usuarios";
    private static final MediaType MEDIA_TYPE_JSON = MediaType.APPLICATION_JSON;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    private Long idUsuarioExistente;

    private Long idUsuarioInexistente;

    private UsuarioCreateRequestDTO usuarioCreateRequestDTO;

    private UsuarioUpdateRequestDTO usuarioUpdateRequestDTO;

    @BeforeEach
    void setUp(){
        idUsuarioExistente = 1L;
        idUsuarioInexistente = -1L;
        usuarioCreateRequestDTO = criarUsuarioInsert();
        usuarioUpdateRequestDTO = criarUsuarioUpdate();
    }

    @Test
    @DisplayName("Deve cadastrar um usuário")
    @Order(1)
    void deveCadastrarUsuario() throws Exception{
        String jsonBody = objectMapper.writeValueAsString(usuarioCreateRequestDTO);

        String nomeEsperado = usuarioCreateRequestDTO.getNome();
        String emailEsperado = usuarioCreateRequestDTO.getEmail();

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.nome").value(nomeEsperado));
        resultActions.andExpect(jsonPath("$.email").value(emailEsperado));
        resultActions.andExpect(jsonPath("$.telefone.id").exists());
        resultActions.andExpect(jsonPath("$.telefone.tipoTelefone.id").exists());
        resultActions.andExpect(jsonPath("$.genero.id").exists());
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Deve buscar usuário quando existir")
    @Order(2)
    void deveBuscarUsuario() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/{id}"), idUsuarioExistente)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.nome").exists());
        resultActions.andExpect(jsonPath("$.email").exists());
        resultActions.andExpect(jsonPath("$.telefone.id").exists());
        resultActions.andExpect(jsonPath("$.telefone.tipoTelefone.id").exists());
        resultActions.andExpect(jsonPath("$.genero.id").exists());
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Não deve buscar usuário quando não existir")
    @Order(3)
    void naoDeveBuscarUsuario() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/{id}"), idUsuarioInexistente)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.mensagem").value("Usuário não encontrado"));
    }

    @Test
    @DisplayName("Deve alterar usuário quando existir")
    @Order(4)
    void deveAlterarUsuario() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(usuarioUpdateRequestDTO);

        Long idEsperado = idUsuarioExistente;
        String nomeEsperado = usuarioUpdateRequestDTO.getNome();
        BigDecimal rendaEsperado = usuarioUpdateRequestDTO.getRenda();

        ResultActions resultActions = mockMvc
                .perform(put(URL.concat("/{id}"), idUsuarioExistente)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").value(idEsperado));
        resultActions.andExpect(jsonPath("$.nome").value(nomeEsperado));
        resultActions.andExpect(jsonPath("$.renda").value(rendaEsperado));
        resultActions.andExpect(jsonPath("$.telefone.id").exists());
        resultActions.andExpect(jsonPath("$.telefone.tipoTelefone.id").exists());
        resultActions.andExpect(jsonPath("$.genero.id").exists());
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Não deve alterar usuário quando não existir")
    @Order(5)
    void naoDeveAlterarUsuario() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(usuarioUpdateRequestDTO);

        ResultActions resultActions = mockMvc
                .perform(put(URL.concat("/{id}"), idUsuarioInexistente)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.mensagem").value("Usuário não encontrado"));
    }

    @Test
    @DisplayName("Deve deletar usuário quando existir")
    @Order(6)
    void deveDeletarUsuario() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(delete(URL.concat("/{id}"), idUsuarioExistente)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Não deve deletar usuário quando não existir")
    @Order(7)
    void naoDeveDeletarUsuario() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(delete(URL.concat("/{id}"), idUsuarioInexistente)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.mensagem").value("Usuário não encontrado"));
    }


    @Test
    @DisplayName("Deve buscar usuários paginados quando existir")
    @Order(8)
    void deveBuscarUsuariosPaginados() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("?page=0&size=5&sort=id,desc"))
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.content").exists());
    }

    @Test
    @DisplayName("Não deve buscar usuários paginados quando informar propriedade incorreta")
    @Order(9)
    void naoDeveBuscarUsuariosPaginados() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("?page=0&size=5&sort=nomeee,desc"))
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.mensagem").value("A propriedade informada 'nomeee' não existe"));
    }

    @Test
    @DisplayName("Deve buscar usuários paginados quando existir e com filtros")
    @Order(10)
    void deveBuscarUsuariosPaginadosComFiltros() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("?page=0&size=5&sort=id,desc&sort=cpfFormatado,desc"))
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.content").exists());
    }

    @Test
    @DisplayName("Não deve cadastrar um usuário quando não informar campos")
    @Order(11)
    void naoDeveCadastrarUsuario() throws Exception{
        usuarioCreateRequestDTO = criarUsuarioInsertVazio();
        String jsonBody = objectMapper.writeValueAsString(usuarioCreateRequestDTO);

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.erro").value("Erro validação de campos"));
        resultActions.andExpect(jsonPath("$.mensagem").value("Ocorreu um erro de validação nos campos"));
        resultActions.andExpect(jsonPath("$.erroFormularios").exists());
    }

    @Test
    @DisplayName("Não deve cadastrar um usuário quando informar e-mail existente")
    @Order(12)
    void naoDeveCadastrarUsuarioQuandoInformarEmailExistente() throws Exception{
        usuarioCreateRequestDTO.setEmail("lucas-assuncao91@arteche.com.br");
        String jsonBody = objectMapper.writeValueAsString(usuarioCreateRequestDTO);

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.erro").value("Erro personalizado"));
        resultActions.andExpect(jsonPath("$.mensagem").value("Não vai ser possível cadastrar este usuário pois o e-mail 'lucas-assuncao91@arteche.com.br' já foi cadastrado"));
    }

    @Test
    @DisplayName("Não deve cadastrar um usuário quando informar cpf existente")
    @Order(13)
    void naoDeveCadastrarUsuarioQuandoInformarCpfExistente() throws Exception{
        usuarioCreateRequestDTO.setCpf("19827537733");
        String jsonBody = objectMapper.writeValueAsString(usuarioCreateRequestDTO);

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.erro").value("Erro personalizado"));
        resultActions.andExpect(jsonPath("$.mensagem").value("Não vai ser possível cadastrar este usuário pois o CPF '198.275.377-33' já foi cadastrado"));
    }

    @Test
    @DisplayName("Não deve cadastrar um usuário quando informar gênero inexistente")
    @Order(14)
    void naoDeveCadastrarUsuarioQuandoInformarGeneroInexistente() throws Exception{
        usuarioCreateRequestDTO.getGenero().setId(-1L);
        String jsonBody = objectMapper.writeValueAsString(usuarioCreateRequestDTO);

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.erro").value("Não encontrado"));
        resultActions.andExpect(jsonPath("$.mensagem").value("Gênero não encontrado"));
    }

    @Test
    @DisplayName("Não deve cadastrar um usuário quando informar tipo de telefone inexistente")
    @Order(15)
    void naoDeveCadastrarUsuarioQuandoInformarTipoTelefoneInexistente() throws Exception{
        usuarioCreateRequestDTO.getTelefone().getTipoTelefone().setId(-1L);
        String jsonBody = objectMapper.writeValueAsString(usuarioCreateRequestDTO);

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.erro").value("Não encontrado"));
        resultActions.andExpect(jsonPath("$.mensagem").value("Tipo de telefone não encontrado"));
    }

    @Test
    @DisplayName("Não deve cadastrar um usuário quando informar telefone com 8 caracteres e tipo de telefone celular")
    @Order(16)
    void naoDeveCadastrarUsuarioQuandoInformarTelefoneComOitoDigitoTipoCelular() throws Exception{
        usuarioCreateRequestDTO.getTelefone().setDdd("99");
        usuarioCreateRequestDTO.getTelefone().setNumero("99999999");
        usuarioCreateRequestDTO.getTelefone().getTipoTelefone().setId(5L);
        String jsonBody = objectMapper.writeValueAsString(usuarioCreateRequestDTO);

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.erro").value("Erro personalizado"));
        resultActions.andExpect(jsonPath("$.mensagem").value("O número do telefone '(99) 9999-9999' tem ser do tipo residencial"));
    }

    @Test
    @DisplayName("Não deve cadastrar um usuário quando informar telefone com 9 caracteres e tipo de telefone residencial")
    @Order(17)
    void naoDeveCadastrarUsuarioQuandoInformarTelefoneComNoveDigitoTipoResidencial() throws Exception{
        usuarioCreateRequestDTO.getTelefone().setDdd("99");
        usuarioCreateRequestDTO.getTelefone().setNumero("999999999");
        usuarioCreateRequestDTO.getTelefone().getTipoTelefone().setId(4L);
        String jsonBody = objectMapper.writeValueAsString(usuarioCreateRequestDTO);

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.erro").value("Erro personalizado"));
        resultActions.andExpect(jsonPath("$.mensagem").value("O número de telefone '(99) 99999-9999' tem ser do tipo celular"));
    }

}
