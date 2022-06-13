package com.example.proyectosena.security;

import com.example.proyectosena.models.dao.UsuarioDao;
import com.example.proyectosena.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyUserService implements UserDetailsService {
    @Autowired
    private UsuarioDao dao;
    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario  usuario = dao.findByNombreUsuario(username);
        if (usuario == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new MyUserDetails(usuario);
    }
}
