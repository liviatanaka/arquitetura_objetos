package com.example.rede.mensagem;

import com.example.rede.usuario.Usuario;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class Mensagem {
    @NonNull
    private String id_usuario;
    @NonNull
    private String conteudo;

    private Usuario usuario;
    private String id;
}
