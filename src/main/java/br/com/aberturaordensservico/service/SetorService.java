package br.com.aberturaordensservico.service;

import java.util.List;

import org.springframework.stereotype.Service;
import br.com.aberturaordensservico.model.Setor;
import br.com.aberturaordensservico.repository.SetorRepository;
import java.util.Optional;

@Service
public class SetorService {

    private final SetorRepository setorRepository;


    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    
    public Setor cadastrar(Setor setor) {
        return setorRepository.save(setor);
    }

    public List<Setor> listar() {
        return setorRepository.findAll();
    }

    public Optional<Setor> buscarPorId(Integer id) {
        return setorRepository.findById(id);
    }

    public Optional<Setor> atualizar(Integer id, Setor setorAtualizado) {

        Optional<Setor> setorExistente = setorRepository.findById(id);

        if (!setorExistente.isEmpty()) {
            setorExistente.get().setNome(setorAtualizado.getNome());
            return Optional.of(setorRepository.save(setorExistente.get()));
        }
        
        return Optional.empty();
    }

    public boolean excluir(Integer id) {
        if (setorRepository.existsById(id)) {
            setorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}