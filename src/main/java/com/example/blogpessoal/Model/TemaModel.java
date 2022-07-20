package com.example.blogpessoal.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_tema")
public class TemaModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message= "O atributo Titulo é obrigatoio e não pode utilizar espaços em branco!")
    @Size(min = 3, max = 30)
    private String descricao;

    @OneToMany(mappedBy = "tema",cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("tema")
    private List<PostagemModel> postagem;

}
