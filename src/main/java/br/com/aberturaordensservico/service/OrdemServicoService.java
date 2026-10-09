package br.com.aberturaordensservico.service;

import java.nio.file.OpenOption;
import java.util.Optional;

import org.springframework.stereotype.Service;

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


   public OrdemServicoService(EquipamentoRepository equipamentoRepository, OrdemServicoRepository ordemServicoRepository) {
        this.equipamentoRepository = equipamentoRepository;
        this.ordemServicoRepository = ordemServicoRepository;
    }

    public Optional<OrdemServico> cadastrar(OrdemServicoRequest ordemServico) {
       
        if (ordemServico.getEquipamentoId() != null) {

            Optional<Equipamento> equipamentoOpt = equipamentoRepository.buscarPorId(ordemServico.getEquipamentoId());
            if (equipamentoOpt.isPresent()) { 

                OrdemServico novaOrdemServico = new OrdemServico(
                        ordemServico.getDescricao(),
                        ordemServico.getDataAbertura(),
                        
                        equipamentoOpt.get ()
                );
                return Optional.of(ordemServicoRepository.save(novaOrdemServico));
            }

        }
        return Optional.empty(); // Equipamento não existe
    }

    public List<OrdemServico> listar() {
        return ordemServicoRepository.findAll();
    }

    public Optional<OrdemServico> buscarPorId(Integer id) {
        return ordemServicoRepository.findById(id);
    }

    public Optional<OrdemServico> atualizar(Integer id, OrdemServicoRequest ordemServico2) {
        Optional<OrdemServico> ordemServicoExistente = ordemServicoRepository.findById(id);

        if (ordemServicoExistente.isEmpty()) {
            return Optional.empty();
        }

        Optional<Equipamento> equipamentoExistente = equipamentoRepository.buscarPorId(ordemServico2.getEquipamentoId());

        if (equipamentoExistente.isEmpty()) {
            return Optional.empty();
        }

        OrdemServico ordemServicoAtualizada = ordemServicoExistente.get();
        ordemServicoAtualizada.setDescricao(ordemServico2.getDescricao());
        ordemServicoAtualizada.setDataAbertura(ordemServico2.getDataAbertura());
        ordemServicoAtualizada.setEquipamento(equipamentoExistente.get());

        return Optional.of(ordemServicoRepository.save(ordemServicoAtualizada));


            



    
    
}
