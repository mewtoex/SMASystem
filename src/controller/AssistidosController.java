
package controller;

import dao.AssistidosDao;
import java.util.List;
import model.Assistidos;

public class AssistidosController {
    
       private static AssistidosController assistidosController;

    public static AssistidosController getInstance() {
        if (assistidosController == null) {
            assistidosController = new AssistidosController();
        }
        return assistidosController;
    }
  
    public List<Assistidos> buscartodos(){
        return AssistidosDao.getInstance().buscarTodos();
    
    }
    
     public void persitirNxn(Assistidos d, Long h) {
        Long m;
        m = h;
        Assistidos n = d;
        AssistidosDao.getInstance().persistirco(n, m);

    }
            
            
    public boolean deletar(Long id) {
        return AssistidosDao.getInstance().delete(id);

    }

    public List<Assistidos> BuscaNome(String nome) {
        return AssistidosDao.getInstance().buscarNome(nome);
        
    }
 
    public void persistir(Assistidos d) {
        AssistidosDao.getInstance().persistir(d);
    }

    public Assistidos buscarID(Long id) {

        return AssistidosDao.getInstance().buscarID(id);
    }
    
    public Assistidos BuscarNome(String nome){
    
     return AssistidosDao.getInstance().buscaNome(nome);
    }
    
     public List<Assistidos> BuscaTodos() {
        return AssistidosDao.getInstance().buscarTodos();
        
    }
    
     public List<Assistidos> busca(int indice, String texto) {

        if (texto == null) {

            return AssistidosDao.getInstance().buscarTodos();

        } else {
            switch (indice) {

                case 0: {
                    return AssistidosDao.getInstance().buscarNome(texto);
                }
                case 1: {
                    return AssistidosDao.getInstance().buscarCPF(texto);
                }
                case 2: {
                    return AssistidosDao.getInstance().buscaridentidade(texto);

                }
                 case 3: {
                    if (("Sim".equals(texto) ^ "sim".equals(texto)) ^ ("Ativo".equals(texto) ^ "ativo".equals(texto))) {
                        return AssistidosDao.getInstance().buscarAtivo(true);

                    } else if ((("Nao".equals(texto)) ^ ("Não".equals(texto)))
                            ^ ((("Desativo".equals(texto)) ^ ("desativo".equals(texto)))
                            ^ (("nao".equals(texto)) ^ ("não".equals(texto))))) {
                        return AssistidosDao.getInstance().buscarAtivo(false);
                    }
                }
            }
        }
        return null;

    }

}
    

