package org.iesvdm.videoclub.service;

import jakarta.persistence.EntityManager;
import org.hibernate.query.Order;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.exception.PeliculaNotFoundException;
import org.iesvdm.videoclub.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
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


    public Map<String, Object> all(int[] datosPaginado) {
        Pageable paginado = PageRequest.of(datosPaginado[0], datosPaginado[1], Sort.by("id").ascending());

        Page<Pelicula> pageAll = this.peliculaRepository.findAll(paginado);
        Map<String, Object> response = new HashMap<>();

        response.put("peliculas", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());

        return response;
    }

    public List<Pelicula> allOrdenCampo(String[] orden) {
        List<Pelicula> peliculas;
        if (orden[1].equalsIgnoreCase("ASC")){
            peliculas = peliculaRepository.findAll(Sort.by(Sort.Direction.ASC, orden[0]));
        } else {
            peliculas = peliculaRepository.findAll(Sort.by(Sort.Direction.DESC, orden[0]));
        }
        return peliculas;
    }


    public List<Pelicula> allOrdenDosCampos(String[] orden1, String[] orden2) {
        List<Pelicula> peliculas;
        List<Pelicula> peliculas2;
        if (orden1[1].equalsIgnoreCase("ASC")){
            peliculas = peliculaRepository.findAll(Sort.by(Sort.Direction.ASC, orden1[0]));
        } else {
            peliculas = peliculaRepository.findAll(Sort.by(Sort.Direction.DESC, orden1[0]));
        }
        if (orden2[1].equalsIgnoreCase("ASC")){
            peliculas2 = peliculaRepository.findAll(Sort.by(Sort.Direction.ASC, orden1[0]));
        } else {
            peliculas2 = peliculaRepository.findAll(Sort.by(Sort.Direction.DESC, orden1[0]));
        }
        return peliculas;
    }


    public Pelicula save(Pelicula pelicula) {
        return this.peliculaRepository.save(pelicula);
    }

    public Pelicula one(Long id) {
        return this.peliculaRepository.findById(id)
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }

    public Pelicula replace(Long id, Pelicula pelicula) {

        return this.peliculaRepository.findById(id).map( p -> (id.equals(pelicula.getId())  ?
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