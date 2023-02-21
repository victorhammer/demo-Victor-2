package com.s2daw.demo.dao;

import com.s2daw.demo.models.Usuario;

import java.util.List;

public interface UsuarioDao {

    Usuario getUsuario(Long id);

    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registra(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}