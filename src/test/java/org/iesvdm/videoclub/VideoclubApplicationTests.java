package org.iesvdm.videoclub;

import jakarta.transaction.Transactional;
import org.iesvdm.videoclub.domain.Comentario;
import org.iesvdm.videoclub.domain.Tutorial;
import org.iesvdm.videoclub.repository.TutorialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashSet;

@SpringBootTest
class VideoclubApplicationTests {

    @Autowired
    TutorialRepository tutorialRepository;

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