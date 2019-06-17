/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import modelo.Professor;

/**
 *
 * @author jamil
 */
public class ProfessorDAO {

	 private Connection conexao;

	    public ProfessorDAO(Connection conexao) {
	        this.conexao = conexao;
	    }

	    
	    public Professor salvar(Professor prof) {
	        String sql = "insert into professor(codigo, nome) values(?,?)";

	        try (PreparedStatement statement = conexao.prepareStatement(sql)) {

	            statement.setInt(1, prof.getCodigo());
	            statement.setString(2, prof.getNome());

	            statement.execute();

	           
	        } catch (SQLException e) {
	            throw new RuntimeException(e.getMessage());
	        }

	        return prof;
	    }

	  
	    public List<Professor> buscarTodosOsProfessores() {
	        String sql = "select * from Professor";

	        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
	            try (ResultSet resultSet = statement.executeQuery(sql)) {

	                final List<Professor> profs = new ArrayList<>();

	                while (resultSet.next()) {
	                    Professor professor = monta(resultSet);
	                    profs.add(professor);
	                }
	                return profs;
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e.getMessage());
	        }
	    }

	 
	    private Professor monta(ResultSet resultSet) {
	        try {
	            String nome = resultSet.getString("nome");
	            Integer codigo = resultSet.getInt("codigo");
	        
	            Professor professor = new Professor(codigo, nome);
	            
	            return professor;
	        } catch (SQLException e) {
	            throw new RuntimeException(e.getMessage());
	        }
	    }

	    
	 

	    public Professor buscaPorNome(String nome) {
	        String sql = "select * from professor where nome = ?";

	        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
	            statement.setString(1, nome);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next())
	                    return monta(resultSet);
	                return null;
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e.getMessage());
	        }
	    }

	    public Professor buscaPorCodigo(Integer codigo) {
	        String sql = "select * from professor where codigo = ?";

	        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
	            statement.setInt(1, codigo);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next())
	                    return monta(resultSet);
	                return null;
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e.getMessage());
	        }
	    }

	    public Professor atualizar(Professor professor) {
	        String sql = "update professor set nome = ? where codigo = ?";

	        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
	          
	            statement.setString(1, professor.getNome());
	            statement.setInt(2, professor.getCodigo());
	           

	            statement.execute();
	        } catch (SQLException e) {
	            throw new RuntimeException(e.getMessage());
	        }
	        return professor;
	    }

	  
	    public Professor deletar(Professor professor) {
	        String sql = "delete from professor where codigo = ?";

	        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
	            statement.setInt(1, professor.getCodigo());

	            statement.execute();
	        } catch (SQLException e) {
	            throw new RuntimeException(e.getMessage());
	        }
	        return professor;
	    }

	   
	    public int existe(Professor professor) {
	        Professor p = buscaPorCodigo(professor.getCodigo());
	        if(Objects.isNull(p)){
	            return 0;
	        } else {
	            return p.getCodigo();
	        }
	    }
}
