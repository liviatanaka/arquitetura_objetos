package entregas.arqobj.entrega2.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{usuarioId}")
    public Usuario buscarUsuarioPorId(@PathVariable Long usuarioId) {
        return usuarioService.buscarUsuarioPorId(usuarioId);
    }

    @PostMapping 
    public Usuario salvarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.salvarUsuario(usuario);
    }

}
