package controller;

import dao.DespesasDao;
import java.util.ArrayList;
import java.util.List;
import model.Despesas;

public class DespesasController {

    private static DespesasController despesasController;

    public static DespesasController getInstance() {
        if (despesasController == null) {
            despesasController = new DespesasController();
        }
        return despesasController;
    }

    public boolean deletar(Long id) {
        return DespesasDao.getInstance().deleta(id);

    }
    
    public List<Despesas> BuscaAno(String ano) {
        return DespesasDao.getInstance().buscarAno(ano);

    }

    public void persistir(Despesas d) {
        DespesasDao.getInstance().persistir(d);
    }

    public Despesas buscarID(Long id) {

        return DespesasDao.getInstance().buscarID(id);
    }

    public List<Despesas> buscar(String texto) {

        if (texto == null) {

            return DespesasDao.getInstance().buscarTodos();

        }

        return DespesasDao.getInstance().buscarMes(texto);

    }

    public List<Despesas> busca(int indice, String texto) {

        if (texto == null) {

            return DespesasDao.getInstance().buscarTodos();

        } else {
            switch (indice) {

                case 0: {
                    return DespesasDao.getInstance().buscarNome(texto);
                }
                case 1: {
                    return DespesasDao.getInstance().buscarMes(texto);
                }
                case 2: {
                    return DespesasDao.getInstance().buscarAno(texto);

                }
                case 3: {
                    return DespesasDao.getInstance().buscarMesAno(texto);
                }

                case 4: {
                    if (("Sim".equals(texto) ^ "sim".equals(texto)) ^ ("Ativo".equals(texto) ^ "ativo".equals(texto))) {
                        return DespesasDao.getInstance().buscarAtivo(true);

                    } else if ((("Nao".equals(texto)) ^ ("Não".equals(texto)))
                            ^ ((("Desativo".equals(texto)) ^ ("desativo".equals(texto)))
                            ^ (("nao".equals(texto)) ^ ("não".equals(texto))))) {
                        return DespesasDao.getInstance().buscarAtivo(false);
                    }
                }
            }
        }
        return null;

    }

}
