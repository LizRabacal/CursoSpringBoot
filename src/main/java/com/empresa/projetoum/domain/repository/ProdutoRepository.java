package com.empresa.projetoum.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.empresa.projetoum.domain.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
