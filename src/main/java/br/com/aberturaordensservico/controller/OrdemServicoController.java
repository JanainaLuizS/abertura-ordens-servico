package br.com.aberturaordensservico.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aberturaordensservico.dto.OrdemServicoRequest;
import br.com.aberturaordensservico.model.OrdemServico;
import br.com.aberturaordensservico.service.EquipamentoService;
import br.com.aberturaordensservico.service.OrdemServicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;


    public OrdemServicoController(OrdemServicoService ordemServicoService, EquipamentoService equipamentoService) {
        this.ordemServicoService = ordemServicoService;
    }
    

      @PostMapping
   public ResponseEntity<OrdemServico> cadastrar(@Valid @RequestBody OrdemServicoRequest ordemServico) {
    return ordemServicoService.cadastrar(ordemServico)
            .map(novaOrdemServico -> ResponseEntity.status(201).body(novaOrdemServico)) // 201 Created
            .orElse(ResponseEntity.badRequest().build()); // 400 Bad Request se o equipamento não existir
        // <> entre esses sinais do ResponseEntity é o tipo de dado que será retornado no corpo da resposta, e o corpo da resposta é o que está dentro do body()
        // A novaOrdemServico é o objeto da ordem de serviço recém-criada. Quando eu não sei o que vai retornar, coloco <?> que significa "qualquer tipo". Mas aqui sabemos que é OrdemServico, então colocamos <OrdemServico>.Ele vai me trazer a ordem de serviço recém-criada no corpo da resposta, caso seja criada com sucesso. Se não for criada, ele vai me trazer um corpo vazio, mas com o status 400 Bad Request.
    }


    @GetMapping
    public ResponseEntity<Iterable<OrdemServico>> listar() {
        return ResponseEntity.ok(ordemServicoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> buscarPorId(@PathVariable Integer id) {
        return ordemServicoService.buscarPorId(id)
                .map(ordemServico -> ResponseEntity.ok(ordemServico)) // 200
                .orElse(ResponseEntity.notFound().build()); // 404
        
        
    }
  
   
}
