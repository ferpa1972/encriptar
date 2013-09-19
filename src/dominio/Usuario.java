
package dominio;

import java.util.Calendar;
import java.util.Date;

public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String tipo;
    private Date fecha_nac;
    private int edad;
    private String nick;
    private String email;
    private String img;
    private String pass;

    public Usuario() {
        this.id = 0;
        this.nombre = "";
        this.apellido = "";
        this.tipo = "";
        this.fecha_nac = new Date();
        this.edad = 0;
        this.nick = "";
        this.email = "";
        this.img = "";
        this.pass = "";
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public int getEdad() {
        return edad;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    private void calcular() {
        Calendar hoy = Calendar.getInstance();
        //int dif_y = hoy.get(Calendar.YEAR) - this.fecha_nac.get(Calendar.YEAR);
        //int dif_m = hoy.get(Calendar.MONTH) - this.fecha_nac.get(Calendar.MONTH);
        //int dif_d = hoy.get(Calendar.DAY_OF_MONTH) - this.fecha_nac.get(Calendar.DAY_OF_MONTH); 
        
        //if(dif_m<0 || (dif_m==0 && dif_d<0))
            //dif_y = dif_y – 1;
        
        //this.edad = dif_y;
        }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
