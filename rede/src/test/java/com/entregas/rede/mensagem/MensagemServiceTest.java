package com.entregas.rede.mensagem;

import com.entregas.rede.usuario.Usuario;
import com.entregas.rede.usuario.UsuarioService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class MensagemServiceTest {

    @InjectMocks
    MensagemService mensagemService;

    @Mock
    UsuarioService usuarioService;

    @Mock
    MensagemRepository mensagemRepository;

    @Test
    void test_listaMensagens(){
        Mensagem msg = new Mensagem();
        msg.setIdentifier("msg1");
        msg.setTexto("oi");
        msg.setId(1);

        List<Mensagem> lista = new ArrayList<>();
        lista.add(msg);

        Mockito.when(mensagemRepository.findAll()).thenReturn(lista);

        List<Mensagem> resp = mensagemService.listaMensagens();

        Assertions.assertEquals(1, resp.size());
        Assertions.assertEquals(1, resp.get(0).getId());
        Assertions.assertEquals("msg1", resp.get(0).getIdentifier());

    }

    @Test
    void test_getMensagemIdentifier(){
        Mensagem msg = new Mensagem();
        msg.setIdentifier("msg1");
        msg.setTexto("oi");
        msg.setId(1);

        Mockito.when(mensagemRepository.findByIdentifier("msg1")).thenReturn(msg);

        Mensagem resp = mensagemService.getMensagem("msg1");

        Assertions.assertEquals("oi", resp.getTexto());
    }

    @Test
    void test_getMensagemPorUsuario(){
        Usuario usuario = new Usuario();
        usuario.setIdentifier("user1");
        usuario.setId(1);
        usuario.setIdade(19);

        Mensagem mensagem = new Mensagem();
        mensagem.setTexto("oi");
        mensagem.setUsuario(usuario);
        mensagem.setId(1);
        mensagem.setIdentifier("msg-1");
        mensagem.setUsuario_idt("user1");

        List<Mensagem> mensagens = new ArrayList<>();
        mensagens.add(mensagem);

        Mockito.when(usuarioService.buscarUsuarioPorIdentifier("user1")).thenReturn(usuario);
        Mockito.when(mensagemRepository.findByUsuario(usuario)).thenReturn(mensagens);

        List<Mensagem> resp = mensagemService.getMensagensPorUsuario("user1");

        Assertions.assertEquals(1, resp.size());
        Assertions.assertEquals(usuario, resp.get(0).getUsuario());
        Assertions.assertEquals("user1", resp.get(0).getUsuario_idt());

    }

    @Test
    void test_salvarMensagemTexto(){
        Usuario usuario = new Usuario();
        usuario.setIdentifier("user1");
        usuario.setId(1);
        usuario.setIdade(19);

        MensagemTexto mensagem = new MensagemTexto();
        mensagem.setTexto("oi");
        mensagem.setUsuario(usuario);
        mensagem.setId(1);
        mensagem.setUsuario_idt("user1");
        mensagem.setTitulo("magica");


        Mockito.when(usuarioService.buscarUsuarioPorIdentifier("user1")).thenReturn(usuario);
        Mockito.when(mensagemRepository.save(mensagem)).thenReturn(mensagem);

        MensagemTexto resp = mensagemService.salvarMensagemTexto(mensagem);

        Assertions.assertEquals(resp.getTitulo(), "magica");
        Assertions.assertEquals(resp.getTexto(), "oi");
        Assertions.assertEquals(resp.getUsuario(), usuario);
    }

    @Test
    void test_salvarMensagemArquivo(){
        Usuario usuario = new Usuario();
        usuario.setIdentifier("user1");
        usuario.setId(1);
        usuario.setIdade(19);

        MensagemArquivo mensagem = new MensagemArquivo();
        mensagem.setTexto("oi");
        mensagem.setUsuario(usuario);
        mensagem.setId(1);
        mensagem.setUsuario_idt("user1");
        mensagem.setUrl("http");


        Mockito.when(usuarioService.buscarUsuarioPorIdentifier("user1")).thenReturn(usuario);
        Mockito.when(mensagemRepository.save(mensagem)).thenReturn(mensagem);

        MensagemArquivo resp = mensagemService.salvarMensagemArquivo(mensagem);

        Assertions.assertEquals(resp.getUrl(), "http");
        Assertions.assertEquals(resp.getTexto(), "oi");
        Assertions.assertEquals(resp.getUsuario(), usuario);

    }
}
