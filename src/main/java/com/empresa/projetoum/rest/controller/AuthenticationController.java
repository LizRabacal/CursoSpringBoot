package com.empresa.projetoum.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.projetoum.domain.repository.UserRepository;
//import com.empresa.projetoum.service.UserServiceImpl;
import com.empresa.projetoum.domain.user.User;
import com.empresa.projetoum.rest.dto.*;
import com.empresa.projetoum.service.UserServiceImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.empresa.projetoum.exception.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.empresa.projetoum.securityjwt.JwtService;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    @Autowired
    private  JwtService jwtservice;

    @Autowired
    private PasswordEncoder PE;

    @Autowired
    public UserServiceImpl service;

    @Autowired
    public UserRepository repository;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User Register(@RequestBody User data) {
        data.setPassword(PE.encode(data.getPassword()));
        service.salvar(data);
        return data;
    }


    @PostMapping("/login")
    public TokenDTO login(@RequestBody CredenciaisDTO credenciais) {
        try{
            User u = new User();
            u.setPassword(credenciais.getSenha());
            u.setLogin(credenciais.getLogin());
            UserDetails uautenticado = service.autenticar(u);
            String token = jwtservice.gerarToken(u);
            System.out.println("TA VALIDO????" + jwtservice.tokenValido(token));            
            return new TokenDTO(u.getLogin(), token);

        }catch(UsernameNotFoundException | senhaInvalidaException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());

        }
        
    }


    //ROTAS PRA TESTAR AUTENTICAÇÃO:

    //ROTA Q PRECISA DE AUTENTICAÇAO ADMIN PRA SER ACESSADA
    @PostMapping("/isAuth")
    public boolean isAuth(@RequestBody TokenDTO token){
        return jwtservice.tokenValido(token.getToken());

    }


    // ROTA LIBERADA PARA USUARIOS AUTENTICADOS (USER OU ADMIN)
    @GetMapping("/getUserLogin/{token}")
    public String getLogin(@PathVariable String token){
        return jwtservice.obterLoginUsuario(token);

    }

    //ROTA LIBERADA PARA TODOS
    @PostMapping("/isAuthLiberada")
    public boolean isAuth2(@RequestBody TokenDTO token) {
        return jwtservice.tokenValido(token.getToken());

    }

 

}
