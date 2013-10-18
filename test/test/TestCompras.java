package test;

import baseDatos.ManejadorBD;
import dominio.Compra;
import dominio.Juego;
import dominio.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class TestCompras {
    ManejadorBD mbd = ManejadorBD.getInstancia();
    controladores.ControladorCompras ccomp= controladores.ControladorCompras.getInstancia();
    Juego j = new Juego();
    Usuario u = new Usuario();
    Date d = new Date();
    Compra c;
    public TestCompras() {
        try {
            mbd.setHost("localhost");
            mbd.setPuerto("3306");
            mbd.setBd("market");
            mbd.setUsuario("root");
            mbd.setPassword("root");
            if (mbd.estaDesconectado())
                mbd.conectar();
        } catch (SQLException ex) {
            Logger.getLogger(TestVersiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       @Before
    public void setUp() {
            j.setNombre("Skyrim");//nombre_juego
            j.setId(111111);//idjuego
            u.setId(121212);//idusuario
            u.setNick("alejo");//idjuego
            u.setTipo("c");
            
            c = new Compra(j, u, d);
    }
    
    @Test
    public void altaCompra() throws SQLException{
    try {
            ccomp.altaCompra(c);
     
 
        
        ccomp.bajaCompra(c);
    
        
       } catch (Exception ex) {
            fail();
        }
        
    }
}



