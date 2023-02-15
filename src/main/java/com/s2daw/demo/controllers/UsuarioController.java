package com.s2daw.demo.controllers;

import com.s2daw.demo.models.Usuario;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {

    @RequestMapping(value="usuario")
    public Usuario getUsuario(){
        Usuario usuario=new Usuario();
        usuario.setNombre("Víctor");
        usuario.setApellidos("Alonso");
        usuario.setEmail("victoralonso@gmail.com");
        usuario.setTelefono("648702241");
        return usuario;
    }
}
