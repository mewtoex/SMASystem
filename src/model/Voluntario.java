
package model;

import java.util.ArrayList;


public class Voluntario {

    private Long Id;
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
    private String dia_atuacao;
    private String Hora;
    private boolean Ativo;
    private Login log = new Login();
    private ArrayList<Projeto> colabora;

    public Long getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(Long Id) {
        this.Id = Id;
    }

    /**
     * @return the Nome
     */
    public String getNome() {
        return Nome;
    }

    /**
     * @param Nome the Nome to set
     */
    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    /**
     * @return the Identidade
     */
    public String getIdentidade() {
        return Identidade;
    }

    /**
     * @param Identidade the Identidade to set
     */
    public void setIdentidade(String Identidade) {
        this.Identidade = Identidade;
    }

    /**
     * @return the Telefone
     */
    public String getTelefone() {
        return Telefone;
    }

    /**
     * @param Telefone the Telefone to set
     */
    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    /**
     * @return the Profissao
     */
    public String getProfissao() {
        return Profissao;
    }

    /**
     * @param Profissao the Profissao to set
     */
    public void setProfissao(String Profissao) {
        this.Profissao = Profissao;
    }

    /**
     * @return the CPF
     */
    public String getCPF() {
        return CPF;
    }

    /**
     * @param CPF the CPF to set
     */
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    /**
     * @return the Rua
     */
    public String getRua() {
        return Rua;
    }

    /**
     * @param Rua the Rua to set
     */
    public void setRua(String Rua) {
        this.Rua = Rua;
    }

    /**
     * @return the Numero
     */
    public Long getNumero() {
        return Numero;
    }

    /**
     * @param Numero the Numero to set
     */
    public void setNumero(Long Numero) {
        this.Numero = Numero;
    }

    /**
     * @return the Complemento
     */
    public String getComplemento() {
        return Complemento;
    }

    /**
     * @param Complemento the Complemento to set
     */
    public void setComplemento(String Complemento) {
        this.Complemento = Complemento;
    }

    /**
     * @return the Bairro
     */
    public String getBairro() {
        return Bairro;
    }

    /**
     * @param Bairro the Bairro to set
     */
    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    /**
     * @return the Cidade
     */
    public String getCidade() {
        return Cidade;
    }

    /**
     * @param Cidade the Cidade to set
     */
    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    /**
     * @return the Estado
     */
    public String getEstado() {
        return Estado;
    }

    /**
     * @param Estado the Estado to set
     */
    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    /**
     * @return the dia_atuacao
     */
    public String getDia_atuacao() {
        return dia_atuacao;
    }

    /**
     * @param dia_atuacao the dia_atuacao to set
     */
    public void setDia_atuacao(String dia_atuacao) {
        this.dia_atuacao = dia_atuacao;
    }

    /**
     * @return the Hora
     */
    public String getHora() {
        return Hora;
    }

    /**
     * @param Hora the Hora to set
     */
    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    /**
     * @return the Ativo
     */
    public boolean getAtivo() {
        return Ativo;
    }

    /**
     * @param Ativo the Ativo to set
     */
    public void setAtivo(boolean Ativo) {
        this.Ativo = Ativo;
    }

    /**
     * @return the log
     */
    public Login getLog() {
        return log;
    }

    /**
     * @param log the log to set
     */
    public void setLog(Login log) {
        this.log = log;
    }
   public ArrayList<Projeto> getcolabora() {
        if (colabora == null) {
            colabora = new ArrayList<>();
        }
        return colabora;
    }

   public void setcolabora(ArrayList<Projeto> j) {
        this.colabora = j;
    }

    public void addcolabora(Projeto e) {
        getcolabora().add(e);
    }
   
    
}
