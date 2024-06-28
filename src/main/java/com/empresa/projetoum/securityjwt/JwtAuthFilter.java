package com.empresa.projetoum.securityjwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.empresa.projetoum.service.UserServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter{

    private JwtService jwtService;
    private UserServiceImpl userservice;


    @Autowired
    public JwtAuthFilter(JwtService jwtService, UserServiceImpl userservice) {
        this.jwtService = jwtService;
        this.userservice = userservice;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Obtém o cabeçalho Authorization da requisição
        String authHeader = request.getHeader("Authorization");

        // Verifica se o cabeçalho Authorization existe e começa com "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // Extrai apenas o token JWT removendo o prefixo "Bearer "
            String token = authHeader.substring(7); // O tamanho de "Bearer " é 7

            // Verifica se o token JWT é válido usando o serviço JwtService
            if (jwtService.tokenValido(token)) {
                // Obtém o login do usuário a partir do token JWT
                String loginUser = jwtService.obterLoginUsuario(token);

                // Carrega os detalhes do usuário usando o UserServiceImpl
                UserDetails usuario = userservice.loadUserByUsername(loginUser);

                // Cria uma autenticação do tipo UsernamePasswordAuthenticationToken com os
                // detalhes do usuário
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario,
                        null, usuario.getAuthorities());

                // Define os detalhes da autenticação com base na requisição atual
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Define a autenticação no contexto de segurança
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Continua com a cadeia de filtros
        filterChain.doFilter(request, response);
    }



    //CODIGO ERRADOOOOOOOOOOOOO:

/* 
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
    HttpServletResponse response, 
    FilterChain filterChain)
    throws ServletException, IOException {

        String auth = response.getHeader("Authorization");

        if(auth != null && auth.startsWith("Bearer")){
            String token = auth.replace("Bearer", "");
            if(jwtService.tokenValido(token)){
                String loginUser = jwtService.obterLoginUsuario(token);
                UserDetails usuario = userservice.loadUserByUsername(loginUser);
                UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(user);
            }
        }

        filterChain.doFilter(request, response);

    }  */

    


    
}