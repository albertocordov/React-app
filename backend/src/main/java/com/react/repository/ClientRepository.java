package com.react.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.react.model.Cliente;

@Repository
public interface ClientRepository extends JpaRepository<Cliente, Long> {
}
