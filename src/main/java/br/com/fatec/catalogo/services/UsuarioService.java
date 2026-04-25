package br.com.fatec.catalogo.services;

import br.com.fatec.catalogo.models.UsuarioModel;
import br.com.fatec.catalogo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void salvar(UsuarioModel usuario) {
        if (repository.existsByNome(usuario.getNome())) {
            throw new RuntimeException("Usuário ja existe");
        }
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        repository.save(usuario);
    }
}
