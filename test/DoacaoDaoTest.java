import dao.DoacaoDao;
import dao.ProjetoDao;
import dao.DoadorDao;
import model.Doacao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import model.Login;
import dao.LoginDao;

public class DoacaoDaoTest {
    
    
     public DoacaoDaoTest() {
    }

     @Test
    public void buscarPorID() {
        assertNotNull(DoacaoDao.getInstance().buscarID(
                DoacaoDao.getInstance().getLastId()));
    }

    @Test
    public void getLastId() {
        assertNotNull(DoacaoDao.getInstance().getLastId());
    }
    
    @Test
    public void persistirInserir() {
        Doacao i = new Doacao();

       
        i.setQuantidade(5);
        i.setData(null);
        i.setDescricao("Pinto de traveco gay");
        i.setAtivo(true);
        i.setDoador(DoadorDao.getInstance().buscarID(DoadorDao.getInstance().getLastId()));
        i.setProjeto(ProjetoDao.getInstance().buscarID(ProjetoDao.getInstance().getLastId()));

        assertEquals(true, DoacaoDao.getInstance().persistir(i));
    }
    
   /* @Test
    public void persistirAtualizar() {
        Doacao i = DoacaoDao.getInstance().buscarID(
                DoacaoDao.getInstance().getLastId());

       
        i.setQuantidade(5);
        i.setData(null);
        i.setDescricao("Pinto de traveco gay");
        i.setAtivo(true);
        i.setDoador(DoadorDao.getInstance().buscarID(DoacaoDao.getInstance().getLastId()));
        i.setProjeto(ProjetoDao.getInstance().buscarID(DoacaoDao.getInstance().getLastId()));

        assertEquals(true, DoacaoDao.getInstance().persistir(i));
    }*/
    /*
    @Test
    public void deletar() {
        Doacao i = DoacaoDao.getInstance().buscarID(
                DoacaoDao.getInstance().getLastId());
        assertEquals(true, DoacaoDao.getInstance().deletar(i));
    }*/
    
      @Test
    public void buscarAno() {

      assertEquals(true, DoacaoDao.getInstance().buscarAno("2018").size() > 0);
 
       };
     
    @Test
    public void buscarMes() {

      assertEquals(true, DoacaoDao.getInstance().buscarAno("05").size() > 0);
 
       };
     @Test
    public void buscarData() {

      assertEquals(true, DoacaoDao.getInstance().buscarAno("05/2018").size() > 0);
 
       };
    
    
}
