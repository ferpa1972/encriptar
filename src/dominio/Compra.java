
package dominio;

import java.util.Date;

public class Compra {
    private Juego juego;
    private Usuario cliente;
    private Date fecha;

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Compra(Juego juego, Usuario cliente, Date fecha) {
        this.juego = juego;
        this.cliente = cliente;
        this.fecha = fecha;
    }
   
}
