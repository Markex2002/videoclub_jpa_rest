package org.iesvdm.videoclub.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesvdm.videoclub.domain.Pelicula;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class CategoriaDTO implements Comparable<CategoriaDTO>{
    private long id_categoria;
    private String nombre;
    Set<Pelicula> peliculas = new HashSet<>();
    private Date ultimaActualizacion;
    private int peliculasPorCategoria;


    @Override
    public int compareTo(CategoriaDTO o) {
        return o.peliculasPorCategoria - this.peliculasPorCategoria;
    }
}