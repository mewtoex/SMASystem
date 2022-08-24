import dao.VoluntarioDao;
import model.Voluntario;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import model.Login;
import dao.LoginDao;
import dao.ProjetoDao;
import model.Projeto;


public class VoluntarioDaoTest {
    
    public VoluntarioDaoTest() {
    }

    /* @Test
    public void buscarPorID() {
        assertNotNull(VoluntarioDao.getInstance().buscarID(
                VoluntarioDao.getInstance().getLastId()));
    }*/

    /*@Test
    public void getLastId() {
        assertNotNull(VoluntarioDao.getInstance().getLastId());
    }*/

   /* @Test
    public void deletar() {
        Voluntario i = VoluntarioDao.getInstance().buscarID(
                VoluntarioDao.getInstance().getLastId());
        assertEquals(true, VoluntarioDao.getInstance().deletar(i));
    }*/

   /*@Test
    public void persistirInserir() {
        Voluntario i = new Voluntario();

       
        i.setNome("Gabs");
        i.setIdentidade("MaG a0025");
        i.setTelefone("32 32148787");
        i.setProfissao("Tec Idustrial");
        i.setCPF("120.6a84.0-78");
        i.setRua("Mariana da Oliveira");
        i.setNumero(200);
        i.setComplemento("AP 250");
        i.setBairro("Ipanema");
        i.setCidade("Tres Rios");
        i.setEstado("RJ");
        i.setDia_atuacao("Segunda");
        i.setHora("5 horas");
        i.setAtivo(true);
        
        i.setLog(LoginDao.getInstance().buscarID(LoginDao.getInstance().getLastId()));

        Projeto g2 = ProjetoDao.getInstance().buscarID(ProjetoDao.getInstance().getLastId());
        i.addcolabora(g2);
      
        Projeto g1 = ProjetoDao.getInstance().buscarID(1l);
        i.addcolabora(g1);
        
        assertEquals(true, VoluntarioDao.getInstance().persistir(i));
    }*/

    /*@Test
    public void persistirAtualizar() {
        Voluntario i = VoluntarioDao.getInstance().buscarID(
                VoluntarioDao.getInstance().getLastId());

       
        i.setNome("Mars");
        i.setIdentidade("MG 0412525");
        i.setTelefone("32 32148787");
        i.setProfissao("Tec Idustrial");
        i.setCPF("120.784.870-78");
        i.setRua("Mariana da Oliveira");
        i.setNumero(200);
        i.setComplemento("AP 250");
        i.setBairro("Ipanema");
        i.setCidade("Tres Rios");
        i.setEstado("RJ");
        i.setDia_atuacao("Segunda");
        i.setHora("5 horas");
        i.setAtivo(true);

        assertEquals(true, VoluntarioDao.getInstance().persistir(i));
    }*/
    
      @Test
    public void buscarPorNome() {

      assertEquals(true, VoluntarioDao.getInstance().buscarNome("Gabs").size() > 0);
 
       };
    
      @Test
    public void buscarPorCPF() {

      assertEquals(true, VoluntarioDao.getInstance().buscarCPF("20.684.0-78").size() > 0);
 
       };
    
      @Test
    public void buscarPorRG() {

      assertEquals(true, VoluntarioDao.getInstance().buscaridentidade("MG 12525").size() > 0);
 
       };
    @Test
    public void buscarAtivo() {

      assertEquals(true, VoluntarioDao.getInstance().buscarAtivo(false).size() > 0);
 
       };
    
}
    

