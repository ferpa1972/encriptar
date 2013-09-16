
package dominio;

import java.util.ArrayList;

public class Categoria {
    
    private int id;
    private String nombre;
    private String imagen;
    private ArrayList<Juego> juegos;

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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public ArrayList getJuegos() {
        return juegos;
    }

    public void setJuegos(ArrayList juegos) {
        this.juegos = juegos;
    }
}
