package com.example.rede.mensagem;

import com.example.rede.usuario.Usuario;

public class MensagemArquivo extends Mensagem{
    private String url_arquivo;

    public MensagemArquivo(String url_arquivo, String id_usuario){
        super(id_usuario, url_arquivo);
        this.url_arquivo = url_arquivo;

    }

}
