package com.entregas.rede.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import com.entregas.rede.mensagem.Mensagem;

@Getter
@Setter
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer idade;
    private String identifier;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Mensagem> mensagens;

}