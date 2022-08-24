package model;

import java.util.ArrayList;
import java.util.Date;


public class Despesas {

    private Long Id;
    private String Nome ; 
    private String Valor ;
    private String Data;
    private String aberto;
    private boolean Ativo;
    private Doacao doacao;
    
    public Long getId() {
        return Id;
    }
 
    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String Valor) {
        this.Valor = Valor;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        this.Data = data;
    }

    public boolean getAtivo() {
        return Ativo;
    }

    public void setAtivo(boolean Ativo) {
        this.Ativo = Ativo;
    }
    /**
     * @return the aberto
     */
    public String getAberto() {
        return aberto;
    }

    /**
     * @param aberto the aberto to set
     */
    public void setAberto(String aberto) {
        this.aberto = aberto;
    }

    /**
     * @return the doacao
     */
    public Doacao getDoacao() {
        return doacao;
    }

    /**
     * @param doacao the doacao to set
     */
    public void setDoacao(Doacao doacao) {
        this.doacao = doacao;
    }

    
    
}
