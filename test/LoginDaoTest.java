/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.LoginDao;
import org.junit.Test;
import model.Login;
import static org.junit.Assert.*;

public class LoginDaoTest {

    @Test
    public void buscarTodos() {
        assertEquals(true, LoginDao.getInstance().buscarTodos().size() > 0);
    }
     @Test
    public void buscarTodos2() {
        assertEquals(true, LoginDao.getInstance().buscarTodos2().size() > 0);
    }


    
    @Test
    public void Verificaradm () {
        
        assertNotNull(LoginDao.getInstance().VerificarADM(1l));

    }
   /*@Test
    public void buscarPorusuario() {

      
      assertNotNull(LoginDao.getInstance().("joao","SD"));
      

    }*/
/*
   @Test
    public void buscarPorID() {
        assertNotNull(LoginDao.getInstance().buscarID(
                LoginDao.getInstance().getLastId()));
    }
    
    @Test
    public void persistirInserir() {
        Login i = new Login();
        i.setUsuario("mariaa");
        i.setSenha("gabs");

        assertEquals(true, LoginDao.getInstance().persistir(i));
    }

    /*@Test
    public void persistirAtualizar() {
        Login i = LoginDao.getInstance().buscarID(
                LoginDao.getInstance().getLastId());

        i.setUsuario("XXXXX");
        i.setSenha("86868868");

        assertEquals(true, LoginDao.getInstance().persistir(i));
    }*/

    
   /* @Test
    public void deletar() {
        Login i = LoginDao.getInstance().buscarID(
                LoginDao.getInstance().getLastId());

        assertEquals(true, LoginDao.getInstance().deletar(i));
    }*/

    @Test
    public void getLastId() {
        assertNotNull(LoginDao.getInstance().getLastId());
    }

}
