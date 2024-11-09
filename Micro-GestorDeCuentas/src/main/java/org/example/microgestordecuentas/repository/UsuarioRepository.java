package org.example.microgestordecuentas.repository;

import org.example.microgestordecuentas.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}