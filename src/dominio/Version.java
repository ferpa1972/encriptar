package dominio;

import java.util.Date;

public class Version {
    //public enum Estado {aprobada, pendiente, rechazada};
    
    private int id_version;
    private int id_juego;
    private String nro_version;
    private int orden_alta;
    private String jar;
    private Date fecha_alta;
    private String estado;
    private double size;
    private String motivo_recahazo;
    private Juego juego;
    
    public Version(){
        id_juego = 0;
        nro_version = "";
        orden_alta = 1;
        jar = "";
        fecha_alta = new Date();
        estado = "";
        size = 0;
        motivo_recahazo = "";
        juego = new Juego();
    }

    public Version(int id_juego, String nro_version, String jar) {
        this.id_juego = id_juego;
        this.nro_version = nro_version;
        this.jar = jar;
    }

    public int getId_version() {
        return id_version;
    }

    public void setId_version(int id_version) {
        this.id_version = id_version;
    }

    public int getId_juego() {
        return id_juego;
    }

    public void setId_juego(int id_juego) {
        this.id_juego = id_juego;
    }

    public String getNro_version() {
        return nro_version;
    }

    public void setNro_version(String nro_version) {
        this.nro_version = nro_version;
    }

    public int getOrden_alta() {
        return orden_alta;
    }

    public void setOrden_alta(int orden_alta) {
        this.orden_alta = orden_alta;
    }

    public String getJar() {
        return jar;
    }

    public void setJar(String jar) {
        this.jar = jar;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getMotivo_recahazo() {
        return motivo_recahazo;
    }

    public void setMotivo_recahazo(String motivo_recahazo) {
        this.motivo_recahazo = motivo_recahazo;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }
    
}
