package com.s2daw.demo.controllers;

import com.s2daw.demo.dao.UsuarioDao;
import com.s2daw.demo.models.Usuario;
import com.s2daw.demo.utils.JWTUtil;
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

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value="api/usuarios/{id}",method= RequestMethod.GET)
    public Usuario getUsuario(@RequestHeader(value="Authorization") String token,
                              @PathVariable Long id){
        if (!validarToken(token)) return null;
        Usuario usuario=usuarioDao.getUsuario(id);
        return usuario;
    }

    @RequestMapping(value="api/usuarios",method= RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token){
        // Si devuelves null generas un problema en el front porque espera una lista
        if (!validarToken(token)) return new ArrayList<>();
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

    @RequestMapping(value="api/eliminar/{id}",method= RequestMethod.DELETE)
    public void eliminarUsuario(@RequestHeader(value="Authorization") String token,
                                @PathVariable Long id){
        if (!validarToken(token)) return;
        usuarioDao.eliminar(id);
    }

    // función de apoyo
    private boolean validarToken(String token){
        String usuarioid=jwtUtil.getKey(token);
        return usuarioid!=null;
    }
}