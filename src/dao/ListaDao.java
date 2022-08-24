package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Assistidos;
import model.Lista;
import util.BDUtil;
import util.ConexaoPostGree;

public class ListaDao {

    private static ListaDao listaDao;

    public static ListaDao getInstance() {
        if (listaDao == null) {
            listaDao = new ListaDao();
        }
        return listaDao;
    }

    public ArrayList<Lista> buscarTodos() {

        String sql
                = " select * from lista as a "
                + " order by a.id ";

        ArrayList<Lista> retorno = new ArrayList<Lista>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getlista(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public Lista buscarID(Long pid) {

        String sql
                = " select * from lista as a "
                + " where a.id=" + pid;

        Lista retorno = null;
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno = getlista(rs);
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    private Lista getlista(ResultSet rs) throws SQLException {

        Lista c = new Lista();
        c.setId(rs.getLong("id"));
        c.setData(rs.getString("data"));
        c.setpres(rs.getBoolean("presenca"));
        c.setPro(ProjetoDao.getInstance().buscarID(rs.getLong("id_projeto")));
        c.setAss(AssistidosDao.getInstance().buscarID(rs.getLong("id_assistidos")));

        return c;
    }

    public boolean deletar(Long id) {

        String sql = " delete from lista where id = ? ";

        PreparedStatement state;
        try {
            state = ConexaoPostGree.getConexao().prepareStatement(sql);
            state.setLong(1, id);

            state.executeUpdate();
            state.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao deletar a Lista. \nErro:" + e.getMessage());
            return false;
        }
    }

    public boolean deletar(Lista d) {
        if (d == null) {
            return false;
        }

        return delete(d.getId());
    }

    public boolean delete(Long id) {

        String sql = " delete from lista where id = ? ";
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
                = " select max(i.id) as total from lista as i ";

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

    public boolean persistir(Lista d) {

        String sql;

        if (d.getId() != null) {
            sql = " update lista set data=?, presenca=?, id_assistidos=?, id_projeto=?  "
                    + " where id = ? ";
        } else {
            d.setId((BDUtil.getProxID()));

            sql = " insert into lista(data, presenca, id_assistidos, id_projeto, id) values "
                    + " (?, ?, ?, ?, ?) ";
        }

        ArrayList<Assistidos> m = new ArrayList<Assistidos>();
        m = d.getPro().getAmparatos();
        Long bol = d.getId();
        for (Assistidos i : m) {
            
            d.setAss(i);
           
            PreparedStatement state;
            try {
                state = ConexaoPostGree.getConexao().prepareStatement(sql);

                state.setString(1, d.getData());
                state.setBoolean(2, d.getpres());
                state.setLong(3, d.getAss().getId());
                state.setLong(4, d.getPro().getId());
                state.setLong(5, bol);
                bol ++ ;
                state.executeUpdate();
                state.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro na persistência da Lista. \nErro:" + e.getMessage());
                return false;
            }
            
        }
        return true;
    }
    

    public ArrayList<Lista> buscarMes(String data) {

        String sql = " select * from lista as d ";

        if (!data.equals("")) {
            sql = sql + " where (d.data) like  '%" + data + "%' order by d.data";
        }
        ArrayList<Lista> retorno = new ArrayList<Lista>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getlista(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public ArrayList<Lista> buscarAno(String data) {

        String sql = " select * from lista as d ";

        if (!data.equals("")) {
            sql = sql + " where (d.data) like  '%" + data + "%' order by d.data";
        }
        ArrayList<Lista> retorno = new ArrayList<Lista>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getlista(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public ArrayList<Lista> buscarMesAno(String data) {

        String sql = " select * from lista as d ";

        if (!data.equals("")) {
            sql = sql + " where (d.data) like  '%" + data + "%' order by d.data";
        }
        ArrayList<Lista> retorno = new ArrayList<Lista>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getlista(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public ArrayList<Lista> buscardata(String data) {

        String sql = " select * from lista as d ";

        if (!data.equals("")) {
            sql = sql + " where (d.data) =  '" + data + "' order by d.data";
        }
        ArrayList<Lista> retorno = new ArrayList<Lista>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getlista(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public Lista buscarAssis(Long id_ass) {

        Lista retorno = null;
        String sql
                = " select * from lista as i "
                + "where i.id_assistidos = " + id_ass;

        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            if (rs.next()) {
                retorno = getlista(rs);
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public ArrayList<Lista> buscarPro(Long id_Pro , String d) {

       
        String sql
                = " select * from lista as i "
                + "where i.id_projeto = " + id_Pro +" and i.data ='"+ d +"'";

        ArrayList<Lista> retorno = new ArrayList<Lista>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getlista(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public ArrayList<Lista> buscarAtivo(Boolean a) {

        String sql = " select * from lista as d ";

        if (!a.equals("")) {
            sql = sql + " where (d.presenca) = " + a;

        }

        sql = sql + " order by d.data ";

        ArrayList<Lista> retorno = new ArrayList<Lista>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getlista(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

}
