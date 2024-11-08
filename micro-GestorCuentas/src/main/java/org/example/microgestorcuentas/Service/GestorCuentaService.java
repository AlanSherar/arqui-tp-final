package org.example.microgestorcuentas.Service;

import org.example.microgestorcuentas.Repository.CuentasAsociadasRepository;
import org.example.microgestorcuentas.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@Service
public class GestorCuentaService {
//    @Autowired
//    private FeignClient feign;
    @Autowired
    private UsuarioRepository repositoryUsuario;
    @Autowired
    private CuentasAsociadasRepository cuentasRepository;
}
