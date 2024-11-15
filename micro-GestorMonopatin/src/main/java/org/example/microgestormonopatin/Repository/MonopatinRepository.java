package org.example.microgestormonopatin.Repository;

import org.example.microgestormonopatin.Entity.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface MonopatinRepository extends JpaRepository<Monopatin, Long> {

    @Query("SELECT m FROM Monopatin m ORDER BY m.kms desc")
    public List<Monopatin> getMonopatinesByKms();

    @Query("SELECT m FROM Monopatin m ORDER BY (m.tiempo_de_uso + m.tiempo_pausa) desc")
    public List<Monopatin> getMonopatinesByPausa();

    @Query("SELECT m FROM Monopatin m ORDER BY m.tiempo_de_uso desc")
    public List<Monopatin> getMonopatinesByNotPausa();

    @Query("SELECT COUNT(*) FROM Monopatin m WHERE m.disponible=true")
    public long getMonopatinesEnOperacion();

    @Query("SELECT COUNT(*) " + "FROM Monopatin m " + "WHERE m.disponible=false")
    public long getMonopatinesEnMantenimiento();

    @Query ("SELECT m FROM Monopatin m " +
            "where m.ubicacionX <= :x + :CercaniaX " +
            "AND m.ubicacionX >= :x - :CercaniaX " +
            "AND m.ubicacionY <= :y + :CercaniaY " +
            "AND m.ubicacionY >= :y - :CercaniaY " +
            "AND m.disponible = true")
    public List<Monopatin> getCercanosZona(int x, int y, int CercaniaX, int CercaniaY);


}
