package com.example.blogpessoal.Repository;

import com.example.blogpessoal.Model.TemaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TemaRepositoryTests extends JpaRepository<TemaModel,Long> {

    public List<TemaModel> findAllByDescricaoContainingIgnoreCase (@Param("descricao") String descricao);
}
