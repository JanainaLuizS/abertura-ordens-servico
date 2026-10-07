package br.com.aberturaordensservico.service;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import br.com.aberturaordensservico.model.Equipamento;
import br.com.aberturaordensservico.model.Setor;
import br.com.aberturaordensservico.repository.EquipamentoRepository;
import br.com.aberturaordensservico.repository.SetorRepository;

@Service
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;
    private final SetorRepository setorRepository;

   
    public EquipamentoService(EquipamentoRepository equipamentoRepository, SetorRepository setorRepository) {
        this.equipamentoRepository = equipamentoRepository;
        this.setorRepository = setorRepository;
    }

    public Optional<Equipamento> cadastrar(Equipamento equipamento) {
       
        if (equipamento.getSetor() != null) {
            Optional<Setor> setorOpt = setorRepository.findById(equipamento.getSetor().getId());
            if (setorOpt.isPresent()) {
                return Optional.of(equipamentoRepository.save(equipamento));
            }
        }
        return Optional.empty(); // Setor não existe
    }

    public List<Equipamento> listar() {
        return equipamentoRepository.findAll();
    }

    public Optional<Equipamento> buscarPorId(Integer id) {
        return equipamentoRepository.findById(id);
    }

    public Optional<Equipamento> atualizar(Integer id, Equipamento equipamentoAtualizado) {
        Optional<Equipamento> equipamentoExistente = equipamentoRepository.findById(id);

        if (equipamentoExistente.isEmpty()) {
            return Optional.empty();
        }


        Optional<Setor> setorExistente = setorRepository.findById(equipamentoAtualizado.getSetor().getId());
        
        if (setorExistente.isEmpty()) {
            return Optional.empty();
        }

        Equipamento equipamento = equipamentoExistente.get();
        equipamento.setNome(equipamentoAtualizado.getNome());
        equipamento.setNumeroPatrimonio(equipamentoAtualizado.getNumeroPatrimonio());
        equipamento.setSetor(setorExistente.get());
        return Optional.of(equipamentoRepository.save(equipamento));
    }

    public boolean excluir(Integer id) {
        if (equipamentoRepository.existsById(id)) {
            equipamentoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}