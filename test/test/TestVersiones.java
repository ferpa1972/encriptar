
package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import baseDatos.ManejadorBD;
import controladores.ControladorCategorias;
import controladores.ControladorVersiones;
import dominio.Categoria;
import dominio.Version;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            if (mbd.estaDesconectado())
                mbd.conectar();
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
            v.setId_juego(120);
            v.setJuego(controladores.Controladorjuegos.getInstancia().buscarJuegoPorID(v.getId_juego()));
            v.setExtension("");
            
        } catch (SQLException ex) {
            Logger.getLogger(TestVersiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Test
    public void AltaVersion() {
        try {
            cv.altaversion(v);
            cv.bajaVersion(v.getId_juego(), v.getOrden_alta());
        } catch (SQLException ex) {
            fail();
        }
    }

}