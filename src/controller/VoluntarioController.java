package controller;

import dao.VoluntarioDao;
import java.util.ArrayList;
import java.util.List;
import model.Voluntario;

public class VoluntarioController {

    private static VoluntarioController voluntarioController;

    public static VoluntarioController getInstance() {
        if (voluntarioController == null) {
            voluntarioController = new VoluntarioController();
        }
        return voluntarioController;
    }

    public boolean deletar(Long id) {
        return VoluntarioDao.getInstance().delete(id);

    }

    public void persitirNxn(Voluntario d, Long h) {
        Long m;
        m = h;
        Voluntario n = d;
        VoluntarioDao.getInstance().persistirco(n, m);

    }

    public void persistir(Voluntario d) {
        VoluntarioDao.getInstance().persistir(d);
    }

    public Voluntario buscarID(Long id) {

        return VoluntarioDao.getInstance().buscarID(id);
    }

    public List<Voluntario> buscar(String texto) {

        if (texto == null) {

            return VoluntarioDao.getInstance().buscarTodos();

        }

        return VoluntarioDao.getInstance().buscaridentidade(texto);

    }

    public List<Voluntario> BuscaTodos() {

        return VoluntarioDao.getInstance().buscarTodos();

    }

    public Voluntario BuscaNome(String b) {
        
        String texto= b;
        
        return VoluntarioDao.getInstance().buscaNome(texto);
    }

    public List<Voluntario> busca(int indice, String texto) {

        if (texto == null) {

            return VoluntarioDao.getInstance().buscarTodos();

        } else {
            switch (indice) {

                case 0: {
                    return VoluntarioDao.getInstance().buscarNome(texto);
                }
                case 1: {
                    return VoluntarioDao.getInstance().buscarCPF(texto);
                }
                case 2: {
                    return VoluntarioDao.getInstance().buscaridentidade(texto);

                }

                case 3: {
                    if (("Sim".equals(texto) ^ "sim".equals(texto)) ^ ("Ativo".equals(texto) ^ "ativo".equals(texto))) {
                        return VoluntarioDao.getInstance().buscarAtivo(true);

                    } else if ((("Nao".equals(texto)) ^ ("Não".equals(texto)))
                            ^ ((("Desativo".equals(texto)) ^ ("desativo".equals(texto)))
                            ^ (("nao".equals(texto)) ^ ("não".equals(texto))))) {
                        return VoluntarioDao.getInstance().buscarAtivo(false);
                    }
                }
            }
        }
        return null;

    }

}
