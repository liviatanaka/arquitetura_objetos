package com.example.rede.usuario;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /* GET /usuario -> Listar todos os usuários cadastrados na plataforma */
    @GetMapping
    public List<Usuario> getUsuarios(){
        return usuarioService.listarUsuarios();
    }

    /* GET /usuario/{id} -> Retornar um usuário específico pelo ID */
    @GetMapping("/{id}")
    @ResponseBody
    public Usuario getUsuarioId(@PathVariable String id){
        return usuarioService.buscaUsuarioId(id);
    }

    /* POST /usuario -> Criar um usuário */
    @PostMapping
    public Usuario postUsuario(@RequestBody Usuario usuario){
        return usuarioService.salvarUsuario(usuario);
    }
}
