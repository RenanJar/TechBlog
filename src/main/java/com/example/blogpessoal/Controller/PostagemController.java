package com.example.blogpessoal.Controller;

import com.example.blogpessoal.Model.PostagemModel;
import com.example.blogpessoal.Repository.PostagemRepository;
import com.example.blogpessoal.Repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins= "*",allowedHeaders = "*")
public class PostagemController {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private TemaRepository temaRepository;

    @GetMapping
    public ResponseEntity<List<PostagemModel>> getAll(){
        return ResponseEntity.ok(postagemRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostagemModel> getById(@PathVariable Long id){
        return postagemRepository.findById(id)
                .map(resposta ->ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<PostagemModel>>getByTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
    }

    @PostMapping
    public ResponseEntity<PostagemModel>postPostagem(@Valid @RequestBody PostagemModel postagem){
        if(postagem.getTema().getId()==null || !temaRepository.existsById(postagem.getTema().getId())){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
    }

    @PutMapping
    public ResponseEntity<PostagemModel>putPostagem(@Valid @RequestBody PostagemModel postagem){
           if(postagem.getId() ==null ||
                   postagem.getTema().getId()==null ||
                   !temaRepository.existsById(postagem.getTema().getId())){
               return ResponseEntity.badRequest().build();
           }

       return postagemRepository.findById(postagem.getId())
                   .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem)))
                   .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PostagemModel> delPostagem(@PathVariable Long id){
        if(postagemRepository.existsById(id)){
            postagemRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
