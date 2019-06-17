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

import modelo.Aluno;

/**
 *
 * @author jamil
 */
public class AlunoDAO {

	private Connection conexao;

    public AlunoDAO(Connection conexao) {
        this.conexao = conexao;
    }

   
    public Aluno salvar(Aluno aluno) {
        String sql = "insert into aluno(codigo, nome, rg, cpf, telefone, endereco) values(?,?,?,?,?,?)";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setInt(1, aluno.getCodigo());
            statement.setString(2, aluno.getNome());
            statement.setString(3, aluno.getRg());
            statement.setString(4, aluno.getCpf());
            statement.setString(5, aluno.getTelefone());
            statement.setString(6, aluno.getEndereco());

            statement.execute();
 
            
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return aluno;
    }

    
    
    public List<Aluno> buscarTodosAlunos() {
        String sql = "select * from aluno";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {

                final List<Aluno> alunos = new ArrayList<>();

                while (resultSet.next()) {
                    Aluno aluno = monta(resultSet);
                    alunos.add(aluno);
                }
                return alunos;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    
    
//    public Aluno buscaAlunoPor(Curso curso) {
//    	  String sql = "select * from aluno where idCurso = ?";
//
//          try (PreparedStatement statement = conexao.prepareStatement(sql)) {
//              statement.setInt(1, curso.getId());
//              try (ResultSet resultSet = statement.executeQuery()) {
//                  if (resultSet.next())
//                      return monta(resultSet);
//                  return null;
//              }
//          } catch (SQLException e) {
//              throw new RuntimeException(e.getMessage());
//          }
//    }
//    
//    
//    public List<Aluno> buscaAlunosPor(Curso curso) {
//        String sql = "select * from aluno where idCurso = ?";
//
//        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
//            statement.setInt(1, curso.getCodigo());
//            try (ResultSet resultSet = statement.executeQuery()) {
//
//                final List<Aluno> alunos = new ArrayList<>();
//
//                while (resultSet.next()) {
//                    Aluno aluno = monta(resultSet);
//                    alunos.add(aluno);
//                }
//                return alunos;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }

  
    
    private Aluno monta(ResultSet resultSet) {
        try {
        	Integer codigo = resultSet.getInt("codigo");
            String nome = resultSet.getString("nome");
            String endereco = resultSet.getString("endereco");
            String rg = resultSet.getString("rg");
            String cpf = resultSet.getString("cpf");
            String telefone = resultSet.getString("telefone");
            
    
            Aluno aluno = new Aluno(codigo,nome,cpf,endereco,rg,telefone);
           
            return aluno;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    
  
    
    public Aluno buscaPorNome(String nome) {
        String sql = "select * from aluno where nome = ?";

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

    
    
    public Aluno buscaPorCodigo(Integer cod) {
        String sql = "select * from aluno where codigo ="+cod;

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
           
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                    return monta(resultSet);
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    

    public Aluno atualizar(Aluno aluno) {
        String sql = "update aluno set nome = ?, rg = ?, cpf = ?, telefone = ?, endereco = ? where codigo = ?";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
           
            statement.setString(1, aluno.getNome());
            statement.setString(2, aluno.getRg());
            statement.setString(3, aluno.getCpf());
            statement.setString(4, aluno.getTelefone());
            statement.setString(5, aluno.getEndereco());
            statement.setInt(6, aluno.getCodigo());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return aluno;
    }

    
    
  
    public Aluno deletar(Aluno aluno) {
        String sql = "delete from aluno where codigo = ?";

        try(PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setInt(1,aluno.getCodigo());
            statement.execute();
            return aluno;
        }catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
    

    
    public int existe(Aluno aluno) {
        Aluno a = buscaPorCodigo(aluno.getCodigo());
        if(Objects.isNull(a)){
            return 0;
        } else {
            return a.getCodigo();
        }
    }


	
	

}
