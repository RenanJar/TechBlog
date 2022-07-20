package com.example.blogpessoal.Repository;

import com.example.blogpessoal.Model.PostagemModel;
import com.example.blogpessoal.Model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel,Long> {

    public Optional<UsuarioModel> findByUsuario(String usuario);

    public List<UsuarioModel> findAllByUsuarioContainingIgnoreCase (@Param("usuario") String usuario);

    public List<UsuarioModel> findAllByNomeContainingIgnoreCase(@Param("nome")String nome);
}
