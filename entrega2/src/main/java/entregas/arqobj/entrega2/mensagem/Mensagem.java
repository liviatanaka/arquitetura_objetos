package entregas.arqobj.entrega2.mensagem;
import  entregas.arqobj.entrega2.usuario.Usuario;

public class Mensagem {
    private Usuario autor;

    public Mensagem(Usuario autor, Usuario data) {
        this.autor = autor;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public String getId() {
        return autor.getId();
    }
    
    public void setId(String id) {
        autor.setId(id);
    }

    public String getMensagem() {
        return "";
    }

    public void setMensagem(String mensagem) {
    }

}
