package br.com.aberturaordensservico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrdemServicoRequest {

    @NotBlank (message = "A descrição da ordem de serviço é obrigatória")
    private String descricao;
  

    @NotNull (message = "O ID do equipamento é obrigatório")
    private int equipamentoId;

    public OrdemServicoRequest() {
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

   
    public int getEquipamentoId() {
        return equipamentoId;
    }

    public void setEquipamentoId(int equipamentoId) {
        this.equipamentoId = equipamentoId;
    }

    
}
