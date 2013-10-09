package baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManejadorBD {
    
    private static String driver = "com.mysql.jdbc.Driver";
    private String bd;
    private String host;
    private String puerto;
    private String usuario;
    private String password;
    private String url;
    
    private Connection conexion;
    private Statement st;
    
    private static ManejadorBD instancia = null;
    public static ManejadorBD getInstancia(){
        if(instancia == null){
            instancia = new ManejadorBD();
        }
        return instancia;
    }
    
    private ManejadorBD(){
        this.bd = "";
        this.host = "";
        this.puerto = "";
        this.usuario = "";
        this.password = "";
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
       this.bd = bd;
    }

    public String getHost() {
        return host;
    }
    
    public String getUsuario() {
        return usuario;
    }   
        
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    
    public void setHost(String host) {
        this.host = host;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public void conectar() throws SQLException{
        this.url = "jdbc:mysql://" + host + ":" + puerto + "/" + bd + "";
        try{
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, usuario,password);
            System.out.println("Conexion exitosa");
        }
        catch(ClassNotFoundException ex){
            System.out.println(ex.toString());
        }
    }
    
    public boolean estaDesconectado(){
        return (conexion==null);
    }
    
    public ResultSet SELECT(String sql) throws SQLException{
        ResultSet res;
        st = conexion.createStatement();
        res = st.executeQuery(sql);
        return res;
    }
    
    public int INSERT(String sql) throws SQLException{
        st = conexion.createStatement();
        st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        ResultSet res = st.getGeneratedKeys();
        if (res.next())
            return res.getInt(1);
        else
            return 0;
    }
    
    public void UPDATE(String sql) throws SQLException{
        st = conexion.createStatement();
        st.executeUpdate(sql);
    }
}
