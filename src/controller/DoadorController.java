package controller;

import dao.DoadorDao;
import java.util.ArrayList;
import java.util.List;
import model.Doador;

public class DoadorController {

    private static DoadorController doadorController;

    public static DoadorController getInstance() {
        if (doadorController == null) {
            doadorController = new DoadorController();
        }
        return doadorController;
    }

    public boolean deletar(Long id) {
        return DoadorDao.getInstance().delete(id);

    }
    
    public List<Doador> Buscartodo(){
    
       return DoadorDao.getInstance().buscarTodos();
    }
    
    public Doador buscarNome(String texto) {

        return DoadorDao.getInstance().buscaNome(texto);

    }
    
    public void persistir(Doador doador) {
        DoadorDao.getInstance().persistir(doador);
    }

    public Doador buscarID(Long id) {

        return DoadorDao.getInstance().buscarID(id);
    }

    public List<Doador> buscar(String texto) {

        if (texto == null) {

            return DoadorDao.getInstance().buscarTodos();

        }

        return DoadorDao.getInstance().buscaridentidade(texto);

    }

    public List<Doador> busca(int indice, String texto) {

        if (texto == null) {

            return DoadorDao.getInstance().buscarTodos();

        } else {
            switch (indice) {

                case 0: {
                    return DoadorDao.getInstance().buscarNome(texto);
                }
                case 1: {
                    return DoadorDao.getInstance().buscarCPF(texto);
                }
                case 2: {
                    return DoadorDao.getInstance().buscaridentidade(texto);

                }

                case 3: {
                    if (("Sim".equals(texto) ^ "sim".equals(texto)) ^ ("Ativo".equals(texto) ^ "ativo".equals(texto))) {
                        return DoadorDao.getInstance().buscarAtivo(true);

                    } else if ((("Nao".equals(texto)) ^ ("Não".equals(texto)))
                            ^ ((("Desativo".equals(texto)) ^ ("desativo".equals(texto)))
                            ^ (("nao".equals(texto)) ^ ("não".equals(texto))))) {
                        return DoadorDao.getInstance().buscarAtivo(false);
                    }
                }
            }
        }
        return null;

    }

}
