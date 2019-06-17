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

import modelo.Curso;
import modelo.Disciplina;
import modelo.Professor;

/**
 *
 * @author jamil
 */
public class DisciplinaDAO {

	private Connection conexao;

	public DisciplinaDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public Disciplina salvar(Disciplina disciplina) {

		String sql = "insert into disciplina(codigo, nome, ementa, ch) values(?,?,?, ?)";

		try (PreparedStatement statement = conexao.prepareStatement(sql)) {

			statement.setInt(1, disciplina.getCodigo());
			statement.setString(2, disciplina.getNome());
			statement.setString(3, disciplina.getEmenta());
			statement.setString(4, disciplina.getCh());
			statement.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

		return disciplina;
	}

	public List<Disciplina> buscarTodasDisciplinas() {

		String sql = "select * from disciplina";

		try (PreparedStatement statement = conexao.prepareStatement(sql)) {
			try (ResultSet resultSet = statement.executeQuery()) {

				final List<Disciplina> cidades = new ArrayList<>();

				while (resultSet.next()) {
					Disciplina disciplina = monta(resultSet);
					cidades.add(disciplina);
				}
				return cidades;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public List<Disciplina> buscaDisciplinasPor(Curso curso) {

		String sql = "select * from disciplina inner join grade on disciplina.codigo = grade.codDisc "
				+ "inner join Curso on curso.codigo = grade.codCurso where curso.nome = ?";

		try (PreparedStatement statement = conexao.prepareStatement(sql)) {

			statement.setString(1, curso.getNome());

			try (ResultSet resultSet = statement.executeQuery()) {

				final List<Disciplina> discs = new ArrayList<>();

				while (resultSet.next()) {
					Disciplina disciplina = monta(resultSet);
					discs.add(disciplina);
				}
				return discs;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	private Disciplina monta(ResultSet resultSet) {

		try {
			Integer codigo = resultSet.getInt("codigo");
			String nome = resultSet.getString("nome");
			String ementa = resultSet.getString("ementa");
			String ch = resultSet.getString("ch");

			Disciplina disciplina = new Disciplina(codigo, nome, ementa, ch);

			return disciplina;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Disciplina buscaPorNome(String nome) {

		String sql = "select * from disciplina where nome = ?";

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
	
	 public Disciplina buscaDisciplinaDado(Disciplina disc, Professor prof) {
	    	String sql = "select * from disciplina where codigo = ? and codProf = ?";
	    	 try (PreparedStatement statement = conexao.prepareStatement(sql)) {
	    		 
		            statement.setInt(1, disc.getCodigo());
		            statement.setInt(2, prof.getCodigo());
		            
		            try (ResultSet resultSet = statement.executeQuery()) {
		                if (resultSet.next())
		                    return monta(resultSet);
		                return null;
		            }
		        } catch (SQLException e) {
		            throw new RuntimeException(e.getMessage());
		        }
	    }

	public Disciplina buscaPorCodigo(Integer codigo) {

		String sql = "select * from disciplina where codigo = ?";

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

	public Disciplina atualizar(Disciplina disc) {

		String sql = "update disciplina set nome = ?, ementa = ?, ch = ? where codigo = ?";

		try (PreparedStatement statement = conexao.prepareStatement(sql)) {

			statement.setString(1, disc.getNome());
			statement.setString(2, disc.getEmenta());
			statement.setString(3, disc.getCh());
			statement.setInt(4, disc.getCodigo());

			statement.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return disc;
	}
	
	

	public Disciplina atualiza(Disciplina disc, Professor p) {

		String sql = "update disciplina set codProf = ? where nome = ?";

		try (PreparedStatement statement = conexao.prepareStatement(sql)) {

			statement.setInt(1, p.getCodigo());
			statement.setString(2, disc.getNome());
			

			statement.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return disc;
	}

	
	
	public Disciplina deletar(Disciplina disciplina) {

		String sql = "delete from disciplina where codigo = ?";

		try (PreparedStatement statement = conexao.prepareStatement(sql)) {
			statement.setInt(1, disciplina.getCodigo());

			statement.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return disciplina;
	}

	public int existe(Disciplina disciplina) {

		Disciplina d = buscaPorCodigo(disciplina.getCodigo());
		if (Objects.isNull(d)) {
			return 0;
		} else {
			return d.getCodigo();
		}
	}

	public List<Disciplina> buscaDisciplinasPor(Professor p) {
		
		String sql = "select * from disciplina where disciplina.codProf = ?";
		
		try (PreparedStatement statement = conexao.prepareStatement(sql)) {

			statement.setInt(1, p.getCodigo());

			try (ResultSet resultSet = statement.executeQuery()) {

				final List<Disciplina> discs = new ArrayList<>();

				while (resultSet.next()) {
					Disciplina disciplina = monta(resultSet);
					discs.add(disciplina);
				}
				return discs;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
