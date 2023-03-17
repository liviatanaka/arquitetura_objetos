package com.entregas.rede.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getUsuarios(){
        return usuarioService.listaUsuarios();
    }

    @PostMapping
    public Usuario postUsuario(@RequestBody Usuario usuario){
        return usuarioService.salvarUsuario(usuario);
    }

    @GetMapping("/{usuarioIdentifier}")
    public Usuario getUsuarioPorIdentifier(@PathVariable String usuarioIdentifier){
        return usuarioService.buscarUsuarioPorIdentifier(usuarioIdentifier);
    }
}
