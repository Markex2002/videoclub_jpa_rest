package org.iesvdm.videoclub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Socio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dni")
    //Si creamos un AutoIncrement usando @GeneratedValue, aseguremonos de que el atributo
    //es int, o long, pues los autoIncrement son númericos
    private long dni;

    @Column(name = "nombre", length = 25)
    private String nombre;
    @Column(name = "apellidos", length = 50)
    private String apellidos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "numero_Tarjeta", foreignKey = @ForeignKey(name = "FK_numero_Tarjeta"))
    private Tarjeta tarjeta;
}