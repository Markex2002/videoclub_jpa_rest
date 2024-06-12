package org.iesvdm.videoclub;

import jakarta.transaction.Transactional;
import org.iesvdm.videoclub.domain.*;
import org.iesvdm.videoclub.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.*;

@SpringBootTest
class VideoclubApplicationTests {

    @Autowired
    TutorialRepository tutorialRepository;
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    IdiomaRepository idiomaRepository;
    @Autowired
    PeliculaRepository peliculaRepository;
    @Autowired
    SocioRepository socioRepository;
    @Autowired
    TarjetaRepository tarjetaRepository;

    @Test
    void contextLoads() {
    }

    @Test
    @Transactional //En caso de que el Fetch sea de tipo Lazy
    void pruebaOneToManyTutorial(){
         var tutorialList = tutorialRepository.findAll();
         tutorialList.forEach(tutorial -> System.out.println(tutorial));
    }

    @Test
    void pruebaCRUDPeliculaCategoria(){
        ////PRUEBAS DE CREACION PELICULA-CATEGORÍA////
        Idioma idioma = Idioma.builder()
                .id(1L)
                .nombre("Español")
                .peliculasIdioma(new ArrayList<Pelicula>())
                .peliculasIdiomaOriginal(new ArrayList<Pelicula>())
                .ultimaActualizacion(new Date())
                .build();
        idiomaRepository.save(idioma);


        Categoria categoriaTerror = Categoria.builder()
                .id_categoria(0)
                .nombre("Terror")
                .peliculas(new HashSet<Pelicula>())
                .ultimaActualizacion(new Date())
                .build();
        categoriaRepository.save(categoriaTerror);

        Categoria categoriaComedia = Categoria.builder()
                .id_categoria(0)
                .nombre("Comedia")
                .peliculas(new HashSet<Pelicula>())
                .ultimaActualizacion(new Date())
                .build();
        categoriaRepository.save(categoriaComedia);

        Categoria categoriaAccion = Categoria.builder()
                .id_categoria(0)
                .nombre("Accion")
                .peliculas(new HashSet<Pelicula>())
                .ultimaActualizacion(new Date())
                .build();
        categoriaRepository.save(categoriaAccion);

        //Creamos un listado de las categorías
        Set<Categoria> listadoCategorias1 = new HashSet<>();
        listadoCategorias1.add(categoriaComedia);
        listadoCategorias1.add(categoriaTerror);

        Set<Categoria> listadoCategorias2 = new HashSet<>();
        listadoCategorias2.add(categoriaTerror);
        listadoCategorias2.add(categoriaAccion);

        Pelicula pelicula1 = Pelicula.builder()
                .id_pelicula(1)
                .categorias(listadoCategorias1)
                .duracionAlquiler(0)
                .descripcion("Lorem Ipsum")
                .anyoLanzamiento(new Date())
                .actores(new HashSet<Actor>())
                .clasificacion("+12")
                .duracion(0)
                .titulo("MortadeloYFilemon, que locuron")
                .idioma(idioma)
                .idiomaOriginal(idioma)
                .rentalRate(BigDecimal.ZERO)
                .replacementCost(BigDecimal.ZERO)
                .duracionAlquiler(0)
                .caracteristicasEspeciales("Es tronchante")
                .build();
        peliculaRepository.save(pelicula1);

        Pelicula pelicula2 = Pelicula.builder()
                .id_pelicula(2)
                .categorias(listadoCategorias2)
                .duracionAlquiler(0)
                .descripcion("Lorem Ipsum")
                .anyoLanzamiento(new Date())
                .actores(new HashSet<Actor>())
                .clasificacion("+12")
                .duracion(0)
                .titulo("ZipiYZape, menudo disparate")
                .idioma(idioma)
                .idiomaOriginal(idioma)
                .rentalRate(BigDecimal.ZERO)
                .replacementCost(BigDecimal.ZERO)
                .duracionAlquiler(0)
                .caracteristicasEspeciales("Es hilarante")
                .build();
        peliculaRepository.save(pelicula2);


        //Prueba para editar una entidad ya creada
        pelicula2.setDescripcion("ANSMDOHASDUHASUIOD");
        peliculaRepository.save(pelicula2);


        //Prueba para borrar una Entidad
        peliculaRepository.deleteById(2L);



    }



    @Test
    @Transactional
    void pruebaCreacionSocioTarjeta(){
        Tarjeta tarjeta = new Tarjeta();
        Date date = new Date(2014, Calendar.FEBRUARY,12);
        tarjeta.setCaducidad(date);

        Socio socio = new Socio();
        socio.setNombre("Marco");
        socio.setApellidos("Martin");

        tarjeta.setSocio(socio);
        socio.setTarjeta(tarjeta);

        socioRepository.save(socio);
        tarjetaRepository.save(tarjeta);
    }




    @Test
    @Transactional
    void pruebaGabarOneToMany(){
        Tutorial tutorial = Tutorial.builder()
                .id(0)
                    .titulo("Titulo de tutorial")
                    .publicado(true)
                    .descripcion("Descripcion de Tutorial")
                .fechaPublicacion(new Date())
                .comentarios(new HashSet<>())
                .build();

        Comentario comentario1 = Comentario.builder()
                .texto("Comentario 1")
                .build();

        Comentario comentario2 = new Comentario(0, "texto", null);
        tutorial.addComentario(comentario1);

        tutorialRepository.save(tutorial);
        tutorialRepository.flush();

        tutorial.addComentario(comentario2);
        tutorialRepository.save(tutorial);
        tutorialRepository.flush();
    }


    @Test
    @Transactional
    void PruebaEliminarComentario(){
        var optionalTutorial = this.tutorialRepository.findById(1L);

        optionalTutorial.ifPresent(tutorial -> {
            tutorial
                    .getComentarios()
                    .forEach(System.out::println);

            var optionalComentario = tutorial.getComentarios().stream().findFirst();
            tutorial.removeComentario(optionalComentario.get());

            this.tutorialRepository.save(tutorial);
        });


        this.tutorialRepository.delete(optionalTutorial.get());
        this.tutorialRepository.flush();
    }
}