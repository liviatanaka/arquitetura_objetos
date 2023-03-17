package com.entregas.rede.mensagem;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MensagemTexto extends Mensagem {
    private String titulo;
}