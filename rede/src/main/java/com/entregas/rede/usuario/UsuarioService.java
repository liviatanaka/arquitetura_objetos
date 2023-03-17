package com.entregas.rede.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // lista de usuários
    public List<Usuario> listaUsuarios(){
        return usuarioRepository.findAll();
    }

    // buscar usuário por identifier
    public Usuario buscarUsuarioPorIdentifier(String identifier) {
        return usuarioRepository.findByIdentifier(identifier);
    }

    // Salvar usuário
    public Usuario salvarUsuario (Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
