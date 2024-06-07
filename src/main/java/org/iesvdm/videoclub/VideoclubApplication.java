package org.iesvdm.videoclub;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.videoclub.domain.*;
import org.iesvdm.videoclub.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@SpringBootApplication
public class VideoclubApplication implements CommandLineRunner {

    @Autowired
    SocioRepository socioRepository;
    @Autowired
    TarjetaRepository tarjetaRepository;
    @Autowired
    PeliculaRepository peliculaRepository;
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    IdiomaRepository idiomaRepository;


    public static void main(String[] args) {
        SpringApplication.run(VideoclubApplication.class, args);
    }



    @Override
    public void run(String... args) throws Exception {
        log.info("\n\n\n*******************************");
        log.info("*Prueba de arranque");
        log.info("*******************************");
    }


}