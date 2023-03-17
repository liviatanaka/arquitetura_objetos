package com.example.rede.usuario;
import com.example.rede.mensagem.Mensagem;
import lombok.*;

import java.util.*;

@Setter
@Getter
@RequiredArgsConstructor
public class Usuario {

    @NonNull
    private String nome;

    @NonNull
    private int idade;
    private List<Usuario> amigos = new ArrayList<>();

    private List<Mensagem> mensagens = new ArrayList<>();

    private String id;


}
