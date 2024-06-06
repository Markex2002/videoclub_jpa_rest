package org.iesvdm.videoclub;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.videoclub.domain.Socio;
import org.iesvdm.videoclub.domain.Tarjeta;
import org.iesvdm.videoclub.repository.SocioRepository;
import org.iesvdm.videoclub.repository.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;
import java.util.Date;

@Slf4j
@SpringBootApplication
public class VideoclubApplication implements CommandLineRunner {

    @Autowired
    SocioRepository socioRepository;

    @Autowired
    TarjetaRepository tarjetaRepository;


    public static void main(String[] args) {
        SpringApplication.run(VideoclubApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("\n\n\n*******************************");
        log.info("*Prueba de arranque SocioTarjeta*");
        log.info("*******************************");


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
}