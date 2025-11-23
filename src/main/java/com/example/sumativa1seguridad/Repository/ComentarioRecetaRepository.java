package com.example.sumativa1seguridad.Repository;

import com.example.sumativa1seguridad.Entity.ComentarioReceta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRecetaRepository extends JpaRepository<ComentarioReceta, Long> {

    List<ComentarioReceta> findByNombreRecetaOrderByFechaCreacionDesc(String nombreReceta);
}
