package com.example.blogpessoal.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name= "tb_postagens")
public class PostagemModel {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message= "O atributo Titulo é obrigatoio e não pode utilizar espaços em branco!")
        @Size(min = 5, max = 100,message = "O atributo Titulo deve conter no minimo 05 e no maximo 100")
        private String titulo;

        @NotNull(message= "O atributo texto é obrigatoio e não pode utilizar espaços em branco!")
        @Size(min = 10, max = 1000,message = "O atributo texto deve conter no minimo 10 e no maximo 1000")
        private String texto;

        @UpdateTimestamp
        private LocalDateTime data;

        @ManyToOne
        @JsonIgnoreProperties("postagem")
        private TemaModel tema;

        @ManyToOne
        @JsonIgnoreProperties("postagem")
        private UsuarioModel usuario;

}
