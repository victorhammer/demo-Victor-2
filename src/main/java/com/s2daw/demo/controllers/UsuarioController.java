package com.s2daw.demo.controllers;


import com.s2daw.demo.models.Usuario;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @RequestMapping(value = "usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Víctor");
        usuario.setApellidos("Alonso");
        usuario.setEmail("alumno.53998@ies-azarquiel.es");
        usuario.setTelefono("648702241");
        return usuario;
    }

    @RequestMapping(value = "usuarios")
    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Víctor");
        usuario.setApellidos("Alonso");
        usuario.setEmail("alumno.53998@ies-azarquiel.es");
        usuario.setTelefono("648702241");

        Usuario usuario2 = new Usuario();
        usuario.setId(2L);
        usuario.setNombre("Juliano");
        usuario.setApellidos("Martin");
        usuario.setEmail("alumno.12345@ies-azarquiel.es");
        usuario.setTelefono("612302241");

        Usuario usuario3 = new Usuario();
        usuario.setId(3L);
        usuario.setNombre("Espinaca");
        usuario.setApellidos("Molina");
        usuario.setEmail("alumno.56732@ies-azarquiel.es");
        usuario.setTelefono("612302987");

        usuarios.add(usuario);
        usuarios.add(usuario2);
        usuarios.add(usuario3);

        return usuarios;
    }
}