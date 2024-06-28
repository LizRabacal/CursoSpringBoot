package com.empresa.projetoum.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.empresa.projetoum.domain.user.User;
import java.util.List;
import java.util.Optional;



public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByLogin(String login);

    
}
