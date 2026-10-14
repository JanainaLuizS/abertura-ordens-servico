package br.com.aberturaordensservico.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordens_servico")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    int id;

    String descricao;
    LocalDateTime dataAbertura;
    LocalDateTime dataEncerramento; // não precisa colocar no construtor, pois quando a ordem de serviço é criada, ela ainda não foi encerrada, então o campo dataEncerramento será nulo. Quando a ordem de serviço for encerrada, o campo dataEncerramento será preenchido com a data e hora do encerramento.
    String Solucao; 

    @ManyToOne
    @JoinColumn(name = "equipamento_id")
    Equipamento equipamento;

    public OrdemServico() {
    }

    public OrdemServico(String descricao, LocalDateTime dataAbertura, Equipamento equipamento) {
        this.descricao = descricao;
        this.dataAbertura = dataAbertura;
        this.equipamento = equipamento;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSolucao() {
        return Solucao;
    }

    public void setSolucao(String Solucao) {
        this.Solucao = Solucao;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(LocalDateTime dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

}

