package org.example.microgestormonopatin.Dto;
import org.example.microgestormonopatin.Entity.Monopatin;

public class MonopatinKmsDTO {
    private Long id;
    private double kms;


    public MonopatinKmsDTO(Monopatin monopatin) {
        this.id = monopatin.getId();
        this.kms = monopatin.getKms();

    }

    @Override
    public String toString() {
        return "MonopatinDto{" +
                "id=" + id +
                ", kms=" + kms;
    }
}