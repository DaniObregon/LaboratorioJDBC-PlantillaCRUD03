package domain;

public class Usuario {
    private int id_usuario;
    private String user;
    private String pass;

    public Usuario() {
    }

    public Usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Usuario(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public Usuario(int id_usuario, String user, String pass) {
        this.id_usuario = id_usuario;
        this.user = user;
        this.pass = pass;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id_usuario=" + id_usuario + ", user=" + user + ", pass=" + pass + '}';
    }
    
}
