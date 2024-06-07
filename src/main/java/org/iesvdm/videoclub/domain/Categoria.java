package org.iesvdm.videoclub.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categoria")

@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private long id_categoria;

    @NaturalId
    @EqualsAndHashCode.Include
    private String nombre;

    @ManyToMany(
            mappedBy = "categorias")
    @ToString.Exclude
    @JsonIgnore
    Set<Pelicula> peliculas = new HashSet<>();

    @Column(name = "ultima_actualizacion")
    @JsonFormat(pattern = "yyyy-MM-dd-HH:mm:ss",  shape = JsonFormat.Shape.STRING)
    private Date ultimaActualizacion;


}