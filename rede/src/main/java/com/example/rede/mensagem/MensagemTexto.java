package com.example.rede.mensagem;

import com.example.rede.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensagemTexto extends Mensagem{
    private String texto;

    public MensagemTexto(String texto, String id_usuario){
        super(id_usuario, texto);
        this.texto = texto;

    }

}
