
package controladores;

import baseDatos.ManejadorBD;

import dominio.Version;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorVersiones {
    
    private static ControladorVersiones INSTANCIA = null;
    private ManejadorBD mbd = ManejadorBD.getInstancia();
    private Controladorjuegos cj = Controladorjuegos.getInstancia();
    
    public static ControladorVersiones getInstancia(){
        if (INSTANCIA == null)
             INSTANCIA = new ControladorVersiones();
         return INSTANCIA;
    }
    
    public ArrayList listarVersiones(int id_juego, char filtro) throws SQLException{
        String sql = "select * from versiones where id_juego = "+id_juego;
        if (filtro == 'a'){
            sql += " and estado = 'aprobada'";
        }
        else if(filtro == 'p'){
            sql += " and estado = 'pendiente'";
        }
        else if (filtro == 'r'){
            sql += " and estado = 'rechazada'";
        }
        
        ResultSet res = mbd.SELECT(sql);
        ArrayList <Version> versiones = new ArrayList<>();
        while (res.next()){
            Version v = new Version();
            v.setId_juego((id_juego));
            v.setNro_version(res.getString("numero_version"));
            v.setSize(res.getDouble("size"));
            v.setJar(res.getString("jar"));
            v.setEstado(res.getString("estado"));
            v.setFecha_alta(res.getDate("fecha_alta"));
            v.setOrden_alta(res.getInt("orden_de_alta"));
            v.setJuego(cj.buscarJuegoPorID(id_juego));
            
            if (v.getEstado().equals("rechazada")){
                String sql2 = "select motivo from versiones_rechazadas where id_juego = "+id_juego
                        +" and numero_version = '"+v.getNro_version()+"'";
                
                ResultSet res2 = mbd.SELECT(sql2);
                res2.next();
                v.setMotivo_recahazo(res2.getString("motivo"));
            }
            versiones.add(v);
        }
        return versiones;
    }
    
    public void altaversion(Version v){
        try {
            String sql = "INSERT INTO versiones (`id_juego`,`orden_de_alta`,`numero_version`,`size`,`jar`, " +
                        "`estado`, `fecha_alta`)" +
                        "VALUES ($1,$2,$3,$4,$5,$6,$7);";
            
            v.setOrden_alta(getOrdenDeAlta(v.getId_juego()));
            
            sql = sql.replace("$1", String.valueOf(v.getId_juego()));
            sql = sql.replace("$2", String.valueOf(v.getOrden_alta()));
            sql = sql.replace("$3", (v.getNro_version()));
            sql = sql.replace("$4", String.valueOf(v.getSize()));
            sql = sql.replace("$5", v.getJar());
            sql = sql.replace("$6", v.getEstado());
            sql = sql.replace("$7", String.valueOf(v.getFecha_alta()));

            mbd.INSERT(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorVersiones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private int getOrdenDeAlta(int idJuego ){
        int ordenA = 1;
        try {
            String sql = "SELECT max(orden_de_alta) as orden FROM market.versiones where id_juego = " + idJuego + ";";
            
            ResultSet res;
            
            res = mbd.SELECT(sql);
            
             while(res.next()){
                ordenA = (res.getInt("orden"));
            
        }
             
                     

            
        } catch (SQLException ex) {
            Logger.getLogger(ControladorVersiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ordenA;

    }
    
}
