
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import util.BDUtil;
import util.ConexaoPostGree;
import model.Despesas;


public class DespesasDao {
  
    private static DespesasDao despesasDao;

    public static DespesasDao getInstance() {
        if (despesasDao == null) {
            despesasDao = new DespesasDao();
        }
        return despesasDao;
    }

    public ArrayList<Despesas> buscarTodos() {

        String sql
                = " select * from despesas as a "
                + " order by a.id ";

        ArrayList<Despesas> retorno = new ArrayList<Despesas>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getDespesas(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public Despesas buscarID(Long pid) {

        String sql
                = " select * from despesas as a "
                + " where a.id=" + pid;

        Despesas retorno = null;
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno = getDespesas(rs);
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    private Despesas getDespesas(ResultSet rs) throws SQLException {

        Despesas c = new Despesas();
        c.setId(rs.getLong("id"));
        c.setNome(rs.getString("nome"));
        c.setValor(rs.getString("valor"));
        c.setData(rs.getString("data"));
        c.setAberto(rs.getString("aberto"));
        c.setAtivo(rs.getBoolean("ativo"));

  
        c.setDoacao(DoacaoDao.getInstance().buscarID(rs.getLong("id_doacao")));

        return c;
    }

    public boolean deleta(Long id) {

        String sql = " delete from despesas where id = ? ";

        PreparedStatement state;
        try {
            state = ConexaoPostGree.getConexao().prepareStatement(sql);
            state.setLong(1, id);

            state.executeUpdate();
            state.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao deletar a despesa. \nErro:" + e.getMessage());
            return false;
        }
    }

    
    public boolean deletar(Despesas d) {
        if (d == null) {
            return false;
        }

        return deleta(d.getId());
    }

    public Long getLastId() {
        String sql
                = " select max(i.id) as total from despesas as i ";

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
  
    public boolean  persistir(Despesas d) {

        String sql;

        if (d.getId()!= null) {
            sql = " update despesas set nome=?, valor=?, data=?, ativo=?, aberto=?, id_doacao=?  "
                    + " where id = ? ";
        } else {
            d.setId((BDUtil.getProxID()));

            sql = " insert into despesas(nome, valor, data, ativo, aberto, id_doacao, id) values "
                    + " (?, ?, ?, ?, ?, ?, ?) ";
        }

        PreparedStatement state;
        try {
            state = ConexaoPostGree.getConexao().prepareStatement(sql);

            state.setString(1, d.getNome());
            state.setString(2, d.getValor());
            state.setString(3, d.getData());
            state.setBoolean(4, d.getAtivo());
            state.setString(5, d.getAberto());
            state.setLong(6, d.getDoacao().getId());
            state.setLong(7, d.getId());
            
            state.executeUpdate();
            state.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro na persistência da despesa. \nErro:" + e.getMessage());
         return false;
        }
        return true;
    }
    
    public ArrayList<Despesas> buscarMes(String data) {

        String sql = " select * from despesas as d ";

        if (!data.equals("")) {
           sql = sql + " where (d.data) like  '%/" + data + "/%'" ;
        }
        ArrayList<Despesas> retorno = new ArrayList<Despesas>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getDespesas(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }
    
    public ArrayList<Despesas> buscarAno(String data) {

        String sql = " select * from despesas as d ";

        if (!data.equals("")) {
           sql = sql + " where (d.data) like  '%/" + data + "'" ;
        }
        ArrayList<Despesas> retorno = new ArrayList<Despesas>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getDespesas(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }
    
      public ArrayList<Despesas> buscarNome(String n) {

        String sql = " select * from despesas as d ";

        if (!n.equals("")) {
           sql = sql + " where (d.nome) like  '%" + n + "%'" ;
        }
        ArrayList<Despesas> retorno = new ArrayList<Despesas>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getDespesas(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }
     
    public ArrayList<Despesas> buscarMesAno(String data) {

        String sql = " select * from despesas as d ";

        if (!data.equals("")) {
           sql = sql + " where (d.data) like  '%" + data + "%'" ;
        }
        ArrayList<Despesas> retorno = new ArrayList<Despesas>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getDespesas(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }
       public ArrayList<Despesas> buscarAtivo(Boolean a) {

        String sql = " select * from despesas as d ";

        if (!a.equals("")) {
            sql = sql + " where (d.ativo) = " + a ;

        }

        sql = sql + " order by d.nome ";

        ArrayList<Despesas> retorno = new ArrayList<Despesas>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getDespesas(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }    
}

