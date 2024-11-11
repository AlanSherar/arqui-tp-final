package org.example.micromantenimiento.Entity;

import jakarta.persistence.Id;
import lombok.Data;

//@Entity
@Data

public class MonopatinMantenimiento {

    @Id
   // @GeneratedValue
    private Long id;

   // @ManyToOne
    //private Monopatin monopatin;

}
