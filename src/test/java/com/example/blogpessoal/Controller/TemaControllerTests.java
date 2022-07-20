package com.example.blogpessoal.Controller;

import com.example.blogpessoal.Model.TemaModel;
import com.example.blogpessoal.Repository.TemaRepositoryTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tema")
@CrossOrigin(origins= "*",allowedHeaders = "*")
public class TemaControllerTests {
/*
    @Autowired
    TemaRepositoryTests temaRepositoryTests;

    @GetMapping
    public ResponseEntity<List<TemaModel>> getALL(TemaModel tema){
        return ResponseEntity.ok(temaRepositoryTests.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemaModel> getById(@PathVariable Long id){
        return temaRepositoryTests.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping
    public ResponseEntity<TemaModel> putTema(@Valid @RequestBody TemaModel tema){
        if(tema.getId() == null)
            return ResponseEntity.notFound().build();
        return temaRepositoryTests.findById(tema.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(temaRepositoryTests.save(tema)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping
    public ResponseEntity<TemaModel> postTema(@Valid @RequestBody TemaModel tema){
        return ResponseEntity.status(HttpStatus.CREATED).body(temaRepositoryTests.save(tema));
    }

    @DeleteMapping
    public ResponseEntity<TemaModel> delTema(@PathVariable Long id){
        if(temaRepositoryTests.existsById(id)){
            temaRepositoryTests.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


*/



}
