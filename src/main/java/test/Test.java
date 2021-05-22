package test;

import datos.Conexion;
import datos.UsuarioDAO;
import domain.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        Connection conn = null;

        try {

            conn = Conexion.getConnection();

            if (conn.getAutoCommit() == true) {
                conn.setAutoCommit(false);
            }

            UsuarioDAO usuarioDAO = new UsuarioDAO(conn);

//            Usuario updateUsuario = new Usuario();
//            updateUsuario.setId_usuario(8);
//            updateUsuario.setUser("Daniel O");
//            updateUsuario.setPass("333333");
//            usuarioDAO.update(updateUsuario);
//
            Usuario createUsuario = new Usuario();
            createUsuario.setUser("JUSTINAJUSTINAJUSTINAJUSTINAJUSTINAJUSTINAJUSTINAJUSTINAJUSTINAJUSTINAJUSTINAJUSTINAJUSTINAJUSTINA");
            createUsuario.setPass("000000");
            usuarioDAO.create(createUsuario);
            
//            Usuario deleteUsuario = new Usuario();
//            deleteUsuario.setId_usuario(0);
//            usuarioDAO.delete(deleteUsuario);

            //FIN DE MODIFICACIONES EN DB
            conn.commit();
            System.out.println("Commit done!");

            List<Usuario> usuarios = new UsuarioDAO().read();
            usuarios.forEach(usuario -> {
                System.out.println("usuario = " + usuario);
                //System.out.println(usuario.toString());       
            });

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.out.println("Rollback done!");
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }
}
