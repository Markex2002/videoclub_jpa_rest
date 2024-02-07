package org.iesvdm.videoclub;

import jakarta.transaction.Transactional;
import org.iesvdm.videoclub.repository.TutorialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class VideoclubApplicationTests {

    @Autowired
    TutorialRepository tutorialRepository;

    @Test
    void contextLoads() {
    }

    @Test
    @Transactional
    void pruebaOneToManyTutorial(){
         var tutorialList = tutorialRepository.findAll();
         tutorialList.forEach(tutorial -> System.out.println(tutorial));
    }
}