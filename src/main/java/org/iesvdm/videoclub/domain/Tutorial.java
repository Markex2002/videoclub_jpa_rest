package org.iesvdm.videoclub.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @Column(name = "titulo", length = 50)
    private String titulo;
    @Column(name = "descripcion", length = 50, nullable = true)
    private String descripcion;
    @Column(name = "publicado")
    private Boolean publicado;

    @Column(nullable = false)
    private Date fechaPublicacion;


    //Lista de Comentarios
    @OneToMany(mappedBy = "tutorial", cascade = CascadeType.ALL)
    private Set<Comentario> comentarios = new HashSet<>();


    public Tutorial addComentario(Comentario comentario){
        this.comentarios.add(comentario);
        comentario.setTutorial(this);
        return this;
    }

    public Tutorial removeComentario(Comentario comentario){
        this.comentarios.remove(comentario);
        comentario.setTutorial(null);
        return this;
    }



}