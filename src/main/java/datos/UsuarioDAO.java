package datos;

import domain.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private static final String SQL_SELECT = "SELECT * FROM test.usuario";
    private static final String SQL_INSERT = "INSERT INTO test.usuario (usuario, password) VALUES (?, ?)";
    private static final String SQL_DELETE = "DELETE FROM test.usuario WHERE id_usuario = ?";
    private static final String SQL_UPDATE = "UPDATE test.usuario SET usuario = ?, password = ? WHERE id_usuario = ?";
    
    private Connection conexionTransaccional;

    public UsuarioDAO() {
    }
    
    public UsuarioDAO(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }
    
    public List<Usuario> read() throws SQLException{
        List<Usuario> usuarios = new ArrayList();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario usuario;
        int rows = 0;
        try {
            
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();

            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while(rs.next()){
                int id_usuario = rs.getInt("id_usuario");
                String user = rs.getString("usuario");
                String password = rs.getString("password");
                usuario = new Usuario(id_usuario, user, password);
                usuarios.add(usuario);
                rows += 1;
            }
            System.out.println("Columnas leídas = " + rows);//COLUMNAS DE LA TABLA DE LA DB
        }finally{
                rs.close();
                ps.close();
                if(this.conexionTransaccional == null){
                    conn.close();
                }
        }
        return usuarios;
    }
    
    public int create(Usuario usuario) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, usuario.getUser());
            ps.setString(2, usuario.getPass());
            rows = ps.executeUpdate();
            System.out.println("Created rows: " + rows);
        }finally{
                ps.close();
                if(this.conexionTransaccional == null){
                    conn.close();
                }
        }
        return rows;
    }
    
    public int update(Usuario usuario) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, usuario.getUser());
            ps.setString(2, usuario.getPass());
            ps.setInt(3, usuario.getId_usuario());
            rows = ps.executeUpdate();
            System.out.println("Updated rows: " + rows);
        }finally{
                ps.close();
                if(this.conexionTransaccional == null){
                    conn.close();
                }
        }
        return rows;
    }
    
    public int delete(Usuario usuario) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        int rows = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, usuario.getId_usuario());
            rows = ps.executeUpdate();
            System.out.println("Deleted rows: " + rows);
        }finally{
                ps.close();
                if(this.conexionTransaccional == null){
                    conn.close();
                }
        }
        return rows;
    }
}
