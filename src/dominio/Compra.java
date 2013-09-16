
package dominio;

import java.util.Date;

public class Compra {
    private Juego juego;
    private Cliente cliente;
    private Date fecha;

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Compra(Juego juego, Cliente cliente, Date fecha) {
        this.juego = juego;
        this.cliente = cliente;
        this.fecha = fecha;
    }
   
}
