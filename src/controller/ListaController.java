package controller;

import dao.DoacaoDao;
import dao.ListaDao;
import java.util.ArrayList;
import java.util.List;
import model.Lista;


public class ListaController {

    private static ListaController listaController;

    public static ListaController getInstance() {
        if (listaController == null) {
            listaController = new ListaController();
        }
        return listaController;
    }

    public List<Lista> buscartodos() {
        return ListaDao.getInstance().buscarTodos();

    }

    public boolean deletar(Long id) {
        return ListaController.getInstance().deletar(id);

    }

    
    public ArrayList<Lista> buscarPro(Long o, String d) {

        return ListaDao.getInstance().buscarPro(o,d);

    }
     
    public void persistir(Lista d) {
        ListaDao.getInstance().persistir(d);
    }

    public Lista buscarID(Long id) {

        return ListaDao.getInstance().buscarID(id);
    }

    public List<Lista> busca(int indice, String texto) {

        if (texto == null) {

            return ListaDao.getInstance().buscarTodos();

        } else {
            switch (indice) {

                case 0: {
                    return ListaDao.getInstance().buscarTodos();
                }
                case 1: {
                    return ListaDao.getInstance().buscarMes(texto);
                }
                case 2: {
                    return ListaDao.getInstance().buscarAno(texto);

                }
                case 3: {
                    return ListaDao.getInstance().buscarMesAno(texto);
                }

                case 4: {
                  return ListaDao.getInstance().buscardata(texto);
                }
            }
        }
        return null;

    }
}
