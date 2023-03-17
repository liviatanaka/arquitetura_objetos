package com.entregas.rede.mensagem;

import com.entregas.rede.usuario.Usuario;
import com.entregas.rede.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class MensagemService {

    @Autowired
    private MensagemRepository mensagemRepository;

    @Autowired
    private UsuarioService usuarioService;

    public List<Mensagem> listaMensagens(){
        return mensagemRepository.findAll();
    }

    public Mensagem getMensagem(String identifier) {
        return mensagemRepository.findByIdentifier(identifier);
    }

    public List<Mensagem> getMensagensPorUsuario(String userId) {
        Usuario usuario = usuarioService.buscarUsuarioPorIdentifier(userId);
        return mensagemRepository.findByUsuario(usuario);
    }

    public MensagemTexto salvarMensagemTexto(MensagemTexto mensagem) {
        Usuario usuario = usuarioService.buscarUsuarioPorIdentifier(mensagem.getUsuario_idt());

        mensagem.setIdentifier(UUID.randomUUID().toString());
        mensagem.setUsuario(usuario);

        return mensagemRepository.save(mensagem);
    }
    public MensagemArquivo salvarMensagemArquivo(MensagemArquivo mensagem) {
        Usuario usuario = usuarioService.buscarUsuarioPorIdentifier(mensagem.getUsuario_idt());

        mensagem.setIdentifier(UUID.randomUUID().toString());
        mensagem.setUsuario(usuario);

        return mensagemRepository.save(mensagem);
    }




}
