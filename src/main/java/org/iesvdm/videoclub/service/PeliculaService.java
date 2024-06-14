package org.iesvdm.videoclub.service;

import jakarta.persistence.EntityManager;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.exception.PeliculaNotFoundException;
import org.iesvdm.videoclub.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PeliculaService {

    @Autowired
    EntityManager entityManager;

    private final PeliculaRepository peliculaRepository;

    public PeliculaService(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    public List<Pelicula> all() {
        return this.peliculaRepository.findAll();
    }


    public Map<String, Object> all(int pagina, int tamanio) {
        Pageable paginado = PageRequest.of(pagina, tamanio, Sort.by("id_pelicula").ascending());

        Page<Pelicula> pageAll = this.peliculaRepository.findAll(paginado);
        Map<String, Object> response = new HashMap<>();

        response.put("peliculas", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());

        return response;
    }


    public Pelicula save(Pelicula pelicula) {
        return this.peliculaRepository.save(pelicula);
    }

    public Pelicula one(Long id) {
        return this.peliculaRepository.findById(id)
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }

    public Pelicula replace(Long id, Pelicula pelicula) {

        return this.peliculaRepository.findById(id).map( p -> (id.equals(pelicula.getId_pelicula())  ?
                                                            this.peliculaRepository.save(pelicula) : null))
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }

    public void delete(Long id) {
        this.peliculaRepository.findById(id).map(p -> {this.peliculaRepository.delete(p);
                                                        return p;})
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }



    public Page<Pelicula> findByTituloContainingIgnoreCaseOrderByTituloAsc(String tituloBuscar){
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "titulo"));

        return this.peliculaRepository.findByTituloContainingIgnoreCaseOrderByTituloAsc(tituloBuscar, pageable);
    }
}