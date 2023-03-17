package com.example.rede.usuario;

import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UsuarioService {

    private List<Usuario> usuarios = new ArrayList<>();

    /* Listar todos os usuários cadastrados na plataforma */
    public List<Usuario> listarUsuarios(){
        return usuarios;
    }

    /* Retornar um usuário específico pelo ID */
    public Usuario buscaUsuarioId(String id){
        for (Usuario usuario : usuarios){
            if (usuario.getId().equals(id)){
                return usuario;
            }
        }
        return null;
    }

    /* Criar um usuário */
    public Usuario salvarUsuario(Usuario usuario){
        usuario.setId(UUID.randomUUID().toString());
        usuarios.add(usuario);
        return usuario;
    }


}
