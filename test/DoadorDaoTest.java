
import dao.DoadorDao;
import model.Doador;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DoadorDaoTest {

    public DoadorDaoTest() {
    }

    @Test
    public void buscarPorID() {
        assertNotNull(DoadorDao.getInstance().buscarID(
                DoadorDao.getInstance().getLastId()));
    }

    @Test
    public void getLastId() {
        assertNotNull(DoadorDao.getInstance().getLastId());
    }

   /* @Test
    public void deletar() {
        Doador i = DoadorDao.getInstance().buscarID(
                DoadorDao.getInstance().getLastId());
        assertEquals(true, DoadorDao.getInstance().deletar(i));
    }*/

   /* @Test
    public void persistirInserir() {
        Doador i = new Doador();

       
        i.setNome("Gabs");
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
        i.setAtivo(true);

        assertEquals(true, DoadorDao.getInstance().persistir(i));
    }*/
    @Test
    public void buscarPorNome() {

      assertEquals(true, DoadorDao.getInstance().buscarNome("Tabs").size() > 0);
 
       };
    
      @Test
    public void buscarPorCPF() {

      assertEquals(true, DoadorDao.getInstance().buscarCPF("120.784.474-78").size() > 0);
 
       };
    
      @Test
    public void buscarPorRG() {

      assertEquals(true, DoadorDao.getInstance().buscaridentidade("MG 125").size() > 0);
 
       };
    @Test
    public void buscarAtivo() {

      assertEquals(true, DoadorDao.getInstance().buscarAtivo(false).size() > 0);
 
       };
    
    @Test
    public void persistirAtualizar() {
        Doador i = DoadorDao.getInstance().buscarID(
                DoadorDao.getInstance().getLastId());

        i.setNome("Tabs");
        i.setIdentidade("Rj 0412525");
        i.setTelefone("32 32148787");
        i.setProfissao("Tec Idustrial");
        i.setCPF("100.784.870-78");
        i.setRua("Mariana da Oliveira");
        i.setNumero(200l);
        i.setComplemento("AP 250");
        i.setBairro("Ipanema");
        i.setCidade("Tres Rios");
        i.setEstado("SP");
        i.setAtivo(false);

        assertEquals(true, DoadorDao.getInstance().persistir(i));
    }
}
