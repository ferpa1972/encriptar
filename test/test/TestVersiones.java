package test;

import baseDatos.ManejadorBD;
import controladores.ControladorVersiones;
import controladores.Controladorjuegos;
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
    Controladorjuegos cj = Controladorjuegos.getInstancia();
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
            v.setNro_version("");
            v.setSize(765);
           
            v.setExtension("");
            v.setFecha_alta(new Date());
            v.setId_juego(10);
            v.setJuego(cj.buscarJuegoPorID(v.getId_juego()));
            v.setOrden_alta(5);
            } catch (SQLException ex) {
            Logger.getLogger(TestVersiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    @Test
//    public void AltaVersion() {
//        try {
//           cv.altaversion(v);
//        } catch (Exception ex) {
//           fail();
//        }
//    }
    @Test
    public void bajaVersion(){
        try{
            cv.bajaVersion(v.getId_juego(),v.getOrden_alta());
        }catch(Exception ex){
            Logger.getLogger(TestVersiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}