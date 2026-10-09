package br.com.aberturaordensservico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aberturaordensservico.model.Equipamento;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Integer> {

}