package com.entregas.rede.mensagem;

import com.entregas.rede.usuario.Usuario;
import com.entregas.rede.usuario.UsuarioController;
import com.entregas.rede.usuario.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MensagemControllerTests {
    MockMvc mockMvc;

    @InjectMocks
    MensagemController mensagemController;

    @Mock
    MensagemService mensagemService;

    @BeforeEach
    void setup(){
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(mensagemController)
                .build();
    }


    @Test
    void test_listarMensagens() throws  Exception{
        Mensagem msg = new Mensagem();
        msg.setIdentifier("msg1");
        msg.setTexto("oi");
        msg.setId(1);

        List<Mensagem> mensagens = new ArrayList<>();
        mensagens.add(msg);

        Mockito.when(mensagemService.listaMensagens()).thenReturn(mensagens);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/mensagem"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper om = new ObjectMapper();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(mensagens), resp);

    }

    @Test
    void test_getMensagemPorIdentifier() throws Exception {
        Mensagem msg = new Mensagem();
        msg.setIdentifier("msg1");
        msg.setTexto("oi");
        msg.setId(1);

        Mockito.when(mensagemService.getMensagem("msg1")).thenReturn(msg);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/mensagem/msg1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper om = new ObjectMapper();
        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(msg), resp);


    }

    @Test
    void test_getMensagemPorUsuario() throws Exception {
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

        Mockito.when(mensagemService.getMensagensPorUsuario("user1")).thenReturn(mensagens);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/mensagem/user1/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper om = new ObjectMapper();
        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(mensagens), resp);


    }

    @Test
    void test_postMensagemTexto() throws Exception{
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

        Mockito.when(mensagemService.salvarMensagemTexto(Mockito.any())).thenReturn(mensagem);

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(mensagem);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/mensagemText").content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(content, resp);
    }

    @Test
    void test_postMensagemArquivo() throws Exception{
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

        Mockito.when(mensagemService.salvarMensagemArquivo(Mockito.any())).thenReturn(mensagem);

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(mensagem);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/mensagemArquivo").content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(content, resp);
    }



}
