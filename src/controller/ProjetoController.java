package controller;

import dao.ProjetoDao;
import java.util.List;
import model.Projeto;

public class ProjetoController {

    private static ProjetoController projetoController;

    public static ProjetoController getInstance() {
        if (projetoController == null) {
            projetoController = new ProjetoController();
        }
        return projetoController;
    }

    public boolean deletar(Long id) {
        return ProjetoDao.getInstance().delete(id);

    }
    
    public void persitirNxn(Projeto d, Long h) {
        Long m;
        m = h;
        Projeto n = d;
        ProjetoDao.getInstance().persistirco(n, m);

    }
    
     public void persitirNxN(Projeto d, Long h) {
        Long m;
        m = h;
        Projeto n = d;
        ProjetoDao.getInstance().persistirAm(n, m);

    }

    public void persistir(Projeto d) {
        ProjetoDao.getInstance().persistir(d);
    }

    public Projeto buscarID(Long id) {

        return ProjetoDao.getInstance().buscarID(id);
    }

    public List<Projeto> buscar() {

        return ProjetoDao.getInstance().buscarTodos();

    }
    
     public Projeto buscarNome(String texto) {

        return ProjetoDao.getInstance().buscaNome(texto);

    }
    
    public List<Projeto> busca(int indice, String texto) {

        if (texto == null) {

            return ProjetoDao.getInstance().buscarTodos();

        } else {
            switch (indice) {

                case 0: {
                    return ProjetoDao.getInstance().buscarNome(texto);
                }
                case 1: {
                    return ProjetoDao.getInstance().buscarMes(texto);
                }
                case 2: {
                    return ProjetoDao.getInstance().buscarAno(texto);

                }
                case 3: {
                    return ProjetoDao.getInstance().buscarMesAno(texto);
                }

                case 4: {
                    if (("Sim".equals(texto) ^ "sim".equals(texto)) ^ ("Ativo".equals(texto) ^ "ativo".equals(texto))) {
                        return ProjetoDao.getInstance().buscarAtivo(true);

                    } else if ((("Nao".equals(texto)) ^ ("Não".equals(texto)))
                            ^ ((("Desativo".equals(texto)) ^ ("desativo".equals(texto)))
                            ^ (("nao".equals(texto)) ^ ("não".equals(texto))))) {
                        return ProjetoDao.getInstance().buscarAtivo(false);
                    }
                }
            }
        }
        return null;

    }
    
}


