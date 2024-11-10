package org.example.microgestorviajes.Entity;
import lombok.Data;
import org.example.microgestorviajes.clienteFeign.Monopatin;
import org.example.microgestorviajes.clienteFeign.Parada;
import org.example.microgestorviajes.clienteFeign.Usuario;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Data
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "monopatin_id", nullable = false)
    private Monopatin monopatin;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "parada_id", nullable = false)
    private Parada parada;

    private LocalDate fecha;
    private int horaInicio;
    private double precio;
    private int tiempoDePausa;
}



