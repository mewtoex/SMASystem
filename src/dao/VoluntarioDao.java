package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Projeto;
import util.BDUtil;
import util.ConexaoPostGree;
import model.Voluntario;

public class VoluntarioDao {

    private static VoluntarioDao voluntarioDao;

    public static VoluntarioDao getInstance() {
        if (voluntarioDao == null) {
            voluntarioDao = new VoluntarioDao();
        }
        return voluntarioDao;
    }

    public ArrayList<Voluntario> buscarTodos() {

        String sql
                = " select * from voluntario as a "
                + " order by a.id ";

        ArrayList<Voluntario> retorno = new ArrayList<Voluntario>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getVoluntarioo(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public Voluntario buscarID(Long pid) {

        String sql
                = " select * from voluntario as a "
                + " where a.id=" + pid;

        Voluntario retorno = null;
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno = getVoluntarioo(rs);
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public Voluntario buscarIDnXn(Long pid) {

        String sql
                = " select * from voluntario as a "
                + " where a.id=" + pid;

        Voluntario retorno = null;
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno = getVoluntario(rs);
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    private Voluntario getVoluntario(ResultSet rs) throws SQLException {

        Voluntario e = new Voluntario();
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
        e.setDia_atuacao(rs.getString("dia_atuacao"));
        e.setHora(rs.getString("hora"));

        e.setLog(LoginDao.getInstance().buscarID(rs.getLong("id_login")));

        return e;
    }

    private Voluntario getVoluntarioo(ResultSet rs) throws SQLException {

        Voluntario e = new Voluntario();
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
        e.setDia_atuacao(rs.getString("dia_atuacao"));
        e.setHora(rs.getString("hora"));

        List<Long> k;
        k = buscarCola(rs.getLong("id"));
        e.setcolabora(null);

        for (Long i : k) {

            e.addcolabora(ProjetoDao.getInstance().buscarID(i));

        }

        e.setLog(LoginDao.getInstance().buscarID(rs.getLong("id_login")));
        return e;
    }

    public boolean deletar(Long id) {

        String sql = " delete from voluntario where id = ? ";

        PreparedStatement state;
        try {
            state = ConexaoPostGree.getConexao().prepareStatement(sql);
            state.setLong(1, id);

            state.executeUpdate();
            state.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao deletar o Voluntarioo. \nErro:" + e.getMessage());
            return false;
        }
    }

    public boolean deletar(Voluntario voluntario) {
        if (voluntario == null) {
            return false;
        }

        return delete(voluntario.getId());
    }

    public boolean delete(Long id) {

        String sql = " delete from voluntario where id = ? ";
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
                = " select max(i.id) as total from voluntario as i ";

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

    public boolean persistir(Voluntario voluntario) {

        String sql;

        if (voluntario.getId() != null) {
            sql = " update voluntario set nome=?, identidade=?, telefone=?, profissao=?, \"CPF\"=?, rua=?, numero=?, complemento=?, bairro=?, cidade=?, estado=?, dia_atuacao=?, hora=?, ativo=?, id_login=?  "
                    + " where id = ? ";
        } else {
            voluntario.setId((BDUtil.getProxID()));

            sql = " insert into voluntario(nome, identidade, telefone, profissao, \"CPF\", rua, numero, complemento, bairro, cidade, estado, dia_atuacao, hora, ativo, id_login, id) values "
                    + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        }

        PreparedStatement state;
        try {
            state = ConexaoPostGree.getConexao().prepareStatement(sql);

            state.setString(1, voluntario.getNome());
            state.setString(2, voluntario.getIdentidade());
            state.setString(3, voluntario.getTelefone());
            state.setString(4, voluntario.getProfissao());
            state.setString(5, voluntario.getCPF());
            state.setString(6, voluntario.getRua());
            state.setLong(7, voluntario.getNumero());
            state.setString(8, voluntario.getComplemento());
            state.setString(9, voluntario.getBairro());
            state.setString(10, voluntario.getCidade());
            state.setString(11, voluntario.getEstado());
            state.setString(12, voluntario.getDia_atuacao());
            state.setString(13, voluntario.getHora());
            state.setBoolean(14, voluntario.getAtivo());
            state.setLong(15, voluntario.getLog().getId());
            state.setLong(16, voluntario.getId());

            state.executeUpdate();
            state.close();
            buscaNxn(voluntario);
            persistirCol(voluntario);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro na persistência do voluntario. \nErro:" + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean persistirco(Voluntario voluntario, Long g) {

        String sql;

            sql = " insert into colabora(id_projeto, id_voluntario) values "
                    + " (?, ?) ";
       

        PreparedStatement state;
        try {
            state = ConexaoPostGree.getConexao().prepareStatement(sql);

            state.setLong(1, g);
            state.setLong(2, voluntario.getId());

            state.executeUpdate();
            state.close();
           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro na persistência do voluntario. \nErro:" + e.getMessage());
            return false;
        }
        return true;
    }

    public void persistirCol(Voluntario c) {
        String sql = " delete from colabora where id_voluntario = ? ";
        try {
            PreparedStatement state = ConexaoPostGree.getConexao().prepareStatement(sql);
            state.setLong(1, c.getId());
            state.executeUpdate();
            state.close();

            sql = " insert into colabora (id_projeto, id_voluntario) values (?, ?)";

            for (Projeto i : c.getcolabora()) {
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

    public ArrayList<Voluntario> buscarNome(String n) {

        String sql = " select * from Voluntario as d ";

        if (!n.equals("")) {
            sql = sql + " where (d.nome) like '" + n;
            sql = sql + "%'";
        }

        sql = sql + " order by d.nome ";

        ArrayList<Voluntario> retorno = new ArrayList<Voluntario>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getVoluntarioo(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }
     public Voluntario buscaNome(String pnome) {

        Voluntario retorno = null;
        String sql
                = " select * from voluntario as i "
                + "where i.nome = '" + pnome + "'";

        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            if (rs.next()) {
                retorno = getVoluntario(rs);
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public ArrayList<Voluntario> buscarCPF(String CPF) {

        String sql = " select * from voluntario as d ";

        if (!CPF.equals("")) {
            sql = sql + " where (d.\"CPF\") like '" + CPF;
            sql = sql + "%'";
        }

        sql = sql + " order by d.nome ";

        ArrayList<Voluntario> retorno = new ArrayList<Voluntario>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getVoluntarioo(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public ArrayList<Voluntario> buscarAtivo(Boolean a) {

        String sql = " select * from voluntario as d ";

        if (!a.equals("")) {
            sql = sql + " where (d.ativo) = " + a;

        }

        sql = sql + " order by d.nome ";

        ArrayList<Voluntario> retorno = new ArrayList<Voluntario>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getVoluntarioo(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public ArrayList<Voluntario> buscaridentidade(String identidade) {

        String sql = " select * from voluntario as d ";

        if (!identidade.equals("")) {
            sql = sql + " where (d.identidade) like '" + identidade;
            sql = sql + "%'";
        }

        sql = sql + " order by d.nome ";

        ArrayList<Voluntario> retorno = new ArrayList<Voluntario>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getVoluntarioo(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }

    public ArrayList<Long> buscarCola(Long id_vol) {

        String sql
                = " select * from colabora as a  where a.id_voluntario=" + id_vol;

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
        e = (rs.getLong("id_projeto"));
        return e;
    }
    
    private void buscaNxn(Voluntario a) {

        List<Long> k;
        k = buscarCola(a.getId());
        a.setcolabora(null);

        for (Long i : k) {

            a.addcolabora(ProjetoDao.getInstance().buscarIDnXn(i));

        }

    }
}
