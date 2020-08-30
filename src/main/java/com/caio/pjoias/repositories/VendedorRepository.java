package com.caio.pjoias.repositories;

import com.caio.pjoias.models.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, String> {
}
