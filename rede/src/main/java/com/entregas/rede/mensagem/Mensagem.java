package com.entregas.rede.mensagem;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.entregas.rede.usuario.Usuario;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String texto;

    private String identifier;
    private String usuario_idt;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}