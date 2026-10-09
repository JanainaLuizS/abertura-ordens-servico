package br.com.aberturaordensservico.service;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import br.com.aberturaordensservico.dto.EquipamentoRequest;
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

    public Optional<Equipamento> cadastrar(EquipamentoRequest equipamento) {
       
        if (equipamento.getSetorId() != null) {
            Optional<Setor> setorOpt = setorRepository.findById(equipamento.getSetorId());
            if (setorOpt.isPresent()) { 

                Equipamento novoEquipamento = new Equipamento(
                        equipamento.getNome(),
                        equipamento.getNumeroPatrimonio(),
                        setorOpt.get()
                );
                return Optional.of(equipamentoRepository.save(novoEquipamento));
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

    public Optional<Equipamento> atualizar(Integer id, EquipamentoRequest equipamento2) {
        Optional<Equipamento> equipamentoExistente = equipamentoRepository.findById(id);

        if (equipamentoExistente.isEmpty()) {
            return Optional.empty();
        }


        Optional<Setor> setorExistente = setorRepository.findById(equipamento2.getSetorId());
        
        if (setorExistente.isEmpty()) {
            return Optional.empty();
        }

        Equipamento equipamento = equipamentoExistente.get();
        equipamento.setNome(equipamento2.getNome());
        equipamento.setNumeroPatrimonio(equipamento2.getNumeroPatrimonio());
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