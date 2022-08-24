package controller;

import dao.LoginDao;
import java.util.ArrayList;
import java.util.List;
import model.Login;

public class LoginController {

    private static LoginController loginController;

    public static LoginController getInstance() {
        if (loginController == null) {
            loginController = new LoginController();
        }
        return loginController;
    }

    public Login buscaruser(String texto) {

        return LoginDao.getInstance().buscarUsuario(texto);

    }
    
    public List<Login> BuscarTodos(){
       
       return LoginDao.getInstance().buscarTodos();
    }
    
    public List<Login> buscar(String texto) {

        if (texto == null) {
            return LoginDao.getInstance().buscarTodos();
        } else {
            return LoginDao.getInstance().buscarusuarios(texto);
        }
    }

    public Login autenticar(Login m) {

        String nome = m.getUsuario();
        String senha = m.getSenha();
        return LoginDao.getInstance().buscarUsuarioSenha(nome, senha);

    }

    public boolean deletar(Long id) {
        return LoginDao.getInstance().delete(id);

    }

    public void persistir(Login l) {
        LoginDao.getInstance().persistir(l);
    }

    public Login buscarID(Long id) {

        return LoginDao.getInstance().buscarID(id);
    }

    public Login VerAdm(Login m) {

        Long Idl = m.getId();

        return LoginDao.getInstance().VerificarADM(Idl);

    }

}
