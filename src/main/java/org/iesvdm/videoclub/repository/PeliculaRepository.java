package org.iesvdm.videoclub.repository;

import org.iesvdm.videoclub.domain.Pelicula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    public Page<Pelicula> findByTituloContainingIgnoreCaseOrderByTituloAsc(String tituloBuscar, Pageable pageable);
}