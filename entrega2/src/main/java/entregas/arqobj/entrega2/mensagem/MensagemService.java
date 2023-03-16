package entregas.arqobj.entrega2.mensagem;

import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import entregas.arqobj.entrega2.usuario.*;

import java.util.*;

@Service
public class MensagemService {

    private List<Mensagem> mensagens = new ArrayList<>();

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public Mensagem getMensagem(String id) {
        for (Mensagem mensagem : mensagens) {
            if (mensagem.getId().equals(id)) { 
                return mensagem;
            }
        }
        return null;
    }

    public List<Mensagem> getMensagensPorUsuario(String userId) {
        for (Mensagem mensagem : mensagens) {
            if (mensagem.getAutor().getId().equals(userId)) {
                return mensagens.stream().filter(m -> m.getAutor().getId().equals(userId)).collect(Collectors.toList());
            }
        }
        return null;
    }

    public Mensagem addMensagem(Mensagem mensagem) {
        mensagem.setId(UUID.randomUUID().toString());
        mensagens.add(mensagem);
        return mensagem;
    }

    public Mensagem updateMensagem(Mensagem mensagem) {
        Mensagem mensagemAntiga = getMensagem(mensagem.getId());
        if (mensagemAntiga != null) {
            mensagemAntiga.setAutor(mensagem.getAutor());
            mensagemAntiga.setMensagem(mensagem.getMensagem());
        }
        return mensagemAntiga;
    }

    public Mensagem deleteMensagem(String id) {
        Mensagem mensagem = getMensagem(id);
        if (mensagem != null) {
            mensagens.remove(mensagem);
        }
        return mensagem;
    }

}
