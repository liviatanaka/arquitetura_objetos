package entregas.arqobj.entrega2.mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {

    @Autowired 
    MensagemService mensagemService;
    // GET /mensagem - Listar todas as mensagens
    @GetMapping
    public List<Mensagem> listarMensagens() {
        return mensagemService.getMensagens();
    }

    // POST /mensagemText - Criar uma mensagem, verificar se o usuário dono da mensagem existe.
    @PostMapping("/mensagemText")
    public Mensagem criarMensagem(@RequestBody Mensagem mensagem) {
        return mensagemService.addMensagem(mensagem);
    }

    // POST /mensagemArquivo - Criar uma mensagem, verificar se o usuário dono da mensagem existe.
    @PostMapping("/mensagemArquivo")
    public Mensagem criarMensagemArquivo(@RequestBody Mensagem mensagem) {
        return mensagemService.addMensagem(mensagem);
    }

    // GET /mensagem - Listar todas as mensagens cadastradas
    @GetMapping
    public List<Mensagem> listarMensagensCadastradas() {
        return mensagemService.getMensagens();
    }

    // GET /mensagem/{mensagemId} - Recuperar uma mensagem específica
    @GetMapping("/{mensagemId}")
    public Mensagem buscarMensagemPorId(@PathVariable String mensagemId) {
        return mensagemService.getMensagem(mensagemId);
    }

    // GET /mensagem/{userId}/user - Listar todas as mensagens de um usuário
    @GetMapping("/{userId}/user")
    public List<Mensagem> listarMensagensPorUsuario(@PathVariable String userId) {
        return mensagemService.getMensagensPorUsuario(userId);
    }
    
}
