package ar.edu.unahur.obj2;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.profugo.Profugo;

public interface EstrategiaCapturar {
    Boolean intentarCapturar(Profugo p);
    void intimidar(Profugo p);
}

class Cazador {
    private Integer experiencia;
    private EstrategiaCapturar tipoCazador;
    private List<Profugo> profugosIntimidados;
    private List<Profugo> profugosCapturados;

    public void cazarEn(Zona zona) {
        //lista temporal para no usar for i, que salterá profugos:
        var profugosAEvaluar = new ArrayList<>(zona.getListaDeProfugos());

        for(Profugo profugo : profugosAEvaluar)
                this.evaluarProfugo(profugo, zona);
    }

    private void evaluarProfugo(Profugo profugo, Zona zona) {
        if(this.puedeCapturar(profugo))
            this.capturar(profugo, zona);
        else
            this.intimidar(profugo);
    }

    private Boolean puedeCapturar(Profugo p) {
        return this.experiencia > p.getInocencia() && tipoCazador.intentarCapturar(p);
    }

    private void capturar(Profugo profugo, Zona zona) {
        this.profugosCapturados.add(profugo);
        zona.removerProfugo(profugo);
        this.sumarExperiencia();
    }

    private void intimidar(Profugo p) {
        this.profugosIntimidados.add(p);
        p.disminuirInocencia();
        tipoCazador.intimidar(p);
    }

    private void sumarExperiencia() {
        var expPorIntimidados = this.profugosIntimidados.stream()
                                                        .mapToInt(Profugo::getHabilidad)
                                                        .min()
                                                        .orElse(0);

        var expPorCapturados = this.profugosCapturados.size() * 2;



        this.experiencia += expPorIntimidados + expPorCapturados;
    }
}

class CazadorSigiloso implements EstrategiaCapturar {
    @Override
    public Boolean intentarCapturar(Profugo p) {
        return p.getHabilidad() < 50;
    }

    @Override
    public void intimidar(Profugo p) {
        p.reducirHabilidad();
    }
}

class CazadorRural implements EstrategiaCapturar {
    @Override
    public Boolean intentarCapturar(Profugo p) {
        return p.esNervioso();
    }

    @Override
    public void intimidar(Profugo p) {
        p.volverseNervioso();
    }
}

class CazadorUrbano implements EstrategiaCapturar {
    @Override
    public Boolean intentarCapturar(Profugo p) {
        return ! p.esNervioso();
    }

    @Override
    public void intimidar(Profugo p) {
        p.dejarDeEstarNervioso();
    }
}