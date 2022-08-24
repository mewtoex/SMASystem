
package dao;

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

public class AssistidosDao {
    
    private static AssistidosDao assistidosDao;

    public static AssistidosDao getInstance() {
        if (assistidosDao == null) {
            assistidosDao = new AssistidosDao();
        }
        return assistidosDao;
    }   
    
      public ArrayList<Assistidos> buscarTodos() {

        String sql
                = " select * from assistidos as d "
                + " order by d.nome ";

        ArrayList<Assistidos> retorno = new ArrayList<Assistidos>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getAssistidos(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }
      private Assistidos getAssistidos(ResultSet rs) throws SQLException {

        Assistidos e = new Assistidos();
        e.setId(rs.getLong("id"));
        e.setNome(rs.getString("nome"));
        e.setIdentidade(rs.getString("identidade"));
        e.setTelefone(rs.getString("telefone"));
        e.setProfissao(rs.getString("profissao"));
        e.setCPF(rs.getString("CPF"));
        e.setRenda(rs.getString("renda"));
        e.setIdade(rs.getInt("idade"));
        e.setRua(rs.getString("rua"));
        e.setNumero(rs.getLong("numero"));
        e.setComplemento(rs.getString("complemento"));
        e.setBairro(rs.getString("bairro"));
        e.setCidade(rs.getString("cidade"));
        e.setEstado(rs.getString("estado"));
        e.setAtivo(rs.getBoolean("ativo"));
        
         List<Long> k;
        k = buscarAmp(rs.getLong("id"));
        e.setAmparatos(null);

        for (Long i : k) {

            e.addAmparatos(ProjetoDao.getInstance().buscarIDnXn(i));

        }
  
        return e;
    }
      public Assistidos buscaNome(String pnome) {

        Assistidos retorno = null;
        String sql
                = " select * from assistidos as i "
                + "where i.nome = '" + pnome + "'";

        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            if (rs.next()) {
                retorno = getAssistido(rs);
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }
    private void buscaNxn(Assistidos a) {

        List<Long> k;
        k = buscarAmp(a.getId());
        a.setAmparatos(null);

        for (Long i : k) {

            a.addAmparatos(ProjetoDao.getInstance().buscarIDnXn(i));

        }

    }
      
      private Assistidos getAssistido(ResultSet rs) throws SQLException {

        Assistidos e = new Assistidos();
        e.setId(rs.getLong("id"));
        e.setNome(rs.getString("nome"));
        e.setIdentidade(rs.getString("identidade"));
        e.setTelefone(rs.getString("telefone"));
        e.setProfissao(rs.getString("profissao"));
        e.setCPF(rs.getString("CPF"));
        e.setRenda(rs.getString("renda"));
        e.setIdade(rs.getInt("idade"));
        e.setRua(rs.getString("rua"));
        e.setNumero(rs.getLong("numero"));
        e.setComplemento(rs.getString("complemento"));
        e.setBairro(rs.getString("bairro"));
        e.setCidade(rs.getString("cidade"));
        e.setEstado(rs.getString("estado"));
        e.setAtivo(rs.getBoolean("ativo"));
        
      
  
        return e;
    }
    public ArrayList<Assistidos> buscarNome(String nome) {

        String sql = " select * from assistidos as d ";

        if (!nome.equals("")) {
              sql = sql + " where (d.nome) like '" + nome ;
              sql = sql + "%'";
        }

        sql = sql + " order by d.nome ";

        ArrayList<Assistidos> retorno = new ArrayList<Assistidos>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getAssistidos(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }  
    public ArrayList<Assistidos> buscarCPF(String CPF) {

        String sql = " select * from assistidos as d ";

        if (!CPF.equals("")) {
              sql = sql + " where (d.\"CPF\") like '" + CPF ;
              sql = sql + "%'";
        }

        sql = sql + " order by d.nome ";

        ArrayList<Assistidos> retorno = new ArrayList<Assistidos>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getAssistidos(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }  
    
    
      
    public ArrayList<Assistidos> buscaridentidade(String identidade) {

        String sql = " select * from assistidos as d ";

        if (!identidade.equals("")) {
            sql = sql + " where (d.identidade) like '" + identidade ;
            sql = sql + "%'";
        }

        sql = sql + " order by d.nome ";

        ArrayList<Assistidos> retorno = new ArrayList<Assistidos>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getAssistidos(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }
    public Assistidos buscarID(Long pid) {

        String sql
                = " select * from assistidos as a  where a.id=" + pid;

        Assistidos retorno = null;
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno = getAssistidos(rs);
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }
  public boolean  persistir(Assistidos assistidos) {

        String sql;

        if (assistidos.getId()!= null) {
            sql = " update assistidos set nome=?, identidade=?, telefone=?, profissao=?, \"CPF\"=?, renda=?, idade=?, rua=?, numero=?, complemento=?, bairro=?, cidade=?, estado=?, ativo=? "
                    + " where id = ? ";
        } else {
            assistidos.setId((BDUtil.getProxID()));

            sql = " insert into assistidos(nome, identidade, telefone, profissao, \"CPF\", renda, idade, rua, numero, complemento, bairro, cidade, estado, ativo, id) values "
                    + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        }

        PreparedStatement state;
        try {
            state = ConexaoPostGree.getConexao().prepareStatement(sql);

            state.setString(1, assistidos.getNome());
            state.setString(2, assistidos.getIdentidade());
            state.setString(3, assistidos.getTelefone());
            state.setString(4, assistidos.getProfissao());
            state.setString(5, assistidos.getCPF());
            state.setString(6, assistidos.getRenda());
            state.setInt(7, assistidos.getIdade());
            state.setString(8, assistidos.getRua());
            state.setLong(9, assistidos.getNumero());
            state.setString(10, assistidos.getComplemento());
            state.setString(11, assistidos.getBairro());
            state.setString(12, assistidos.getCidade());
            state.setString(13, assistidos.getEstado());
            state.setBoolean(14, assistidos.getAtivo());
            state.setLong(15, assistidos.getId());
            
            state.executeUpdate();
            state.close();
            buscaNxn(assistidos);
            persistirAmp(assistidos);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro na persistência do assistidos. \nErro:" + e.getMessage());
         return false;
        }
        return true;
    }
    public boolean deletar(Assistidos d) {
        if (d == null) {
            return false;
        }

        return delete(d.getId());
    }

    public boolean delete(Long id) {

        String sql = " delete from assistidos where id = ? ";
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
                = "select max(i.id) as total from assistidos as i ";

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
    private void persistirAmp(Assistidos c) {
        String sql = " delete from amparados where id_assistidos = ? ";
        try {
            PreparedStatement state = ConexaoPostGree.getConexao().prepareStatement(sql);
            state.setLong(1, c.getId());
            state.executeUpdate();
            state.close();

            sql = " insert into amparados (id_assistidos, id_projeto) values (?, ?)";

            for (Projeto i : c.getAmparatos()) {
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
    
       public ArrayList<Assistidos> buscarAtivo(Boolean a) {

        String sql = " select * from assistidos as d ";

        if (!a.equals("")) {
            sql = sql + " where (d.ativo) = " + a ;

        }

        sql = sql + " order by d.nome ";

        ArrayList<Assistidos> retorno = new ArrayList<Assistidos>();
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno.add(getAssistidos(rs));
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }
       
       public ArrayList<Long> buscarAmp(Long id_vol) {

        String sql
                = " select * from amparados as a  where a.id_assistidos=" + id_vol;

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
     public boolean persistirco(Assistidos am, Long g) {

        String sql;

            sql = " insert into amparados(id_projeto, id_assistidos) values "
                    + " (?, ?) ";
       

        PreparedStatement state;
        try {
            state = ConexaoPostGree.getConexao().prepareStatement(sql);

            state.setLong(1, g);
            state.setLong(2, am.getId());

            state.executeUpdate();
            state.close();
           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro na persistência do Assistidos. \nErro:" + e.getMessage());
            return false;
        }
        return true;
    }
       public Assistidos buscarIDnXn(Long pid) {

        String sql
                = " select * from assistidos as a "
                + " where a.id=" + pid;

        Assistidos retorno = null;
        try {
            Statement state = ConexaoPostGree.getConexao().createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                retorno = getAssistido(rs);
            }

            state.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return retorno;
    }
}
