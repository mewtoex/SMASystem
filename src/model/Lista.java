/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Lista {

    private Long Id;
    private String data;
    private boolean presenca;
    private Projeto pro = new Projeto();
    private Assistidos ass = new Assistidos();

    public Long getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(Long Id) {
        this.Id = Id;
    }


    public boolean getpres() {
        return presenca;
    }

    public void setpres(boolean Ativo) {
        this.presenca = Ativo;
    }

    /**
     * @return the pro
     */
    public Projeto getPro() {
        return pro;
    }

    /**
     * @param pro the pro to set
     */
    public void setPro(Projeto pro) {
        this.pro = pro;
    }

    /**
     * @return the ass
     */
    public Assistidos getAss() {
        return ass;
    }

    /**
     * @param ass the ass to set
     */
    public void setAss(Assistidos ass) {
        this.ass = ass;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

 

}
