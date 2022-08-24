
package model;

import java.util.ArrayList;

public class Assistidos {
    
    private Long Id;
    private String Nome ; 
    private String Identidade ;
    private String Telefone;
    private String Profissao;
    private String CPF ;
    private String Renda;
    private int Idade;
    private String Rua ; 
    private long Numero ;
    private String Complemento;
    private String Bairro;
    private String Cidade ;
    private String Estado ;
    private boolean Ativo;
    private ArrayList<Projeto> amparatos;

     public ArrayList<Projeto> getAmparatos() {
        if (amparatos == null) {
            amparatos = new ArrayList<>();
        }
        return amparatos;
    }

    public void setAmparatos(ArrayList<Projeto> e) {
        this.amparatos = e;
    }

    public void addAmparatos(Projeto e) {
        getAmparatos().add(e);
    }
     

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

    public String getRenda() {
        return Renda;
    }

    public void setRenda(String Renda) {
        this.Renda = Renda;
    }
    public int getIdade() {
        return Idade;
    }

 
    public void setIdade(int Idade) {
        this.Idade = Idade;
    }
    

    public String getRua() {
        return Rua;
    }


    public void setRua(String Rua) {
        this.Rua = Rua;
    }

    public long getNumero() {
        return Numero;
    }


    public void setNumero(long Numero) {
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
    
    
}

    

