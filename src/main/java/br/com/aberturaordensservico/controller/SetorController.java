package br.com.aberturaordensservico.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aberturaordensservico.model.Setor;
import br.com.aberturaordensservico.service.SetorService;
import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/setores")

public class SetorController {

    private final SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @PostMapping 
    public ResponseEntity<Setor> cadastrar( @Valid @RequestBody Setor setor) {
        Setor novoSetor = setorService.cadastrar(setor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoSetor); // Status 201
    }

    @GetMapping
    public ResponseEntity<List<Setor>> listar() {
        return ResponseEntity.ok(setorService.listar()); // Status 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<Setor> buscarPorId(@PathVariable Integer id) {
        return setorService.buscarPorId(id)
                .map(setor -> ResponseEntity.ok(setor)) // Status 200
                .orElse(ResponseEntity.notFound().build()); // Status 404
    }

    @PutMapping("/{id}")
    public ResponseEntity<Setor> atualizar(@Valid @PathVariable Integer id, @RequestBody Setor setor) {
        return setorService.atualizar(id, setor)
                .map(setorAtualizado -> ResponseEntity.ok(setorAtualizado)) // Status 200
                .orElse(ResponseEntity.notFound().build()); // Status 404
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        if (setorService.excluir(id)) {
            return ResponseEntity.noContent().build(); // Status 204
        }
        return ResponseEntity.notFound().build(); // Status 404
    }
}
