package ar.edu.unahur.obj2;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.profugo.Profugo;

public class Zona {
    private String nombre;
    private List<Profugo> listaDeProfugos;

    public Zona(String nombre) {
        this.nombre = nombre;
        this.listaDeProfugos = new ArrayList<>();
    }

    public void removerProfugo(Profugo p) {
        this.listaDeProfugos.remove(p);
    }
    
    
    //GS
    public String getNombre() {
        return nombre;
    }
    public List<Profugo> getListaDeProfugos() {
        return listaDeProfugos;
    }


}
