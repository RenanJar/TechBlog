package com.example.blogpessoal.Security;
import java.util.Optional;

import com.example.blogpessoal.Model.UsuarioModel;
import com.example.blogpessoal.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<UsuarioModel> usuario = userRepository.findByUsuario(userName);

        usuario.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));

        return usuario.map(UserDetailsImpl::new).get();
    }
}
