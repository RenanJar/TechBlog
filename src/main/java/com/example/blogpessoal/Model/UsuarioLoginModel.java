package com.example.blogpessoal.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioLoginModel {

    private Long id;
    private String nome;
    private String usuario;
    private String senha;
    private String foto;
    private String token;

    public UsuarioLoginModel(Long id, String nome, String usuario, String senha, String foto, String token) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.foto = foto;
        this.token = token;
    }

    public UsuarioLoginModel(String usuario,String senha){
        this.usuario = usuario;
        this.senha= senha;
    }

    public UsuarioLoginModel(){}
}
