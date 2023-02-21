package com.s2daw.demo.dao;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import com.s2daw.demo.models.Usuario;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Usuario getUsuario(Long id) {
        String query="FROM Usuario u WHERE u.id=:id";
        List<Usuario> lista=entityManager.createQuery(query,Usuario.class)
                .setParameter("id",id)
                .getResultList();
        if (lista.isEmpty()) return null;
        return lista.get(0);
    }

    @Override
    public List<Usuario> getUsuarios() {
        String query="FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario=entityManager.find(Usuario.class,id);
        entityManager.remove(usuario);
    }

    @Override
    public void registra(Usuario usuario) {
        entityManager.merge(usuario);
    }
    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        String query="FROM Usuario where email=:email";
        List<Usuario> lista=entityManager.createQuery(query,Usuario.class)
                .setParameter("email",usuario.getEmail())
                .getResultList();
        if (lista.isEmpty()) return null;
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        // Pasar password como String está depreciado
        if (argon2.verify(lista.get(0).getPassword(),usuario.getPassword().getBytes()))
            return lista.get(0);
        else return null;
    }
}
