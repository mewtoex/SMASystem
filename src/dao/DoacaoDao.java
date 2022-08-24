package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Doacao;
import util.BDUtil;
import util.ConexaoPostGree;

public class DoacaoDao {

    private static DoacaoDao doacaoDao;

    public static DoacaoDao getInstance() {
        if (doacaoDao == null) {
            doacaoDao = new DoacaoDao();
        }
        return doacaoDao;
    }

    public ArrayList<Doacao> buscarTodos() {

        String sql
                = " select * from doacao as a "
                + " order by a.id ";

        ArrayList<Doacao> retorno = new ArrayList<Doacao>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getDoacao(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public Doacao buscarID(Long pid) {

        String sql
                = " select * from doacao as a "
                + " where a.id=" + pid;

        Doacao retorno = null;
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno = getDoacao(rs);
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    private Doacao getDoacao(ResultSet rs) throws SQLException {

        Doacao c = new Doacao();
        c.setId(rs.getLong("id"));
        c.setQuantidade(rs.getInt("quantidade"));
        c.setDescricao(rs.getString("descricao"));
        c.setData(rs.getString("data"));
        c.setAtivo(rs.getBoolean("ativo"));

        c.setDoador(DoadorDao.getInstance().buscarID(rs.getLong("id_doador")));
        c.setProjeto(ProjetoDao.getInstance().buscarID(rs.getLong("id_projeto")));

        return c;
    }

    public boolean deletar(Long id) {

        String sql = " delete from doacao where id = ? ";

        PreparedStatement state;
        try {
            state = ConexaoPostGree.getConexao().prepareStatement(sql);
            state.setLong(1, id);

            state.executeUpdate();
            state.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao deletar o doacao. \nErro:" + e.getMessage());
            return false;
        }
    }

    public boolean deletar(Doacao d) {
        if (d == null) {
            return false;
        }

        return delete(d.getId());
    }

    public boolean delete(Long id) {

        String sql = " delete from doacao where id = ? ";
        int total = 0;
        try {
            PreparedStatement state = ConexaoPostGree.getConexao().prepareStatement(sql);
            state.setLong(1, id);

            total = state.executeUpdate();

            state.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro na persistência. \nErro:" + e.getMessage());
        }

        return total > 0;
    }

    public Long getLastId() {
        String sql
                = " select max(i.id) as total from doacao as i ";

        Long retorno = null;
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            if (rs.next()) {
                retorno = rs.getLong("total");
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public boolean persistir(Doacao d) {

        String sql;

        if (d.getId() != null) {
            sql = " update doacao set quantidade=?, data=?, descricao=?, ativo=?, id_doador=?, id_projeto=?  "
                    + " where id = ? ";
        } else {
            d.setId((BDUtil.getProxID()));

            sql = " insert into doacao(quantidade, data, descricao, ativo, id_doador, id_projeto, id) values "
                    + " (?, ?, ?, ?, ?, ?, ?) ";
        }

        PreparedStatement state;
        try {
            state = ConexaoPostGree.getConexao().prepareStatement(sql);

            state.setInt(1, d.getQuantidade());
            state.setString(2, d.getData());
            state.setString(3, d.getDescricao());
            state.setBoolean(4, d.getAtivo());
            state.setLong(5, d.getDoador().getId());
            state.setLong(6, d.getProjeto().getId());
            state.setLong(7, d.getId());

            state.executeUpdate();
            state.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro na persistência da doacoes. \nErro:" + e.getMessage());
            return false;
        }
        return true;
    }

    public ArrayList<Doacao> buscarMes(String data) {

        String sql = " select * from doacao as d ";

        if (!data.equals("")) {
            sql = sql + " where (d.data) like  '%" + data + "%'";
        }
        ArrayList<Doacao> retorno = new ArrayList<Doacao>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getDoacao(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public ArrayList<Doacao> buscarAno(String data) {

        String sql = " select * from doacao as d ";

        if (!data.equals("")) {
            sql = sql + " where (d.data) like  '%" + data + "%'";
        }
        ArrayList<Doacao> retorno = new ArrayList<Doacao>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getDoacao(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public ArrayList<Doacao> buscarMesAno(String data) {

        String sql = " select * from doacao as d ";

        if (!data.equals("")) {
            sql = sql + " where (d.data) like  '%" + data + "%'";
        }
        ArrayList<Doacao> retorno = new ArrayList<Doacao>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getDoacao(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public ArrayList<Doacao> buscarAtivo(Boolean a) {

        String sql = " select * from doacao as d ";

        if (!a.equals("")) {
            sql = sql + " where (d.ativo) = " + a;

        }

        sql = sql + " order by d.nome ";

        ArrayList<Doacao> retorno = new ArrayList<Doacao>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getDoacao(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public Doacao buscard(String pnome) {

        Doacao retorno = null;
        String sql
                = " select * from doacao as i "
                + "where i.descricao = '" + pnome + "'" ;

        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            if (rs.next()) {
                retorno = getDoacao(rs);
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        

        return retorno;
    }

}
