package com.entregas.rede.usuario;

import com.entregas.rede.mensagem.Mensagem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTests {

    @InjectMocks
    UsuarioService usuarioService;

    @Mock
    UsuarioRepository usuarioRepository;

    @Test
    void test_listaUsuariosVazio(){
        Mockito.when(usuarioRepository.findAll()).thenReturn(new ArrayList<>());

        List<Usuario> resp = usuarioService.listaUsuarios();

        Assertions.assertEquals(0, resp.size());
    }

    @Test
    void test_listaUsuarios(){
        Usuario usuario = cria_user("user show", 1, 15);

        List<Usuario> lista_usuarios = new ArrayList<>();
        lista_usuarios.add(usuario);

        Mockito.when(usuarioRepository.findAll()).thenReturn(lista_usuarios);

        List<Usuario> resp = usuarioService.listaUsuarios();

        Assertions.assertEquals(1, resp.size());
        Assertions.assertEquals(1, resp.get(0).getId());
        Assertions.assertEquals("user show", resp.get(0).getIdentifier());
    }

    @Test
    void test_buscaUsuarioPorIdentifier(){
        Usuario usuario = cria_user("user show", 1, 15);

        Mockito.when(usuarioRepository.findByIdentifier("user show")).thenReturn(usuario);

        Usuario resp = usuarioService.buscarUsuarioPorIdentifier("user show");

        Assertions.assertEquals(15, resp.getIdade());
    }

    @Test
    void test_salvarUsuario(){
        Usuario usuario = cria_user("user show", 1, 15);

        Mensagem mensagem = new Mensagem();
        mensagem.setTexto("oi");
        mensagem.setUsuario(usuario);
        mensagem.setId(1);
        mensagem.setIdentifier("msg-1");
        mensagem.setUsuario_idt("user show");

        List<Mensagem> mensagens = new ArrayList<>();
        mensagens.add(mensagem);

        usuario.setMensagens(mensagens);

        Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resp = usuarioService.salvarUsuario(usuario);

        Assertions.assertEquals(1, resp.getId());
        Assertions.assertEquals(1, resp.getMensagens().size());
        Assertions.assertEquals("oi", resp.getMensagens().get(0).getTexto());

    }

    private static Usuario cria_user(String identifier, Integer id, Integer idade){
        Usuario usuario = new Usuario();
        usuario.setIdentifier(identifier);
        usuario.setId(id);
        usuario.setIdade(idade);
        return usuario;
    }

}
