package org.iesvdm.videoclub.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.service.CategoriaService;
import org.iesvdm.videoclub.service.PeliculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/categorias")
public class CategoriaV1Controller {
    private final CategoriaService categoriaService;

    public CategoriaV1Controller(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping(value = {"","/"}, params ={"!pagina", "!tamanio"})
    public List<Categoria> all() {
        log.info("Accediendo a todas las categorias");
        return this.categoriaService.all();
    }


    @GetMapping(value ={"", "/"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam(value = "pagina", defaultValue = "0") int pagina
        , @RequestParam(value = "tamanio", defaultValue = "3") int tamanio){

        log.info("Accediendo a todas las categorias con Paginacion");
        Map<String, Object> responseAll = this.categoriaService.all(pagina, tamanio);

        return ResponseEntity.ok(responseAll);
    }

    @PostMapping({"","/"})
    public Categoria newCategoria(@RequestBody Categoria categoria) {
        return this.categoriaService.save(categoria);
    }

    @GetMapping("/{id}")
    public Categoria one(@PathVariable("id") Long id) {
        return this.categoriaService.one(id);
    }

    @PutMapping("/{id}")
    public Categoria replaceCategoria(@PathVariable("id") Long id, @RequestBody Categoria categoria) {
        return this.categoriaService.replace(id, categoria);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable("id") Long id) {
        this.categoriaService.delete(id);
    }
}