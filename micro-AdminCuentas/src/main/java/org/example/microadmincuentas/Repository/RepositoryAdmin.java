package org.example.microadmincuentas.Repository;

import org.example.microadmincuentas.Entities.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RepositoryAdmin extends JpaRepository<Tarifa,Long> {

}
