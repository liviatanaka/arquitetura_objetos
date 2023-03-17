package com.entregas.rede.mensagem;

import com.entregas.rede.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {

    Mensagem findByIdentifier(String identifier);

    List<Mensagem> findByUsuario(Usuario usuario);
}
