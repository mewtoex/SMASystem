import dao.AssistidosDao;
import dao.ProjetoDao;
import model.Assistidos;
import model.Projeto;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class AssistidoDaoTest {
    
    public AssistidoDaoTest() {
    }

    @Test
    public void buscarPorID() {
        assertNotNull(AssistidosDao.getInstance().buscarID(
                AssistidosDao.getInstance().getLastId()));
    }

    @Test
    public void getLastId() {
        assertNotNull(AssistidosDao.getInstance().getLastId());
    }
    /*
    @Test
    public void deletar() {
        Assistidos i = AssistidosDao.getInstance().buscarID(
                AssistidosDao.getInstance().getLastId());
        assertEquals(true, AssistidosDao.getInstance().deletar(i));
    }*/

   /* @Test
    public void persistirInserir() {
        Assistidos i = new Assistidos();

       
        i.setNome("Gabs");
        i.setIdentidade("M 0412525");
        i.setTelefone("32 32148787");
        i.setProfissao("Tec Idustrial");
        i.setCPF("20.784.870-78");
        i.setRenda("500,00");
        i.setIdade(2);
        i.setRua("Mariana da Oliveira");
        i.setNumero(200);
        i.setComplemento("AP 250");
        i.setBairro("Ipanema");
        i.setCidade("Tres Rios");
        i.setEstado("RJ");
        i.setAtivo(true);
    
      

        Projeto g2 = ProjetoDao.getInstance().buscarID(ProjetoDao.getInstance().getLastId());
        i.addAmparatos(g2);
      
        Projeto g1 = ProjetoDao.getInstance().buscarID(1l);
        i.addAmparatos(g1);
        

        assertEquals(true, AssistidosDao.getInstance().persistir(i));
    }*/

    /*@Test
    public void persistirAtualizar() {
        Assistidos i = AssistidosDao.getInstance().buscarID(
                AssistidosDao.getInstance().getLastId());

        i.setNome("Tabs");
        i.setIdentidade("Rj 0412525");
        i.setTelefone("32 32148787");
        i.setProfissao("Tec Idustrial");
        i.setCPF("100.784.870-78");
        i.setRenda("4500,00");
        i.setIdade(20);
        i.setRua("Mariana da Oliveira");
        i.setNumero(200);
        i.setComplemento("AP 250");
        i.setBairro("Ipanema");
        i.setCidade("Tres Rios");
        i.setEstado("SP");
        i.setAtivo(false);

        assertEquals(true, AssistidosDao.getInstance().persistir(i));
    }*/
     @Test
    public void buscarPorNome() {

      assertEquals(true, AssistidosDao.getInstance().buscarNome("Tabs").size() > 0);
 
       };
    
      @Test
    public void buscarPorCPF() {

      assertEquals(true, AssistidosDao.getInstance().buscarCPF("100.784.870-78").size() > 0);
 
       };
    
      @Test
    public void buscarPorRG() {

      assertEquals(true, AssistidosDao.getInstance().buscaridentidade("Rj 0412525").size() > 0);
 
       };
    @Test
    public void buscarAtivo() {

      assertEquals(true, AssistidosDao.getInstance().buscarAtivo(true).size() > 0);
 
       };
}

    

