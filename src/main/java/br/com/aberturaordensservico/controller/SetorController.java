package br.com.aberturaordensservico.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aberturaordensservico.model.Setor;
import br.com.aberturaordensservico.service.SetorService;

@RestController 
@RequestMapping ("/setores")

public class SetorController {

    private final SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @PostMapping 
    public ResponseEntity<Setor> criarSetor(@RequestBody Setor setor) {
        Setor novoSetor = setorService.criarSetor(setor);
        
        return ResponseEntity.status(201).body(novoSetor);  
    }


    
}
