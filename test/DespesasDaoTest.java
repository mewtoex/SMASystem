
import dao.DespesasDao;
import dao.DoacaoDao;
import model.Despesas;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;


public class DespesasDaoTest {
    
     public DespesasDaoTest() {
    }

     @Test
    public void buscarPorID() {
        assertNotNull(DespesasDao.getInstance().buscarID(
                DespesasDao.getInstance().getLastId()));
    }

    @Test
    public void getLastId() {
        assertNotNull(DespesasDao.getInstance().getLastId());
    }

    /*@Test
    public void deletar() {
        Despesas i = DespesasDao.getInstance().buscarID(
                DespesasDao.getInstance().getLastId());
        assertEquals(true, DespesasDao.getInstance().deletar(i));
    }*/

    /*@Test
    public void persistirInserir() {
        Despesas i = new Despesas();

       
        i.setNome("Gabs");
        i.setValor("415");
        i.setData(null);
        i.setAtivo(true);
        i.setAberto("A Pagar");
        i.setDoacao(DoacaoDao.getInstance().buscarID(DoacaoDao.getInstance().getLastId()));

        assertEquals(true, DespesasDao.getInstance().persistir(i));
    }*/

     /*@Test
    public void persistirAtualizar() {
        Despesas i = DespesasDao.getInstance().buscarID(
                DespesasDao.getInstance().getLastId());

       
       i.setNome("Gabs");
        i.setValor("415");
        i.setData(null);
        i.setAtivo(true);
        i.setAberto("A77 Pagar");
        i.setDoacao(DoacaoDao.getInstance().buscarID(DoacaoDao.getInstance().getLastId()));

        assertEquals(true, DespesasDao.getInstance().persistir(i));
    }*/
    
       @Test
    public void buscarAno() {

      assertEquals(true, DespesasDao.getInstance().buscarAno("2018").size() > 0);
 
       };
     
    @Test
    public void buscarMes() {

      assertEquals(true, DespesasDao.getInstance().buscarAno("05").size() > 0);
 
       };
     @Test
    public void buscarData() {

      assertEquals(true, DespesasDao.getInstance().buscarAno("05/2018").size() > 0);
 
       };
    
}
    
    

