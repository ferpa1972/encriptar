package test;

import baseDatos.ManejadorBD;
import controladores.ControladorVersiones;
import dominio.Version;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestVersiones {

    ManejadorBD mbd = ManejadorBD.getInstancia();
    ControladorVersiones cv = ControladorVersiones.getInstancia();
    Version v = new Version();

    public TestVersiones() {
        try {
            mbd.setHost("localhost");
            mbd.setPuerto("3306");
            mbd.setBd("market");
            mbd.setUsuario("root");
            mbd.setPassword("root");
            if (mbd.estaDesconectado()) {
                mbd.conectar();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestVersiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Before
    public void setUp() {
        try {
            v.setEstado("pendiente");
            v.setExtension("");
            v.setFecha_alta(new Date());
            v.setId_juego(v.getId_juego());
            v.setJuego(controladores.Controladorjuegos.getInstancia().buscarJuegoPorID(v.getId_juego()));
            v.setOrden_alta(v.getOrden_alta());
            

        } catch (SQLException ex) {
            Logger.getLogger(TestVersiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void bajaVersion() {
        v = null;
    }

    
    @Test
    public void AltaVersion() {
        try{
            String prueba=v.getNro_version();
            ResultSet res=mbd.SELECT("Select numero_version");
            res.next();
            assertSame(res,prueba);
                  }catch(SQLException ex){
                      Logger.getLogger(TestVersiones.class.getName()).log(Level.SEVERE,null,ex);
                  }
    }
@Test
    public void BajaVersion() {
        try {
            String prueba;
            prueba = cv.bajaVersion(v.getId_juego(),v.getOrden_alta());
            ResultSet res = mbd.SELECT("DELETE from versiones "
                    + "WHERE id_version= " + v.getNro_version());
            res.next();
            assertSame(res.getString("nro_version"), prueba);
        } catch (SQLException ex) {
            Logger.getLogger(TestVersiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
