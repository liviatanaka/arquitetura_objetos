package entregas.arqobj.entrega2.usuario;

public class Usuario {
    private String nome;
    private String email;
    private String telefone;
    private String id;

    public Usuario(String nome, String email, String telefone, String id) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome inválido");
        }
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (telefone == null || telefone.isEmpty()) {
            throw new IllegalArgumentException("Telefone inválido");
        }
        this.telefone = telefone;
    }

    public String getId () {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

