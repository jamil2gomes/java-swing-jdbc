package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.ConnectionPool;
import modelo.Curso;
import modelo.Disciplina;
import modelo.Grade;

public class GradeDAO {

	   private Connection conexao;

	    public GradeDAO(Connection conexao) {
	        this.conexao = conexao;
	    }

	   
	    public Grade salvar(Grade grade) {
	    	
	        String sql = "insert into grade(ano, codDisc, codCurso) values(?,?,?)";

	        try (PreparedStatement statement = conexao.prepareStatement(sql)) {

	            statement.setString(1, grade.getAno());
	            statement.setInt(2, grade.getDisciplina().getCodigo());
	            statement.setInt(3, grade.getCurso().getCodigo());

	            statement.execute();
	        } catch (SQLException e) {
	            throw new RuntimeException(e.getMessage());
	        }

	        return grade;
	    }

	    
	    public Grade buscaGradeDado(Curso curso, Disciplina disciplina) {
	    	String sql = "select * from grade where codCurso = ? and codDisc = ?";
	    	 try (PreparedStatement statement = conexao.prepareStatement(sql)) {
		            statement.setInt(1, curso.getCodigo());
		            statement.setInt(2, disciplina.getCodigo());
		            try (ResultSet resultSet = statement.executeQuery()) {
		                if (resultSet.next())
		                    return monta(resultSet);
		                return null;
		            }
		        } catch (SQLException e) {
		            throw new RuntimeException(e.getMessage());
		        }
	    }
	    public List<Grade> buscaTodasGrades() {
	    	
	        String sql = "select * from grade";

	        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
	            try (ResultSet resultSet = statement.executeQuery(sql)) {

	                final List<Grade> grades = new ArrayList<>();

	                while (resultSet.next()) {
	                    Grade grade = monta(resultSet);
	                    grades.add(grade);
	                }
	                return grades;
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e.getMessage());
	        }
	    }

	
	    
	    public Grade monta(ResultSet resultSet) {
	    	
	        try {
	            String ano = resultSet.getString("ano");
	            int idCurso = resultSet.getInt("codCurso");
	            int idDisciplina = resultSet.getInt("codDisc");
	            Disciplina disciplina = new DisciplinaDAO(ConnectionPool.getConexao()).buscaPorCodigo(idDisciplina);
	            Curso curso = new CursoDAO(ConnectionPool.getConexao()).buscaPorCodigo(idCurso);

	            Grade grade = new Grade(ano, curso, disciplina);
	            return grade;
	        } catch (SQLException e) {
	            throw new RuntimeException(e.getMessage());
	        }
	    }

	   

	   
	    public Grade deletar(Grade grade) {
	    	
	        String sql = "delete from grade where codDisc = ? and codCurso = ?";

	        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
	            statement.setInt(1, grade.getDisciplina().getCodigo());
	            statement.setInt(2, grade.getCurso().getCodigo());

	            statement.execute();
	        } catch (SQLException e) {
	            throw new RuntimeException(e.getMessage());
	        }
	        return grade;
	    }


	

	    
	    
}
