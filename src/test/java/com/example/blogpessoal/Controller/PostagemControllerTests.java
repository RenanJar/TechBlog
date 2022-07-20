package com.example.blogpessoal.Controller;

import com.example.blogpessoal.Model.PostagemModel;
import com.example.blogpessoal.Repository.PostagemRepositoryTests;
import com.example.blogpessoal.Repository.TemaRepositoryTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins= "*",allowedHeaders = "*")
public class PostagemControllerTests {
/*
    @Autowired
    private PostagemRepositoryTests postagemRepositoryTests;

    @Autowired
    private TemaRepositoryTests temaRepositoryTests;

    @GetMapping
    public ResponseEntity<List<PostagemModel>> getAll(){
        return ResponseEntity.ok(postagemRepositoryTests.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostagemModel> getById(@PathVariable Long id){
        return postagemRepositoryTests.findById(id)
                .map(resposta ->ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<PostagemModel>>getByTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(postagemRepositoryTests.findAllByTituloContainingIgnoreCase(titulo));
    }

    @PostMapping
    public ResponseEntity<PostagemModel>postPostagem(@Valid @RequestBody PostagemModel postagem){
        if(postagem.getTema().getId()==null || !temaRepositoryTests.existsById(postagem.getTema().getId())){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepositoryTests.save(postagem));
    }

    @PutMapping
    public ResponseEntity<PostagemModel>putPostagem(@Valid @RequestBody PostagemModel postagem){
           if(postagem.getId() ==null ||
                   postagem.getTema().getId()==null ||
                   !temaRepositoryTests.existsById(postagem.getTema().getId())){
               return ResponseEntity.badRequest().build();
           }

       return postagemRepositoryTests.findById(postagem.getId())
                   .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(postagemRepositoryTests.save(postagem)))
                   .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PostagemModel> delPostagem(@PathVariable Long id){
        if(postagemRepositoryTests.existsById(id)){
            postagemRepositoryTests.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
 */


}
