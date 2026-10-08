
package br.com.aberturaordensservico.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class TesteController {
    
    @GetMapping("/teste")
    public String teste() {
        return "Teste de Controller";
    }
    
}
