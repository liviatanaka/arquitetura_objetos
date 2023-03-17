package com.example.rede.mensagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.*;



@RestController
public class MensagemController {


    @Autowired
    MensagemService mensagemService;

    // GET /mensagem -> Listar todas as mensagens
    @GetMapping("/mensagem")
    public List<Mensagem> getMensagens(){
        return mensagemService.listarMensagens();
    }

    // POST /mensagemText -> Criar uma mensagem, verificar se o usuário dono da mensagem existe.
    @PostMapping("/mensagemText")
    public MensagemTexto postMensagemText(@RequestBody MensagemTexto mensagemTexto){
        return mensagemService.salvarMensagemTexto(mensagemTexto);
    }

    // POST /mensagemArquivo -> Criar uma mensagem, verificar se o usuário dono da mensagem existe.
    @PostMapping("/mensagemArquivo")
    public MensagemArquivo postMensagemArquivo(@RequestBody MensagemArquivo mensagemArquivo){
        return mensagemService.salvarMensagemArquivo(mensagemArquivo);
    }

    // GET /mensagem -> Listar todas as mensagens cadastradas

    // GET /mensagem/{mensagemId} -> Recuperar uma mensagem específica
    @GetMapping("/mensagem/{mensagemId}")
    @ResponseBody
    public Mensagem getMensagemId(@PathVariable String mensagemId){
        return mensagemService.buscarMensagemId(mensagemId);
    }

    // GET /mensagem/{userId}/user -> Listar todas as mensagens de um usuário
    @GetMapping("/mensagem/{userId}/user")
    @ResponseBody
    public List<Mensagem> getMensagemUsuario(@PathVariable String userId){
        return mensagemService.buscarMensagemUsuarioId(userId);
    }

}
