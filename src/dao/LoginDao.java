package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Login;
import util.BDUtil;
import util.ConexaoPostGree;

public class LoginDao {

    private static LoginDao loginDAO;

    public static LoginDao getInstance() {
        if (loginDAO == null) {
            loginDAO = new LoginDao();
        }
        return loginDAO;
    }

    public ArrayList<Login> buscarTodos() {

        String sql
                = " select id, usuario, senha, adm from login as i "
                + " order by i.id ";

        ArrayList<Login> retorno = new ArrayList<Login>();
        try {
            Statement state = ConexaoPostGree.getConexao().
                    createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {

                retorno.add(getLogin(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public ArrayList<Login> buscarTodos2() {

        String sql
                = " select id, usuario, senha from login as i "
                + " order by i.id ";

        ArrayList<Login> retorno = new ArrayList<Login>();
        try {
            Statement state = ConexaoPostGree.getConexao().
                    createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {

                retorno.add(getLogin2(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public Login buscarUsuarioSenha(String pnome, String psenha) {

        Login retorno = null;
        String sql
                = " select * from login as i "
                + "where i.usuario = '" + pnome + "' and i.senha= '" + psenha + "'";

        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            if (rs.next()) {
                retorno = getLogin(rs);
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        if (retorno == null) {
            System.err.println("Senha ou Usuario Invalido");
            return retorno;

        }

        return retorno;
    }

    public Login VerificarADM(Long Nid) {

        Login retorno = null;
        String sql
                = " select * from login as i "
                + "where i.adm = '" + true + "' and i.id= " + Nid;

        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            if (rs.next()) {
                retorno = getLogin(rs);
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    private Login getLogin(ResultSet rs) throws SQLException {

        Login i = new Login();
        i.setId(rs.getLong("id"));
        i.setUsuario(rs.getString("usuario"));
        i.setSenha(rs.getString("senha"));
        i.setAdm(rs.getBoolean("adm"));

        return i;
    }

    private Login getLogin2(ResultSet rs) throws SQLException {

        Login i = new Login();
        i.setId(rs.getLong("id"));
        i.setUsuario(rs.getString("usuario"));
        i.setSenha(rs.getString("senha"));

        return i;
    }

    public Login buscarID(Long pid) {

        String sql
                = " select * from login as a  where a.id=" + pid;

        Login retorno = null;
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno = getLogin(rs);
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public boolean persistir(Login login) {

        String sql;

        if (login.getId() != null) {
            sql = " update login set usuario=?, senha=? , adm=? "
                    + " where id = ? ";
        } else {
            login.setId(BDUtil.getProxID());

            sql = " insert into login (usuario, senha, adm, id) values "
                    + " (?, ?, ?, ?) ";
        }

        PreparedStatement state;
        try {
            state = ConexaoPostGree.getConexao().prepareStatement(sql);

            state.setString(1, login.getUsuario());
            state.setString(2, login.getSenha());
            state.setBoolean(3, login.getAdm());
            state.setLong(4, login.getId());

            state.executeUpdate();

            state.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro na persistência. \nErro:" + e.getMessage());
            return false;
        }

        return true;
    }

    public boolean deletar(Login login) {
        if (login == null) {
            return false;
        }

        return delete(login.getId());
    }

    public boolean delete(Long id) {

        String sql = " delete from login where id = ? ";
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
                = " select max(i.id) as total from login as i ";

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

    public ArrayList<Login> buscarusuarios(String s) {

        String sql = " select * from login as d ";

        if (!s.equals("")) {
            sql = sql + " where (d.usuario) like '" + s;
            sql = sql + "%'";
        }

        sql = sql + " order by d.usuario ";

        ArrayList<Login> retorno = new ArrayList<Login>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getLogin(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }
    
    public Login buscarUsuario(String pnome) {

        Login retorno = null;
        String sql
                = " select * from login as i "
                + "where i.usuario = '" + pnome + "'" ;

        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            if (rs.next()) {
                retorno = getLogin(rs);
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        

        return retorno;
    }

}