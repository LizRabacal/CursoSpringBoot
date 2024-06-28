 package com.empresa.projetoum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.empresa.projetoum.exception.*;
import com.empresa.projetoum.domain.repository.UserRepository;
import com.empresa.projetoum.domain.user.User;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;



    @Transactional
    public User salvar (User usuario){
        return repository.save(usuario);
    }

    public UserDetails autenticar(User user){
        UserDetails usuario = loadUserByUsername(user.getLogin());
        boolean senhasBatem = encoder.matches(user.getPassword(), usuario.getPassword());
        if(senhasBatem){
            return usuario;
        }
        throw new senhaInvalidaException();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getLogin())
                .password(user.getPassword())
                .authorities(user.getAuthorities()) // Adicione as autoridades conforme necessário
                .accountExpired(!user.isAccountNonExpired())
                .accountLocked(!user.isAccountNonLocked())
                .credentialsExpired(!user.isCredentialsNonExpired())
                .disabled(!user.isEnabled())
                .build();
    }


}
 