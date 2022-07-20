package com.example.blogpessoal.Controller;

import com.example.blogpessoal.Model.UsuarioLoginModel;
import com.example.blogpessoal.Model.UsuarioModel;
import com.example.blogpessoal.Repository.UsuarioRepository;
import com.example.blogpessoal.Repository.UsuarioRepositoryTests;
import com.example.blogpessoal.Service.UsuarioService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioControllerTests {

        @Autowired
        private TestRestTemplate testRestTemplate;

        @Autowired
        private UsuarioService usuarioService;

        @Autowired
        private UsuarioRepository usuarioRepository;

        @BeforeAll
        void start(){

            usuarioRepository.deleteAll();
        }

        @Test
        @Order(1)
        @DisplayName("Cadastrar Um Usuário")
        public void deveCriarUmUsuario() {

            HttpEntity<UsuarioModel> requisicao = new HttpEntity<UsuarioModel>(new UsuarioModel(0L,
                    "Paulo Antunes", "paulo_antunes@email.com.br", "13465278", "https://i.imgur.com/JR7kUFU.jpg"));

            ResponseEntity<UsuarioModel> resposta = testRestTemplate
                    .exchange("/usuarios/cadastrar", HttpMethod.POST, requisicao, UsuarioModel.class);

            assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
            assertEquals(requisicao.getBody().getNome(), resposta.getBody().getNome());
            assertEquals(requisicao.getBody().getUsuario(), resposta.getBody().getUsuario());
        }

        @Test
        @Order(2)
        @DisplayName("Não deve permitir duplicação do Usuário")
        public void naoDeveDuplicarUsuario() {

            usuarioService.cadastrarUsuario(new UsuarioModel(0L,
                    "Maria da Silva", "maria_silva@email.com.br", "13465278", "https://i.imgur.com/T12NIp9.jpg"));

            HttpEntity<UsuarioModel> requisicao = new HttpEntity<UsuarioModel>(new UsuarioModel(0L,
                    "Maria da Silva", "maria_silva@email.com.br", "13465278", "https://i.imgur.com/T12NIp9.jpg"));

            ResponseEntity<UsuarioModel> resposta = testRestTemplate
                    .exchange("/usuarios/cadastrar", HttpMethod.POST, requisicao, UsuarioModel.class);

            assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        }

        @Test
        @Order(3)
        @DisplayName("Alterar um Usuário")
        public void deveAtualizarUmUsuario() {

            Optional<UsuarioModel> usuarioCreate = usuarioService.cadastrarUsuario(new UsuarioModel(0L,
                    "Juliana Andrews", "juliana_andrews@email.com.br",
                    "juliana123", "https://i.imgur.com/yDRVeK7.jpg"));

            UsuarioModel usuarioUpdate = new UsuarioModel(usuarioCreate.get().getId(),
                    "Juliana Andrews Ramos", "juliana_ramos@email.com.br",
                    "juliana123", "https://i.imgur.com/yDRVeK7.jpg");

            HttpEntity<UsuarioModel> requisicao = new HttpEntity<UsuarioModel>(usuarioUpdate);

            ResponseEntity<UsuarioModel> resposta = testRestTemplate
                    .withBasicAuth("root", "root")
                    .exchange("/usuarios/atualizar", HttpMethod.PUT, requisicao, UsuarioModel.class);

            assertEquals(HttpStatus.OK, resposta.getStatusCode());
            assertEquals(usuarioUpdate.getNome(), resposta.getBody().getNome());
            assertEquals(usuarioUpdate.getUsuario(), resposta.getBody().getUsuario());
        }

        @Test
        @Order(4)
        @DisplayName("Listar todos os Usuários")
        public void deveMostrarTodosUsuarios() {

            usuarioService.cadastrarUsuario(new UsuarioModel(0L,
                    "Sabrina Sanches", "sabrina_sanches@email.com.br",
                    "sabrina123", "https://i.imgur.com/5M2p5Wb.jpg"));

            usuarioService.cadastrarUsuario(new UsuarioModel(0L,
                    "Ricardo Marques", "ricardo_marques@email.com.br",
                    "ricardo123", "https://i.imgur.com/Sk5SjWE.jpg"));

            ResponseEntity<String> resposta = testRestTemplate
                    .withBasicAuth("root", "root")
                    .exchange("/usuarios/all", HttpMethod.GET, null, String.class);

            assertEquals(HttpStatus.OK, resposta.getStatusCode());
        }

        @Test
        @Order(5)
        @DisplayName("Logar usuario")
        public void develogarusuario(){
            HttpEntity<UsuarioLoginModel> requisicao =
                    new HttpEntity<UsuarioLoginModel>(new UsuarioLoginModel("ricardo_marques@email.com.br",
                            "ricardo123"));

            ResponseEntity<String> response= testRestTemplate
                    .withBasicAuth(requisicao.getBody().getUsuario(),requisicao.getBody().getSenha())
                            .exchange("/usuarios/logar",HttpMethod.POST,requisicao,String.class);

             assertEquals(HttpStatus.OK,response.getStatusCode());
        }


        @Test
        @Order(6)
        @DisplayName("trazer usuario por ID")
        public void findId(){

            HttpEntity<UsuarioModel> requisicao =
                    new HttpEntity<UsuarioModel>(new UsuarioModel("ricardo_marques@email.com.br",
                            "ricardo123"));

            ResponseEntity<String> response= testRestTemplate
                    .withBasicAuth(requisicao.getBody().getUsuario(),requisicao.getBody().getSenha())
                    .exchange("/usuarios/1",HttpMethod.GET,null,String.class);

            assertEquals(HttpStatus.OK,response.getStatusCode());
        }



    }

