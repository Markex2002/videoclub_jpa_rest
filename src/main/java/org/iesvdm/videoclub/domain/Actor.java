package org.iesvdm.videoclub.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "actor")

@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long actor_id;

    private String nombre;

    private String apellidos;

    private Date ultimaActualizacion;


    @ManyToMany(
            mappedBy = "actores")
    @ToString.Exclude
    @JsonIgnore
    Set<Pelicula> peliculas = new HashSet<>();
}