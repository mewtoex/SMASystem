package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Assistidos;
import model.Projeto;
import util.BDUtil;
import util.ConexaoPostGree;
import util.DateTimeUtil;

public class ProjetoDao {

    private static ProjetoDao projetoDao;

    public static ProjetoDao getInstance() {
        if (projetoDao == null) {
            projetoDao = new ProjetoDao();
        }
        return projetoDao;
    }

    public ArrayList<Projeto> buscarTodos() {

        String sql
                = " select * from projeto as d "
                + " order by d.nome ";

        ArrayList<Projeto> retorno = new ArrayList<Projeto>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getProjeto(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public ArrayList<Projeto> buscarNome(String nome) {

        String sql = " select * from projeto as d ";

        if (!nome.equals("")) {
            sql = sql + " where (d.nome) like '" + nome;
            sql = sql + "%'";
        }

        sql = sql + " order by d.nome ";

        ArrayList<Projeto> retorno = new ArrayList<Projeto>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getProjeto(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    private Projeto getProjeto(ResultSet rs) throws SQLException {

        Projeto e = new Projeto();
        e.setId(rs.getLong("id"));
        e.setNome(rs.getString("nome"));
        e.setData(rs.getString("data"));
        e.setDescricao(rs.getString("descricao"));
        e.setAtivo(rs.getBoolean("ativo"));
        List<Long> k;
        e.setcolabora(null);
        k = buscarCola(rs.getLong("id"));
        for (Long i : k) {
            e.addcolabora(VoluntarioDao.getInstance().buscarIDnXn(i));

        }
        
        List<Long> mg;
        e.setAmparatos(null);
        mg = buscarAmp(rs.getLong("id"));
        for (Long i : mg) {
            e.addAmparatos(AssistidosDao.getInstance().buscarIDnXn(i));

        }
        
        

        // e.setcolabora(buscarCola("id"));
        return e;
    }
    
    
      private void buscaNxn(Projeto a) {
      
      
        List<Long> mg;
        mg = buscarAmp(a.getId());
        a.setAmparatos(null);

        for (Long i : mg) {

        a.addAmparatos(AssistidosDao.getInstance().buscarIDnXn(i));

        }
        
        List<Long> k;
        a.setcolabora(null);
        k = buscarCola(a.getId());
        for (Long i : k) {
            a.addcolabora(VoluntarioDao.getInstance().buscarIDnXn(i));

        }
      
      }
    
    private Projeto getProjetoNxN(ResultSet rs) throws SQLException {

        Projeto e = new Projeto();
        e.setId(rs.getLong("id"));
        e.setNome(rs.getString("nome"));
        e.setData(rs.getString("data"));
        e.setDescricao(rs.getString("descricao"));
        e.setAtivo(rs.getBoolean("ativo"));

      
        return e;
    }

    public Projeto buscarID(Long pid) {

        String sql
                = " select * from projeto as a  where a.id=" + pid;

        Projeto retorno = null;
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno = getProjeto(rs);
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public Projeto buscarIDnXn(Long pid) {

        String sql
                = " select * from projeto as a  where a.id=" + pid;

        Projeto retorno = null;
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno = getProjetoNxN(rs);
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public boolean persistir(Projeto projeto) {

        String sql;

        if (projeto.getId() != null) {
            sql = " update projeto set nome=?, data=?, descricao=?, ativo=? "
                    + " where id = ? ";
        } else {
            projeto.setId((BDUtil.getProxID()));

            sql = " insert into projeto(nome, data, descricao, ativo, id) values "
                    + " (?, ?, ?, ?, ?) ";
        }

        PreparedStatement state;
        try {
            state = ConexaoPostGree.getConexao().prepareStatement(sql);

            state.setString(1, projeto.getNome());
            state.setString(2, projeto.getData());
            state.setString(3, projeto.getDescricao());
            state.setBoolean(4, projeto.getAtivo());
            state.setLong(5, projeto.getId());

            state.executeUpdate();
            state.close();
            buscaNxn(projeto);
            persistirAmp(projeto);
            persistirCol(projeto);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro na persistência do projeto. \nErro:" + e.getMessage());
            return false;
        }
        return true;
    }

    private void persistirAmp(Projeto c) {
        String sql = " delete from amparados where id_projeto = ? ";
        try {
            PreparedStatement state = ConexaoPostGree.getConexao().prepareStatement(sql);
            state.setLong(1, c.getId());
            state.executeUpdate();
            state.close();

            sql = " insert into amparados (id_assistidos, id_projeto) values (?, ?)";

            for (Assistidos i : c.getAmparatos()) {
                state = ConexaoPostGree.getConexao().prepareStatement(sql);
                state.setLong(1, i.getId());
                state.setLong(2, c.getId());

                state.executeUpdate();
                state.close();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro na persistência. \nErro:" + e.getMessage());

        }

    }

    private void persistirCol(Projeto c) {
        String sql = " delete from colabora where id_projeto = ? ";
        try {
            PreparedStatement state = ConexaoPostGree.getConexao().prepareStatement(sql);
            state.setLong(1, c.getId());
            state.executeUpdate();
            state.close();

            sql = " insert into colabora (id_projeto, id_voluntario) values (?, ?)";

            for (Assistidos i : c.getAmparatos()) {
                state = ConexaoPostGree.getConexao().prepareStatement(sql);
                state.setLong(1, c.getId());
                state.setLong(2, i.getId());

                state.executeUpdate();
                state.close();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro na persistência. \nErro:" + e.getMessage());

        }

    }

    public boolean deletar(Projeto d) {
        if (d == null) {
            return false;
        }

        return delete(d.getId());
    }

    public boolean delete(Long id) {

        String sql = " delete from projeto where id = ? ";
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
                = "select max(i.id) as total from projeto as i ";

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

    public ArrayList<Projeto> buscarAtivo(Boolean a) {

        String sql = " select * from projeto as d ";

        if (!a.equals("")) {
            sql = sql + " where (d.ativo) = " + a;

        }

        sql = sql + " order by d.nome ";

        ArrayList<Projeto> retorno = new ArrayList<Projeto>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getProjeto(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public ArrayList<Projeto> buscarMes(String data) {

        String sql = " select * from projeto as d ";

        if (!data.equals("")) {
            sql = sql + " where (d.data) like  '%" + data + "%'";
        }
        ArrayList<Projeto> retorno = new ArrayList<Projeto>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getProjeto(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public ArrayList<Projeto> buscarAno(String data) {

        String sql = " select * from projeto as d ";

        if (!data.equals("")) {
            sql = sql + " where (d.data) like  '%" + data + "%'";
        }
        ArrayList<Projeto> retorno = new ArrayList<Projeto>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getProjeto(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public ArrayList<Projeto> buscarMesAno(String data) {

        String sql = " select * from projeto as d ";

        if (!data.equals("")) {
            sql = sql + " where (d.data) like  '%" + data + "%'";
        }
        ArrayList<Projeto> retorno = new ArrayList<Projeto>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getProjeto(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public Projeto buscaNome(String pnome) {

        Projeto retorno = null;
        String sql
                = " select * from projeto as i "
                + "where i.nome = '" + pnome + "'";

        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            if (rs.next()) {
                retorno = getProjeto(rs);
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public boolean persistirco(Projeto v, Long g) {

        String sql;

        sql = " insert into colabora(id_voluntario, id_projeto) values "
                + " (?, ?) ";

        PreparedStatement state;
        try {
            state = ConexaoPostGree.getConexao().prepareStatement(sql);

            state.setLong(1, g);
            state.setLong(2, v.getId());

            state.executeUpdate();
            state.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro na persistência do voluntario. \nErro:" + e.getMessage());
            return false;
        }
        return true;
    }

    public ArrayList<Long> buscarCola(Long id_proje) {

        String sql
                = " select * from colabora as a  where a.id_projeto=" + id_proje;

        ArrayList<Long> b = new ArrayList<Long>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                b.add(getColabora(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return b;

    }

    private Long getColabora(ResultSet rs) throws SQLException {

        Long e;
        e = (rs.getLong("id_voluntario"));
        return e;
    }
    
    public boolean persistirAm(Projeto v, Long g) {

        String sql;

        sql = " insert into amparados(id_assistidos, id_projeto) values "
                + " (?, ?) ";

        PreparedStatement state;
        try {
            state = ConexaoPostGree.getConexao().prepareStatement(sql);

            state.setLong(1, g);
            state.setLong(2, v.getId());

            state.executeUpdate();
            state.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro na persistência do Amparato. \nErro:" + e.getMessage());
            return false;
        }
        return true;
    }

    public ArrayList<Long> buscarAmp(Long id_proje) {

        String sql
                = " select * from amparados as a  where a.id_projeto =" + id_proje;

        ArrayList<Long> v = new ArrayList<Long>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                v.add(getAmp(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return v;

    }

    private Long getAmp(ResultSet rs) throws SQLException {

        Long e;
        e = (rs.getLong("id_assistidos"));
        return e;
    }
}
