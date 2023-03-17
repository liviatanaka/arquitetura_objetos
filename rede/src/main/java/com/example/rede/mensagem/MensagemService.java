package com.example.rede.mensagem;

import com.example.rede.usuario.Usuario;
import com.example.rede.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

//        GET /mensagem -> Listar todas as mensagens
//        POST /mensagemText -> Criar uma mensagem, verificar se o usuário dono da mensagem existe.
//        POST /mensagemArquivo -> Criar uma mensagem, verificar se o usuário dono da mensagem existe.
//        GET /mensagem -> Listar todas as mensagens cadastradas
//        GET /mensagem/{mensagemId} -> Recuperar uma mensagem específica
//        GET /mensagem/{userId}/user -> Listar todas as mensagens de um usuário
@Service
public class MensagemService {

    private List<Mensagem> mensagens = new ArrayList<>();

    @Autowired
    private UsuarioService usuarioService;


    /* Listar todas as mensagens */
    public List<Mensagem> listarMensagens(){
        return mensagens;
    }


    /* Criar uma mensagem, verificar se o usuário dono da mensagem existe. */
    public MensagemTexto salvarMensagemTexto(MensagemTexto mensagem){
        Usuario autor = usuarioService.buscaUsuarioId(mensagem.getId_usuario());
        for (Usuario usuario : usuarioService.listarUsuarios()){
            if (autor.equals(usuario)){
                mensagem.setId(UUID.randomUUID().toString());
                mensagem.setUsuario(autor);
                mensagens.add(mensagem);
                return mensagem;
            }
        }
        return null;
    }

    /* Criar uma mensagem, verificar se o usuário dono da mensagem existe. */
    public MensagemArquivo salvarMensagemArquivo(MensagemArquivo mensagem){
        Usuario autor = usuarioService.buscaUsuarioId(mensagem.getId_usuario());
        for (Usuario usuario : usuarioService.listarUsuarios()){
            if (autor.equals(usuario)){
                mensagem.setId(UUID.randomUUID().toString());
                mensagem.setUsuario(autor);
                mensagens.add(mensagem);
                return mensagem;
            }
        }
        return null;
    }

    /* Recuperar uma mensagem específica */
    public Mensagem buscarMensagemId(String id){
        Optional<Mensagem> op = mensagens.stream().filter(m -> m.getId().equals(id)).findFirst();
        return op.get();
    }

    /* Listar todas as mensagens de um usuário */
    public List<Mensagem> buscarMensagemUsuarioId(String id_usuario){
        List<Mensagem> mensagensUsuario = new ArrayList<>();
        for (Mensagem mensagem : mensagens){
            if (mensagem.getId_usuario().equals(id_usuario)){
                mensagensUsuario.add(mensagem);
            }
        }
        return mensagensUsuario;
    }
}
