package model;

import java.util.Date;

public class Login {

    private Long id;
    private String usuario;
    private String senha;
    private boolean adm;
    
    
    public boolean getAdm() {
        return adm;
    }

  
    public void setAdm(boolean adm) {
        this.adm = adm;
    }

  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
