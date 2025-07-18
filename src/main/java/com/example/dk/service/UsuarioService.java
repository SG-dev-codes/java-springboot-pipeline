package com.example.dk.service;

import com.example.dk.model.Usuario;
import com.example.dk.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService{
    private final UsuarioRepository usuarioRepository;


    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.findAll();
    }


    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            usuario.setId(id);
            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }
    public void eliminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }
}
