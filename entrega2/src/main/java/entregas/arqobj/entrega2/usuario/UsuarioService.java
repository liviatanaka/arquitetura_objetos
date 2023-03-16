package entregas.arqobj.entrega2.usuario;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioService {

    private List<Usuario> usuarios  = new ArrayList<>(); 

    // Litsa de usuários
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    // buscar usuário por ID
    public Usuario buscarUsuarioPorId(Long usuarioId) { 
        for (Usuario usuario : usuarios) {
            if (usuario.getId().toString().equals(usuarioId.toString())) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario salvarUsuario (Usuario usuario) {
        // Salvar usuário
        usuarios.add(usuario);
        return usuario;
    }

}
