package com.entregas.rede.mensagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
public class MensagemController {

    @Autowired
    private MensagemService mensagemService;

    // GET /mensagem -> Listar todas as mensagens
    @GetMapping("/mensagem")
    public List<Mensagem> getMensagens() {
        return mensagemService.listaMensagens();
    }

    // POST /mensagemText - Criar uma mensagem, verificar se o usuário dono da mensagem existe.
    @PostMapping("/mensagemText")
    public MensagemTexto postMensagemText(@RequestBody MensagemTexto mensagem) {
        return mensagemService.salvarMensagemTexto(mensagem);
    }

    // POST /mensagemArquivo -> Criar uma mensagem, verificar se o usuário dono da mensagem existe.
    @PostMapping("/mensagemArquivo")
    public MensagemArquivo postMensagemArquivo(@RequestBody MensagemArquivo mensagemArquivo){
        return mensagemService.salvarMensagemArquivo(mensagemArquivo);
    }


    // GET /mensagem/{mensagemId} -> Recuperar uma mensagem específica
    @GetMapping("/mensagem/{mensagemId}")
    public Mensagem getMensagemPorId(@PathVariable String mensagemId) {
        return mensagemService.getMensagem(mensagemId);
    }

    // GET /mensagem/{userId}/user -> Listar todas as mensagens de um usuário
    @GetMapping("/mensagem/{userId}/user")
    @ResponseBody
    public List<Mensagem> listarMensagensPorUsuario(@PathVariable String userId) {
        return mensagemService.getMensagensPorUsuario(userId);
    }

}
