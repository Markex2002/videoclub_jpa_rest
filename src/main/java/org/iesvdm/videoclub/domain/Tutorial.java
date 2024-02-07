package org.iesvdm.videoclub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "titulo", length = 50)
    private String titulo;
    @Column(name = "descripcion", length = 50)
    private String descripcion;
    @Column(name = "publicado")
    private Boolean publicado;

    @Column(nullable = false)
    private Date fechaPublicacion;


    @OneToMany(mappedBy = "tutorial")
    private List<Comentario> comentarios;
}