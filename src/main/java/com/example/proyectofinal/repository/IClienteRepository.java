
package com.example.proyectofinal.repository;

import com.example.proyectofinal.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joaco
 */
@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long>{
    
}
