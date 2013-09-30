package test;

import baseDatos.ManejadorBD;
import controladores.ControladorCategorias;
import dominio.Categoria;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestCategorias {
    
    ManejadorBD mbd = ManejadorBD.getInstancia();
    ControladorCategorias cc = ControladorCategorias.getInstancia();
    Categoria c = new Categoria();
    
    public TestCategorias() throws SQLException {
        mbd.setHost("localhost");
        mbd.setPuerto("3306");
        mbd.setBd("market");
        mbd.setUsuario("root");
        mbd.setPassword("root");
        if (mbd.estaDesconectado())
            mbd.conectar();
    }
    
    @Before
    public void setUp() {
        Long seed = new GregorianCalendar().getTimeInMillis();
        c.setNombre(String.valueOf(new Random(seed).nextDouble()));
        c.setImagen(String.valueOf(new Random(seed).nextDouble()));
    }
    
    @Test
    public void AltaCategotia(){
        try {
            int id_cat = cc.altaCategoria(c);
            
            ResultSet res = mbd.SELECT("select * from categorias "
                    + "where nombre = '"+c.getNombre()+"'");
            res.next();
            
            assertSame(res.getInt("id_categoria"), id_cat);
            
            mbd.UPDATE("delete from categorias where id_categoria = "+id_cat);
        } catch (SQLException ex) {
            Logger.getLogger(TestCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}