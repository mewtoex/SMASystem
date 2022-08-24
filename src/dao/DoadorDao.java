package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import util.BDUtil;
import util.ConexaoPostGree;
import model.Doador;

public class DoadorDao {

    private static DoadorDao doadorDao;

    public static DoadorDao getInstance() {
        if (doadorDao == null) {
            doadorDao = new DoadorDao();
        }
        return doadorDao;
    }

    public ArrayList<Doador> buscarTodos() {

        String sql
                = " select * from doador as d "
                + " order by d.nome ";

        ArrayList<Doador> retorno = new ArrayList<Doador>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getDoador(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public ArrayList<Doador> buscarNome(String n) {

        String sql = " select * from doador as d ";

        if (!n.equals("")) {
            sql = sql + " where (d.nome) like '" + n;
            sql = sql + "%'";
        }

        sql = sql + " order by d.nome ";

        ArrayList<Doador> retorno = new ArrayList<Doador>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getDoador(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public ArrayList<Doador> buscarCPF(String CPF) {

        String sql = " select * from doador as d ";

        if (!CPF.equals("")) {
            sql = sql + " where (d.\"CPF\") like '" + CPF;
            sql = sql + "%'";
        }

        sql = sql + " order by d.nome ";

        ArrayList<Doador> retorno = new ArrayList<Doador>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getDoador(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public ArrayList<Doador> buscarAtivo(Boolean a) {

        String sql = " select * from doador as d ";

        if (!a.equals("")) {
            sql = sql + " where (d.ativo) = " + a;

        }

        sql = sql + " order by d.nome ";

        ArrayList<Doador> retorno = new ArrayList<Doador>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getDoador(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public ArrayList<Doador> buscaridentidade(String identidade) {

        String sql = " select * from doador as d ";

        if (!identidade.equals("")) {
            sql = sql + " where (d.identidade) like '" + identidade;
            sql = sql + "%'";
        }

        sql = sql + " order by d.nome ";

        ArrayList<Doador> retorno = new ArrayList<Doador>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getDoador(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public Doador buscarID(Long pid) {

        String sql
                = " select * from doador as a  where a.id=" + pid;

        Doador retorno = null;
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno = getDoador(rs);
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    private Doador getDoador(ResultSet rs) throws SQLException {

        Doador e = new Doador();
        e.setId(rs.getLong("id"));
        e.setNome(rs.getString("nome"));
        e.setIdentidade(rs.getString("identidade"));
        e.setTelefone(rs.getString("telefone"));
        e.setProfissao(rs.getString("profissao"));
        e.setCPF(rs.getString("CPF"));
        e.setRua(rs.getString("rua"));
        e.setNumero(rs.getLong("numero"));
        e.setComplemento(rs.getString("complemento"));
        e.setBairro(rs.getString("bairro"));
        e.setCidade(rs.getString("cidade"));
        e.setEstado(rs.getString("estado"));
        e.setAtivo(rs.getBoolean("ativo"));

        return e;
    }

    public boolean persistir(Doador doador) {

        String sql;

        if (doador.getId() != null) {
            sql = " update doador set nome=?, identidade=?, telefone=?, profissao=?, \"CPF\"=?, rua=?, numero=?, complemento=?, bairro=?, cidade=?, estado=?, ativo=? "
                    + " where id = ? ";
        } else {
            doador.setId((BDUtil.getProxID()));

            sql = " insert into doador(nome, identidade, telefone, profissao, \"CPF\", rua, numero, complemento, bairro, cidade, estado, ativo, id) values "
                    + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        }

        PreparedStatement state;
        try {
            state = ConexaoPostGree.getConexao().prepareStatement(sql);

            state.setString(1, doador.getNome());
            state.setString(2, doador.getIdentidade());
            state.setString(3, doador.getTelefone());
            state.setString(4, doador.getProfissao());
            state.setString(5, doador.getCPF());
            state.setString(6, doador.getRua());
            state.setLong(7, doador.getNumero());
            state.setString(8, doador.getComplemento());
            state.setString(9, doador.getBairro());
            state.setString(10, doador.getCidade());
            state.setString(11, doador.getEstado());
            state.setBoolean(12, doador.getAtivo());
            state.setLong(13, doador.getId());

            state.executeUpdate();
            state.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro na persistência do doador. \nErro:" + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean deletar(Doador d) {
        if (d == null) {
            return false;
        }

        return delete(d.getId());
    }

    public boolean delete(Long id) {

        String sql = " delete from doador where id = ? ";
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
                = "select max(i.id) as total from doador as i ";

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

    public Doador buscaNome(String pnome) {

        Doador retorno = null;
        String sql
                = " select * from doador as i "
                + "where i.nome = '" + pnome + "'";

        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            if (rs.next()) {
                retorno = getDoador(rs);
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

}
