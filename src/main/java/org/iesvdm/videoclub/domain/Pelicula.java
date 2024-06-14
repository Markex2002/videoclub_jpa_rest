package org.iesvdm.videoclub.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="pelicula")

//Para que funcione la coleccion Set<Pelicula>
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pelicula {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pelicula")
    private long id_pelicula;

    private String titulo;
    private String descripcion;

    @Column(name = "anyo_lanzamiento")
    @JsonFormat(pattern = "yyyy",  shape = JsonFormat.Shape.STRING)
    private Date anyoLanzamiento;

    @ManyToOne()
    @JoinColumn(name = "id_idioma", nullable = false)
    private Idioma idioma;

    @ManyToOne()
    @JoinColumn(name = "id_idioma_original")
    private Idioma idiomaOriginal;

    @Column(name = "duracion_alquiler")
    private int duracionAlquiler;

    @Column(name = "rental_rate")
    private BigDecimal rentalRate;
    private int duracion;

    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;
    private String clasificacion;

    @Column(name = "caracteristicas_especiales")
    private String caracteristicasEspeciales;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "pelicula_categoria",
            joinColumns = @JoinColumn(name = "id_pelicula", referencedColumnName = "id_pelicula"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria"))
    Set<Categoria> categorias = new HashSet<>();


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "pelicula_actor",
            joinColumns = @JoinColumn(name = "id_pelicula", referencedColumnName = "id_pelicula"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"))
    Set<Actor> actores = new HashSet<>();
}