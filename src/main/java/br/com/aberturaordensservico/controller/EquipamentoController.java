package br.com.aberturaordensservico.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.aberturaordensservico.dto.EquipamentoRequest;
import br.com.aberturaordensservico.model.Equipamento;
import br.com.aberturaordensservico.service.EquipamentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {

    private final EquipamentoService equipamentoService;

    public EquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @PostMapping
    public ResponseEntity<Equipamento> cadastrar(@Valid @RequestBody EquipamentoRequest equipamento) {
        return equipamentoService.cadastrar(equipamento)
                .map(novoEquipamento -> ResponseEntity.status(HttpStatus.CREATED).body(novoEquipamento)) // 201
                .orElse(ResponseEntity.badRequest().build()); // 400 Bad Request se o setor não existir
    }

    @GetMapping
    public ResponseEntity<List<Equipamento>> listar() {
        return ResponseEntity.ok(equipamentoService.listar()); // 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> buscarPorId(@PathVariable Integer id) {
        return equipamentoService.buscarPorId(id)
                .map(equipamento -> ResponseEntity.ok(equipamento)) // 200
                .orElse(ResponseEntity.notFound().build()); // 404
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> atualizar(@PathVariable Integer id, @Valid @RequestBody EquipamentoRequest equipamento) {
        return equipamentoService.atualizar(id, equipamento)
                .map(equipamentoAtualizado -> ResponseEntity.ok(equipamentoAtualizado)) // 200
                .orElse(ResponseEntity.notFound().build()); // 404
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        if (equipamentoService.excluir(id)) {
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.notFound().build(); // 404
    }
}