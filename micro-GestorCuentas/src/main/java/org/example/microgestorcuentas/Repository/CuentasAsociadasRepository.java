package org.example.microgestorcuentas.Repository;

import org.example.microgestorcuentas.Dao.CuentasAsociadas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentasAsociadasRepository  extends JpaRepository<CuentasAsociadas,Integer> {
}
