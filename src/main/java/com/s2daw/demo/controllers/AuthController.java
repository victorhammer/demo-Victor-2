package com.s2daw.demo.controllers;

import com.s2daw.demo.dao.UsuarioDao;
import com.s2daw.demo.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    private UsuarioDao usuarioDao;
    // Formateo respuesto como JSON
    @RequestMapping(value="api/login",
                    method= RequestMethod.POST)
    // Cambio devolución de String a Json
    public Map loginUsuario(@RequestBody Usuario usuario){
        String respuesta="FAIL";
        if (usuarioDao.verificarCredenciales(usuario)) respuesta="OK";
        return Collections.singletonMap("success", respuesta);
    }

}
