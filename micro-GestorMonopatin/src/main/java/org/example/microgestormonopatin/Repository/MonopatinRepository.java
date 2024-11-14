package org.example.microgestormonopatin.Repository;

import org.example.microgestormonopatin.Entity.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface MonopatinRepository extends JpaRepository<Monopatin, Long> {

    @Query("SELECT m FROM Monopatin m ORDER BY m.kms desc")
    List<Monopatin> getMonopatinesByKms();

    @Query("SELECT m FROM Monopatin m ORDER BY m.tiempo_de_uso + m.tiempo_pausa desc")
    List<Monopatin> getMonopatinesByPausa();

    @Query("SELECT m FROM Monopatin m ORDER BY m.tiempo_de_uso desc")
    List<Monopatin> getMonopatinesByNotPausa();

    @Query("SELECT COUNT(*) FROM Monopatin m WHERE m.disponible=true")
    long getMonopatinesEnOperacion();

    @Query("SELECT COUNT(*) " + "FROM Monopatin m " + "WHERE m.disponible=false")

    long getMonopatinesEnMantenimiento();



}
