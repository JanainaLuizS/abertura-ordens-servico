package br.com.aberturaordensservico.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrdemServicoRequest {

    @NotBlank (message = "A descrição da ordem de serviço é obrigatória")
    private String descricao;
    private LocalDateTime dataAbertura;

    @NotNull (message = "O ID do equipamento é obrigatório")
    private int equipamentoId;

    public OrdemServicoRequest() {
    }

    public OrdemServicoRequest(String descricao, LocalDateTime dataAbertura, int equipamentoId) {
        this.descricao = descricao;
        this.dataAbertura = dataAbertura;
        this.equipamentoId = equipamentoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public int getEquipamentoId() {
        return equipamentoId;
    }

    public void setEquipamentoId(int equipamentoId) {
        this.equipamentoId = equipamentoId;
    }

    
}
