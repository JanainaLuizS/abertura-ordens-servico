package br.com.aberturaordensservico.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.aberturaordensservico.dto.EncerramentoOrdemServicoRequest;
import br.com.aberturaordensservico.dto.OrdemServicoRequest;
import br.com.aberturaordensservico.model.Equipamento;
import br.com.aberturaordensservico.model.OrdemServico;
import br.com.aberturaordensservico.repository.EquipamentoRepository;
import br.com.aberturaordensservico.repository.OrdemServicoRepository;

import java.util.List;
import java.time.LocalDateTime;

@Service
public class OrdemServicoService {

    private final EquipamentoRepository equipamentoRepository;
    private final OrdemServicoRepository ordemServicoRepository;

    public OrdemServicoService(EquipamentoRepository equipamentoRepository,
            OrdemServicoRepository ordemServicoRepository) {
        this.equipamentoRepository = equipamentoRepository;
        this.ordemServicoRepository = ordemServicoRepository;
    }

    public Optional<OrdemServico> cadastrar(OrdemServicoRequest ordemServico) {
    //não precisa validar aqui, pois o controller já faz a validação do request body com @Valid e as anotações de validação na classe OrdemServicoRequest. Se o request body não atender às validações, o Spring retornará automaticamente um erro 400 Bad Request antes de chegar a este ponto.
        Optional<Equipamento> equipamentoOpt = equipamentoRepository.findById(ordemServico.getEquipamentoId());
        if (equipamentoOpt.isPresent()) {

            OrdemServico novaOrdemServico = new OrdemServico(
                    ordemServico.getDescricao(),
                    LocalDateTime.now(), // Define a data de abertura como a data e hora atual, É passado como parâmetro 
                   // para o construtor da classe OrdemServico, que espera um objeto LocalDateTime representando a data 
                   // e hora de abertura da ordem de serviço.Sempre que quiser a data e hora atual, coloca aqui LocalDateTime.now() e não precisa passar no request.

                    equipamentoOpt.get());
            return Optional.of(ordemServicoRepository.save(novaOrdemServico));
        }

        return Optional.empty(); // Equipamento não existe
    }

    //ordem.setDataAbertura(LocalDateTime.now())

    public List<OrdemServico> listar() {
        return ordemServicoRepository.findAll();
    }

    public Optional<OrdemServico> buscarPorId(Integer id) {
        return ordemServicoRepository.findById(id);
    }

    public Optional<OrdemServico> encerrarOrdemServico(Integer id, EncerramentoOrdemServicoRequest dados) {
        
        Optional<OrdemServico> ordemServicoOpt = ordemServicoRepository.findById(id);
        if (ordemServicoOpt.isPresent()) {
            OrdemServico ordemServico = ordemServicoOpt.get();

            if (ordemServico.getDataEncerramento() != null) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Ordem de serviço já encerrada"); //409 Conflict , significa que a requisição não pôde ser concluída devido a um conflito com o estado atual do recurso. Nesse caso, o conflito é que a ordem de serviço já foi encerrada, então não faz sentido tentar encerrá-la novamente.
            }

            ordemServico.setSolucao(dados.getSolucao());
            ordemServico.setDataEncerramento(LocalDateTime.now());
            return Optional.of(ordemServicoRepository.save(ordemServico));
        }
        return Optional.empty(); // Ordem de serviço não encontrada
    }

    public List<OrdemServico> listarOrdensServicoNaoEncerradas() { 
        //mostra todas as ordens de serviço que ainda não foram encerradas, ou seja, aquelas cujo campo dataEncerramento é nulo. OS abertas e não encerradas são aquelas que ainda estão em andamento ou aguardando solução. Esse método é útil para monitorar o status das ordens de serviço e identificar quais ainda precisam ser concluídas.
        return ordemServicoRepository.findByDataEncerramentoIsNull();
    }

}
