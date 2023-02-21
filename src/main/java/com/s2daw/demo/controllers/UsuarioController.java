package com.s2daw.demo.controllers;

import com.s2daw.demo.dao.UsuarioDao;
import com.s2daw.demo.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value="api/usuarios",method= RequestMethod.POST)
    public void registraUsuario(@RequestBody Usuario usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        // La versión en la que se pasa el password como string está depreciada
        String hash = argon2.hash(1,1024,1, usuario.getPassword().getBytes());
        usuario.setPassword(hash);
        usuarioDao.registra(usuario);
    }

    @RequestMapping(value="api/usuarios/{id}",method= RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id){
        usuarioDao.eliminar(id);
    }
}