package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.ConnectionPool;
import modelo.Aluno;
import modelo.Curso;
import modelo.Matricula;

public class MatriculaDAO {

		private Connection conexao;

	    public MatriculaDAO(Connection conexao) {
	        this.conexao = conexao;
	    }
	    
	    
    public Matricula salvar(Matricula matricula) {
	    	
	        String sql = "insert into matricula(codAluno, codCurso) values(?,?)";

	        try (PreparedStatement statement = conexao.prepareStatement(sql)) {

	        
	            statement.setInt(1, matricula.getAluno().getCodigo());
	            statement.setInt(2, matricula.getCurso().getCodigo());

	            statement.execute();
	        } catch (SQLException e) {
	            throw new RuntimeException(e.getMessage());
	        }

	        return matricula;
	    }
    
    
    public Matricula buscaMatriculaDado(Curso curso, Aluno aluno) {
    	String sql = "select * from matricula where codCurso = ? and codAluno = ?";
    	 try (PreparedStatement statement = conexao.prepareStatement(sql)) {
    		 
	            statement.setInt(1, curso.getCodigo());
	            statement.setInt(2, aluno.getCodigo());
	            
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next())
	                    return monta(resultSet);
	                return null;
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e.getMessage());
	        }
    }
    public List<Matricula> buscaTodasGrades() {
    	
        String sql = "select * from matricula";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {

                final List<Matricula> matriculas = new ArrayList<>();

                while (resultSet.next()) {
                    Matricula matricula = monta(resultSet);
                    matriculas.add(matricula);
                }
                return matriculas;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    
    public Matricula monta(ResultSet resultSet) {
    	
        try {
          
            int idCurso = resultSet.getInt("codCurso");
            int idAluno = resultSet.getInt("codAluno");
            Aluno aluno = new AlunoDAO(ConnectionPool.getConexao()).buscaPorCodigo(idAluno);
            Curso curso = new CursoDAO(ConnectionPool.getConexao()).buscaPorCodigo(idCurso);

            Matricula matricula = new Matricula(aluno,curso);
            return matricula;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

   

   
    public Matricula deletar(Matricula mat) {
    	
        String sql = "delete from grade where codAluno = ? and codCurso = ?";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
        	
            statement.setInt(1, mat.getAluno().getCodigo());
            statement.setInt(2, mat.getCurso().getCodigo());

            statement.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return mat;
    }




}
