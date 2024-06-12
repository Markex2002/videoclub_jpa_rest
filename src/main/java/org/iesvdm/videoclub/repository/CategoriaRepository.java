package org.iesvdm.videoclub.repository;

import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    public List<Categoria> findByNombreContainingIgnoreCase(String titulo);
    public List<Categoria> findByNombreContainingIgnoreCaseOrderByNombreAsc(String titulo);
    public List<Categoria> findByNombreContainingIgnoreCaseOrderByNombreDesc(String titulo);
}