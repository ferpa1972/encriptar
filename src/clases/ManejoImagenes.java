package clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ManejoImagenes {
    
    private static ManejoImagenes instancia = null;
    
    public ManejoImagenes getInstancia(){
        if (instancia == null){
            instancia = new ManejoImagenes();
        }
        return instancia;
    }
    
    public void copiar(File origen, File destino) throws FileNotFoundException, IOException{
        InputStream in = new FileInputStream(origen);
        OutputStream out = new FileOutputStream(destino);
        
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }
}
