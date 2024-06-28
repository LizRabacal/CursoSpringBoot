package com.empresa.projetoum.domain.repository;
/* import java.sql.ResultSet;
import java.sql.SQLException; */
import java.util.List;
/* 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional; */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.empresa.projetoum.domain.entities.Cliente;
/* 
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
 */

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    //Query Methods:
    List<Cliente> findByNomeLike (String nome);
    boolean existsByNome (String nome);

    //Query personalizada:
    @Query("select c from Cliente c where c.nome like %:nome%")
    List<Cliente> procurarPorNome(@Param("nome") String nome);


    @Query("select c from Cliente c left join fetch c.pedidos p where c.id = :id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);



    /* @Autowired
    EntityManager entityManager;

    /*
     * @Autowired
     * private JdbcTemplate jdbcTemplate;
     

    @Transactional
    public Cliente salvar_cliente(Cliente cli) {
        entityManager.persist(cli);
        return cli;

    }

    @Transactional(readOnly = true)
    public List<Cliente> listarClientes() {
        return entityManager.createQuery("from Cliente", Cliente.class).getResultList();

        // return jdbcTemplate.query(SELECTALL, obterClientesMapper());

    }

    @Transactional
    public void deletar_Cliente(Integer id) {
        Cliente cli = entityManager.find(Cliente.class, id);
        if (entityManager.contains(cli)) entityManager.remove(cli);
        // jdbcTemplate.update(DELETE, id);

    }

    @Transactional
    public Cliente atualizar_Cliente(Cliente cli) {
        /*
         * jdbcTemplate.update(UPDATE, nome, cli.getId());
         * 
         
        entityManager.merge(cli);
        return cli;

    }

    @Transactional(readOnly = true)
    public List<Cliente> buscar_por_nome(String nome) {
        // return jdbcTemplate.query(SELECTALL.concat("WHERE NOME LIKE ?"), new
        // Object[]{"%" + nome + "%"}, obterClientesMapper());
        String jpql = "select c from Cliente like c.nome = :nome";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();

    }
    /*
     * public RowMapper<Cliente> obterClientesMapper(){
     * return new RowMapper<Cliente>(){
     * 
     * @Override
     * public Cliente mapRow(ResultSet resultSet, int i) throws SQLException{
     * return new Cliente(resultSet.getString("nome"), resultSet.getInt("id"));
     * }
     * };
     * }
     */
 
}
