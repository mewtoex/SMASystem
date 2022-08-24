
import dao.ProjetoDao;
import org.junit.Test;
import model.Projeto;
import model.Assistidos;
import dao.AssistidosDao;
import dao.VoluntarioDao;
import model.Voluntario;
import static org.junit.Assert.*;

public class ProjetoDaoTest {
    /*
    @Test
    public void buscarTodos() {
        assertEquals(true, ProjetoDao.getInstance().buscarTodos().size() > 0);
    }

   @Test
    public void buscarPorID() {
        assertNotNull(ProjetoDao.getInstance().buscarID(
                ProjetoDao.getInstance().getLastId()));
    }*/
    
    /* @Test
    public void deletar() {
        Projeto i = ProjetoDao.getInstance().buscarID(
                ProjetoDao.getInstance().getLastId());

        assertEquals(true, ProjetoDao.getInstance().deletar(i));
    }*/
   /*
    @Test
    public void getLastId() {
        assertNotNull(ProjetoDao.getInstance().getLastId());
    }*/
    /*
    @Test
    public void persistirInserir() {
        
        
        
        Projeto i = new Projeto();
        i.setNome("254");
        i.setData(null);
        i.setDescricao("mar");
        i.setAtivo(true);
        
        Assistidos e = AssistidosDao.getInstance().buscarID(5l);
        i.addAmparatos(e);
      
        Assistidos e1 = AssistidosDao.getInstance().buscarID(4l);
        i.addAmparatos(e1);
        
         Voluntario g2 = VoluntarioDao.getInstance().buscarID(VoluntarioDao.getInstance().getLastId());
        i.addcolabora(g2);
      
        Voluntario g1 = VoluntarioDao.getInstance().buscarID(VoluntarioDao.getInstance().getLastId());
        i.addcolabora(g1);
        
        


        assertEquals(true, ProjetoDao.getInstance().persistir(i));
    }*/

    /*@Test
    public void persistirAtualizar() {
        Projeto i = ProjetoDao.getInstance().buscarID(
                ProjetoDao.getInstance().getLastId());

        i.setNome("4747");
        i.setData(null);
        i.setDescricao("jaoo");
        i.setAtivo(false);

        assertEquals(true, ProjetoDao.getInstance().persistir(i));
    }*/
    
    /*@Test
    public void buscarPorNome() {

      assertEquals(true, ProjetoDao.getInstance().buscarNome("4747").size() > 0);
 
    };*/
     @Test
    public void buscarAtivo() {

      assertEquals(true, ProjetoDao.getInstance().buscarAtivo(true).size() > 0);
 
       };
    
    @Test
    public void buscarAno() {

      assertEquals(true, ProjetoDao.getInstance().buscarAno("2018").size() > 0);
 
       };
     
    @Test
    public void buscarMes() {

      assertEquals(true, ProjetoDao.getInstance().buscarAno("05").size() > 0);
 
       };
     @Test
    public void buscarData() {

      assertEquals(true, ProjetoDao.getInstance().buscarAno("05/2018").size() > 0);
 
       };
    
}
