package org.iesvdm.videoclub.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.service.PeliculaService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/peliculas")
public class PeliculaController {
    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping(value = {"","/"}, params ={"titulo"})
    public Page<Pelicula> allByTitulo(@RequestParam("titulo") String titulo) {
        return this.peliculaService.findByTituloContainingIgnoreCaseOrderByTituloAsc(titulo);
    }

    @GetMapping(value = {"","/"}, params ={"!paginado", "!orden", "!orden1", "!orden2"})
    public List<Pelicula> all() {
        log.info("Accediendo a todas las películas");
        return this.peliculaService.all();
    }


    @GetMapping(value ={"", "/"}, params = {"paginado", "!orden", "!orden1", "!orden2"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam(value = "paginado", defaultValue = "0") int[] paginado){

        log.info("Accediendo a todas las Peliculas con Paginacion");
        Map<String, Object> responseAll = this.peliculaService.all(paginado);

        return ResponseEntity.ok(responseAll);
    }

    @GetMapping(value ={"", "/"}, params = {"orden", "!orden1", "!orden2"})
    public List<Pelicula> allCampoOrden(@RequestParam(value = "orden", defaultValue = "") String[] orden){
        return peliculaService.allOrdenCampo(orden);
    }

    @GetMapping(value ={"", "/"}, params = {"orden1", "orden2"})
    public List<Pelicula> allCampoDobleOrden(@RequestParam(value = "orden1", defaultValue = "") String[] orden1,
                                             @RequestParam(value = "orden2", defaultValue = "") String[] orden2){
        return peliculaService.allOrdenDosCampos(orden1, orden2);
    }







    @PostMapping({"","/"})
    public Pelicula newPelicula(@RequestBody Pelicula pelicula) {
        return this.peliculaService.save(pelicula);
    }

    @GetMapping("/{id}")
    public Pelicula one(@PathVariable("id") Long id) {
        return this.peliculaService.one(id);
    }

    @PutMapping("/{id}")
    public Pelicula replacePelicula(@PathVariable("id") Long id, @RequestBody Pelicula pelicula) {
        return this.peliculaService.replace(id, pelicula);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable("id") Long id) {
        this.peliculaService.delete(id);
    }
}