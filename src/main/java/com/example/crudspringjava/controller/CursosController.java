package com.example.crudspringjava.controller;

import com.example.crudspringjava.model.Curso;
import com.example.crudspringjava.repository.CursoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursosController {

    private final CursoRepository cursoRepository;

    public CursosController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> list(@PathVariable Long id) {
        return cursoRepository.findById(id).map(curso -> ResponseEntity.status(HttpStatus.OK).body(curso))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public List<Curso> list() {
        return cursoRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Curso create(@RequestBody Curso curso) {
        return cursoRepository.save(curso);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Curso> update(@PathVariable Long id,
                                        @RequestBody Curso curso) {
        return cursoRepository.findById(id)
                .map(cursoEncontrado -> {
                    cursoEncontrado.setNome(curso.getNome());
                    cursoEncontrado.setCategoria(curso.getCategoria());
                    Curso atualizado = cursoRepository.save(cursoEncontrado);
                    return ResponseEntity.status(HttpStatus.OK).body(atualizado);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return cursoRepository.findById(id)
                .map(cursoEncontrado -> {
                    cursoRepository.deleteById(id);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).<Void>build();
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
