package test;

import baseDatos.ManejadorBD;
import controladores.ControladorUsuarios;
import dominio.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestUsuarios {
    ManejadorBD mbd = ManejadorBD.getInstancia();
    ControladorUsuarios cu = ControladorUsuarios.getInstancia();
       
    public TestUsuarios() throws SQLException {
        mbd.setHost("localhost");
        mbd.setPuerto("3306");
        mbd.setBd("market");
        mbd.setUsuario("root");
        mbd.setPassword("root");
        if (mbd.estaDesconectado())
            mbd.conectar();
    }
    
    @Test 
    public void altaUsuario() {
        Usuario u = new Usuario();
        u.setNick("nick de pruba");
        try {
            int id_usuario = cu.altaUsuario(u);
            
            ResultSet res = mbd.SELECT("select id_usuario from usuarios " +
                     "where nick = '"+u.getNick()+"'");
            res.next();
            
            assertSame(res.getInt("id_usuario"), id_usuario);
            
            mbd.UPDATE("delete from usuarios where id_usuario = " +id_usuario);
            
        } catch(SQLException ex) {
            Logger.getLogger(TestUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}