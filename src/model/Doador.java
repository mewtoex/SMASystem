
package model;

import java.util.Date;


public class Doador {
    
    private Long id;
    private String Nome ; 
    private String Identidade ;
    private String Telefone;
    private String Profissao;
    private String CPF ;
    private String Rua ; 
    private Long Numero ;
    private String Complemento;
    private String Bairro;
    private String Cidade ;
    private String Estado ;
    private boolean Ativo;




    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getIdentidade() {
        return Identidade;
    }

    public void setIdentidade(String Identidade) {
        this.Identidade = Identidade;
    }


    public String getTelefone() {
        return Telefone;
    }


    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public String getProfissao() {
        return Profissao;
    }


    public void setProfissao(String Profissao) {
        this.Profissao = Profissao;
    }


    public String getCPF() {
        return CPF;
    }

 
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }


    public String getRua() {
        return Rua;
    }


    public void setRua(String Rua) {
        this.Rua = Rua;
    }

    public Long getNumero() {
        return Numero;
    }


    public void setNumero(Long Numero) {
        this.Numero = Numero;
    }


    public String getComplemento() {
        return Complemento;
    }


    public void setComplemento(String Complemento) {
        this.Complemento = Complemento;
    }

 
    public String getBairro() {
        return Bairro;
    }


    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

 
    public String getCidade() {
        return Cidade;
    }


    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }


    public String getEstado() {
        return Estado;
    }

 
    public void setEstado(String Estado) {
        this.Estado = Estado;
    }


    public boolean getAtivo() {
        return Ativo;
    }


    public void setAtivo(boolean Ativo) {
        this.Ativo = Ativo;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

  
    public void setId(Long id) {
        this.id = id;
    }
    
    
}
