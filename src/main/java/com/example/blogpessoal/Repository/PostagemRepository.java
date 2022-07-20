package com.example.blogpessoal.Repository;

import com.example.blogpessoal.Model.PostagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostagemRepository extends JpaRepository<PostagemModel,Long> {

    public List<PostagemModel> findAllByTituloContainingIgnoreCase (@Param("titulo") String titulo);

}
