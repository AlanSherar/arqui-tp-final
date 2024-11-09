package org.example.microgestorcuentas.Repository;

import org.example.microgestorcuentas.Entity.CuentasAsociadas;
import org.example.microgestorcuentas.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentasAsociadasRepository extends JpaRepository<CuentasAsociadas, Long> {

}
