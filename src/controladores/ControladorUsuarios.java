
package controladores;

import baseDatos.ManejadorBD;
import dominio.Cliente;
import dominio.Desarrollador;
import dominio.Usuario;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControladorUsuarios {
    
    private static ControladorUsuarios INSTANCIA = null;
    private ManejadorBD mbd = ManejadorBD.getInstancia();
    
    public static ControladorUsuarios getInstancia(){
        if (INSTANCIA == null)
             INSTANCIA = new ControladorUsuarios();
         return INSTANCIA;
    }

    private ControladorUsuarios() {
        //mbd.conectar();
    }
    
    public ArrayList listar(String filtro) throws SQLException{
        String sql= "select id_usuario, nick from usuarios ";
        if (filtro.equals("cli")){
            sql += "where tipo = 'c'";
        }
        if (filtro.equals("des")){
            sql += "where tipo = 'd'";
        }
        
        ArrayList usuarios = new ArrayList();
        ResultSet res = mbd.SELECT(sql);
        while(res.next()){
            Usuario user = new Usuario();
            user.setNick(res.getString("nick"));
            user.setId(res.getInt("id_usuario"));
            usuarios.add(user);
        }
        return usuarios;
    }
    
    public int altaUsuario(Usuario user) throws SQLException{
        Date fnac = new Date(user.getFecha_nac().getTime());
        String sql = "insert into usuarios (nombre,apellido,nick,fecha_nacimiento,email,tipo,foto,sitio_web,pass)"
                + "   values ('$1','$2','$3','$4','$5','$6','$7','$8','$9')";

        sql = sql.replace("$1", user.getNombre());
        sql = sql.replace("$2", user.getApellido());
        sql = sql.replace("$3", user.getNick());
        sql = sql.replace("$4", fnac.toString());
        sql = sql.replace("$5", user.getEmail());
        sql = sql.replace("$6", user.getTipo());
        sql = sql.replace("$7", user.getImg());
        sql = sql.replace("$9", user.getPass());
        if (user.getTipo().equals("d")){
            Desarrollador d = (Desarrollador)user;
            sql = sql.replace("$8", d.getWeb());
        }
        else{
            sql = sql.replace("$8", "");
        }
        return mbd.INSERT(sql);
    }
    
    public void actualizarUsuario(Usuario user) throws Exception{
        String sql = "update usuarios set nombre = '$1', apellido = '$2', nick = '$3', "+
                     "fecha_nacimiento = '$4',email = '$5', foto = '$6', sitio_web = '$7',"
                     + " pass = '$8' where id_usuario = "+user.getId();
        Date fnac = new Date(user.getFecha_nac().getTime());
        sql = sql.replace("$1", user.getNombre());
        sql = sql.replace("$2", user.getApellido());
        sql = sql.replace("$3", user.getNick());
        sql = sql.replace("$4", fnac.toString());
        sql = sql.replace("$5", user.getEmail());
        sql = sql.replace("$6", user.getImg());
        sql = sql.replace("$8", user.getPass());
        
        if(user.getTipo().equals("d")){
            Desarrollador d = (Desarrollador)user;
            sql = sql.replace("$7", d.getWeb());
        }
        else
            sql = sql.replace("$7", "");
        
        mbd.UPDATE(sql);
    }
     
    public ArrayList buscar(String bs) throws SQLException{
        ArrayList usuarios = new ArrayList();
        String sql = "select id_usuario, nick from usuarios where nick like '%" + bs + "%'";
        ResultSet res = mbd.SELECT(sql);
        while(res.next()){
            Usuario u = new Usuario();
            u.setNick(res.getString("nick"));
            u.setId(res.getInt("id_usuario"));
            usuarios.add(u);
        }
        return usuarios;
    }
    
    public Usuario verInfoUsuario(int id) throws SQLException{
        Usuario u = null;
        String sql = "select * from usuarios where id_usuario = "+id;
        ResultSet res = mbd.SELECT(sql);
        while(res.next()){
            if (res.getString("tipo").equals("d")){
                Desarrollador d = new Desarrollador();
                d.setWeb(res.getString("sitio_web"));
                u = d;
            }
            else{
                Cliente c = new Cliente();
                u = c;
            }
            u.setTipo(res.getString("tipo"));
            u.setId(res.getInt("id_usuario"));
            u.setNombre(res.getString("nombre"));
            u.setApellido(res.getString("apellido"));
            u.setEmail(res.getString("email"));
            u.setNick(res.getString("nick"));
            u.setFecha_nac(res.getDate("fecha_nacimiento"));
            u.setImg(res.getString("foto"));
        }
        return u;
    }
    
    public boolean login(String user, String pass) throws SQLException{
        return mbd.SELECT("select id_usuario from usuarios where "
                        + " nick = '"+user+"' and pass = '"+pass+"'").first();
    }
    
    public Usuario find(String nick) throws SQLException{
        ResultSet res = mbd.SELECT("select id_usuario from usuarios where nick = '"+nick+"'");
        res.next();
        int id = res.getInt(1);
        return this.verInfoUsuario(id);
    }
}
