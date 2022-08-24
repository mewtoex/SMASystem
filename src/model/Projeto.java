
package model;


import java.util.ArrayList;
import java.util.Date;

public class Projeto {

    private Long Id;
    private String Nome ; 
    private String Data;
    private String Descricao ;
    private boolean Ativo;
    private ArrayList<Assistidos> amparatos;
    private ArrayList<Voluntario> colabora;
    
   public ArrayList<Assistidos> getAmparatos() {
        if (amparatos == null) {
            amparatos = new ArrayList<>();
        }
        return amparatos;
    }

    public void setAmparatos(ArrayList<Assistidos> e) {
        this.amparatos = e;
    }

    public void addAmparatos(Assistidos e) {
        getAmparatos().add(e);
    }
    
    public ArrayList<Voluntario> getcolabora() {
        if (colabora == null) {
            colabora = new ArrayList<>();
        }
        return colabora;
    }

   public void setcolabora(ArrayList<Voluntario> j) {
        this.colabora = j;
    }

    public void addcolabora(Voluntario e) {
        getcolabora().add(e);
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

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public boolean getAtivo() {
        return Ativo;
    }

    public void setAtivo(boolean Ativo) {
        this.Ativo = Ativo;
    }
   

}
