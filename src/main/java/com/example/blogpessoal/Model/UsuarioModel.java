package com.example.blogpessoal.Model;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_usuarios")
public class UsuarioModel {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotNull(message = "O atributo Nome é Obrigatório!")
        private String nome;
        @Schema(example = "email@email.com.br")
        @NotNull(message = "O atributo Usuário é Obrigatório!")
        @Email(message = "O atributo Usuário deve ser um email válido!")
        private String usuario;

        @NotBlank(message = "O atributo Senha é Obrigatório!")
        @Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres")
        private String senha;

        private String foto;

        @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
        @JsonIgnoreProperties("usuario")
        private List<PostagemModel> postagem;

        public UsuarioModel(){}

        public UsuarioModel(Long id, String nome, String usuario, String senha, String foto) {
                this.id = id;
                this.nome = nome;
                this.usuario = usuario;
                this.senha = senha;
                this.foto = foto;
        }

        public UsuarioModel(String usuario,String senha){
                this.usuario = usuario;
                this.senha= senha;
        }
}
