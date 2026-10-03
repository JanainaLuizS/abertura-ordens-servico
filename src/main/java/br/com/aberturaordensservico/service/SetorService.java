package br.com.aberturaordensservico.service;

import java.util.List;

import org.springframework.stereotype.Service;
import br.com.aberturaordensservico.model.Setor;
import br.com.aberturaordensservico.repository.SetorRepository;


@Service
public class SetorService {

    private final SetorRepository setorRepository;

    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    
    public Setor salvarSetor(Setor setor) {
        return setorRepository.save(setor);
    }

    public List<Setor> listarSetores() {
        return setorRepository.findAll();
    }

    public Setor buscarSetorPorId(int id) {
        return setorRepository.findById(id).orElse(null);
    }

    public Setor atualizarSetor(int id, Setor setorAtualizado) {
        Setor setorExistente = setorRepository.findById(id).orElse(null);
        if (setorExistente != null) {
            setorExistente.setNome(setorAtualizado.getNome());
            return setorRepository.save(setorExistente);
        }
        return null;
    }

    public boolean deletarSetor(int id) {
        Setor setorExistente = setorRepository.findById(id).orElse(null);
        if (setorExistente != null) {
            setorRepository.delete(setorExistente);
            return true;
        }
        return false;
    }
  
}
