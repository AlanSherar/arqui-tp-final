package org.example.microgestormonopatin.Dto;
import org.example.microgestormonopatin.Entity.Monopatin;

public class MonopatinTiempoDTO {
    private Long id;
    private double tiempoDeUso;
    private double tiempoPausa;

    private double tiempoConPausa;
    public MonopatinTiempoDTO(Monopatin monopatin) {

        this.tiempoDeUso = monopatin.getTiempo_de_uso();
        this.tiempoPausa = monopatin.getTiempo_pausa();
        this.id= monopatin.getId();
        this.tiempoConPausa = monopatin.getTiempo_pausa() + monopatin.getTiempo_de_uso();

    }

    @Override
    public String toString() {
        return "MonopatinTiempoDTO{" +
                "id=" + id +
                ", tiempoDeUso='" + tiempoDeUso + '\'' +
                ", tiempoPausa='" + tiempoPausa + '\'' +
                ", tiempoConPausa='" + tiempoConPausa + '\'' +
                '}';
    }

}
