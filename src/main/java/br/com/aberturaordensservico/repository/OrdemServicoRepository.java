package br.com.aberturaordensservico.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aberturaordensservico.model.OrdemServico;

@Repository 
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Integer> {


public List<OrdemServico> findByDataEncerramentoIsNull(); 
// Método para buscar ordens de serviço que ainda não foram encerradas, é chamada no service, no método listarOrdensServicoNaoEncerradas(), que é chamado no controller, no endpoint /ordens-servico/abertas. Ele retorna uma lista de ordens de serviço cujo campo dataEncerramento é nulo, ou seja, ainda não foram encerradas. Esse método é útil para monitorar o status das ordens de serviço e identificar quais ainda precisam ser concluídas.

        
}
