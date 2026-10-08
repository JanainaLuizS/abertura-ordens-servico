package br.com.aberturaordensservico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EquipamentoRequest {
    
    @NotBlank(message = "O nome do equipamento é obrigatório")
   
    private String nome;

     @NotBlank(message = "O número de patrimônio do equipamento é obrigatório")
    private String numeroPatrimonio;

    @NotNull (message = "O setor do equipamento é obrigatório")
    private Integer setorId;

    public EquipamentoRequest() {
    }

    public EquipamentoRequest(String nome, String numeroPatrimonio, Integer setorId) {
        this.nome = nome;
        this.numeroPatrimonio = numeroPatrimonio;
        this.setorId = setorId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroPatrimonio() {
        return numeroPatrimonio;
    }

    public void setNumeroPatrimonio(String numeroPatrimonio) {
        this.numeroPatrimonio = numeroPatrimonio;
    }

    public Integer getSetorId() {
        return setorId;
    }

    public void setSetorId(Integer setorId) {
        this.setorId = setorId;
    }
}
