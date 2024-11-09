package org.example.microgestorcuentas.Repository;

import org.example.microgestorcuentas.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
