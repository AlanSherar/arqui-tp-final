package org.example.micromantenimiento.Repository;

import org.example.micromantenimiento.Entity.MantenimientoMonopatin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MantenimientoRepository extends JpaRepository<MantenimientoMonopatin,Long> {
}
