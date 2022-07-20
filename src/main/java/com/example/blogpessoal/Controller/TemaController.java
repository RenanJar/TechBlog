package com.example.blogpessoal.Controller;

import com.example.blogpessoal.Model.PostagemModel;
import com.example.blogpessoal.Model.TemaModel;
import com.example.blogpessoal.Repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tema")
@CrossOrigin(origins= "*",allowedHeaders = "*")
public class TemaController {

    @Autowired
    TemaRepository temaRepository;

    @GetMapping
    public ResponseEntity<List<TemaModel>> getALL(TemaModel tema){
        return ResponseEntity.ok(temaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemaModel> getById(@PathVariable Long id){
        return temaRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<TemaModel>>getByTitulo(@PathVariable String descricao){
        return ResponseEntity.ok(temaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
    }

    @PutMapping
    public ResponseEntity<TemaModel> putTema(@Valid @RequestBody TemaModel tema){
        if(tema.getId() == null)
            return ResponseEntity.notFound().build();
        return temaRepository.findById(tema.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(temaRepository.save(tema)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping
    public ResponseEntity<TemaModel> postTema(@Valid @RequestBody TemaModel tema){
        return ResponseEntity.status(HttpStatus.CREATED).body(temaRepository.save(tema));
    }

    @DeleteMapping
    public ResponseEntity<TemaModel> delTema(@PathVariable Long id){
        if(temaRepository.existsById(id)){
            temaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }





















}
