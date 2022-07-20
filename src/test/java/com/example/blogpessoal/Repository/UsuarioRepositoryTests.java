package com.example.blogpessoal.Repository;

import com.example.blogpessoal.Model.UsuarioModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTests{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeAll
    public void start() {


        usuarioRepository.deleteAll();

        usuarioRepository.save(new UsuarioModel(0L, "João da Silva", "joao@email.com.br", "13465278",
                "https://i.imgur.com/FETvs2O.jpg"));

        usuarioRepository.save(new UsuarioModel(0L, "Manuela da Silva", "manuela@email.com.br", "13465278",
                "https://i.imgur.com/NtyGneo.jpg"));

        usuarioRepository.save(new UsuarioModel(0L, "Adriana da Silva", "adriana@email.com.br", "13465278",
                "https://i.imgur.com/mB3VM2N.jpg"));

        usuarioRepository.save(new UsuarioModel(0L, "Paulo Antunes", "paulo@email.com.br", "13465278",
                "https://i.imgur.com/JR7kUFU.jpg"));
    }

    @Test
    @DisplayName("🤴 Deve mostrar um usuário")
    public void deveRetornarUmUsuario() {

        Optional<UsuarioModel> usuario = usuarioRepository.findByUsuario("joao@email.com.br");
        assertTrue(usuario.get().getUsuario().equals("joao@email.com.br"));
    }

    @Test
    @DisplayName("Retorna 3 usuarios")
    public void deveRetornarTresUsuarios() {

        List<UsuarioModel> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
        assertEquals(3, listaDeUsuarios.size());
        assertTrue(listaDeUsuarios.get(0).getNome().equals("João da Silva"));
        assertTrue(listaDeUsuarios.get(1).getNome().equals("Manuela da Silva"));
        assertTrue(listaDeUsuarios.get(2).getNome().equals("Adriana da Silva"));

    }



}
