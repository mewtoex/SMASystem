package controller;

import dao.DoacaoDao;
import java.util.ArrayList;
import java.util.List;
import model.Doacao;
import model.Doador;

public class DoacaoController {

    private static DoacaoController doacaoController;

    public static DoacaoController getInstance() {
        if (doacaoController == null) {
            doacaoController = new DoacaoController();
        }
        return doacaoController;
    }

    public List<Doacao> buscartodos() {
        return DoacaoDao.getInstance().buscarTodos();

    }

    public boolean deletar(Long id) {
        return DoacaoController.getInstance().deletar(id);

    }

    
    public Doacao buscarDescri(String texto) {

        return DoacaoDao.getInstance().buscard(texto);

    }
     
    public void persistir(Doacao d) {
        DoacaoDao.getInstance().persistir(d);
    }

    public Doacao buscarID(Long id) {

        return DoacaoDao.getInstance().buscarID(id);
    }

    public List<Doacao> busca(int indice, String texto) {

        if (texto == null) {

            return DoacaoDao.getInstance().buscarTodos();

        } else {
            switch (indice) {

                case 0: {
                    return DoacaoDao.getInstance().buscarTodos();
                }
                case 1: {
                    return DoacaoDao.getInstance().buscarMes(texto);
                }
                case 2: {
                    return DoacaoDao.getInstance().buscarAno(texto);

                }
                case 3: {
                    return DoacaoDao.getInstance().buscarMesAno(texto);
                }

                case 4: {
                    if (("Sim".equals(texto) ^ "sim".equals(texto)) ^ ("Ativo".equals(texto) ^ "ativo".equals(texto))) {
                        return DoacaoDao.getInstance().buscarAtivo(true);

                    } else if ((("Nao".equals(texto)) ^ ("Não".equals(texto)))
                            ^ ((("Desativo".equals(texto)) ^ ("desativo".equals(texto)))
                            ^ (("nao".equals(texto)) ^ ("não".equals(texto))))) {
                        return DoacaoDao.getInstance().buscarAtivo(false);
                    }
                }
            }
        }
        return null;

    }
}
