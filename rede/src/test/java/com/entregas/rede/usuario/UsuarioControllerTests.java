package com.entregas.rede.usuario;

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
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTests {

    MockMvc mockMvc;

    @InjectMocks
    UsuarioController usuarioController;

    @Mock
    UsuarioService usuarioService;

    @BeforeEach
    void setup(){
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(usuarioController)
                .build();
    }

    @Test
    void test_getUsuarios() throws  Exception{
        Usuario usuario = cria_user("user show", 1, 15);

        List<Usuario> lista_usuario = new ArrayList<>();
        lista_usuario.add(usuario);

        Mockito.when(usuarioService.listaUsuarios()).thenReturn(lista_usuario);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/usuario"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper om = new ObjectMapper();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(lista_usuario), resp);

    }

    @Test
    void test_getUsuarioPorIdentifier() throws Exception {
        Usuario usuario = cria_user("user1", 1, 18);

        Mockito.when(usuarioService.buscarUsuarioPorIdentifier("user1")).thenReturn(usuario);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/usuario/user1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper om = new ObjectMapper();
        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(usuario), resp);


    }

    @Test
    void test_postUsuario() throws Exception{
        Usuario usuario = cria_user("user1", 1, 19);

        Mockito.when(usuarioService.salvarUsuario(Mockito.any())).thenReturn(usuario);

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(usuario);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/usuario").content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(content, resp);
    }

    private static Usuario cria_user(String identifier, Integer id, Integer idade){
        Usuario usuario = new Usuario();
        usuario.setIdentifier(identifier);
        usuario.setId(id);
        usuario.setIdade(idade);
        return usuario;
    }
}
