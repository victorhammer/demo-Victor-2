package com.s2daw.demo.controllers;

import com.s2daw.demo.dao.UsuarioDao;
import com.s2daw.demo.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;
    @RequestMapping(value="api/usuarios/{id}",method= RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Víctor");
        usuario.setApellidos("Alonso");
        usuario.setEmail("alumno.53998@ies-azarquiel.es");
        usuario.setTelefono("648702241");
        return usuario;
    }
    @RequestMapping(value="api/usuarios",method= RequestMethod.GET)
    public List<Usuario> getUsuarios(){
        return usuarioDao.getUsuarios();
    }

    @RequestMapping(value="api/usuarios/{id}",method= RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id){
        usuarioDao.eliminar(id);
    }
}