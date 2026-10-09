package br.com.aberturaordensservico.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aberturaordensservico.model.OrdemServico;

@Repository 
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Integer> {
}

        

