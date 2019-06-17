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
import modelo.Curso;

/**
 *
 * @author jamil
 */
public class CursoDAO {

	private Connection conexao;

	public CursoDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public Curso salvar(Curso curso) {

		String sql = "insert into curso(codigo, nome) values(?,?)";

		try (PreparedStatement statement = conexao.prepareStatement(sql)) {

			statement.setInt(1, curso.getCodigo());
			statement.setString(2, curso.getNome());

			statement.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

		return curso;
	}

	public List<Curso> buscaTodosCursos() {
		String sql = "select * from curso";

		try (PreparedStatement statement = conexao.prepareStatement(sql)) {
			try (ResultSet resultSet = statement.executeQuery()) {

				final List<Curso> cidades = new ArrayList<>();

				while (resultSet.next()) {
					Curso curso = monta(resultSet);
					cidades.add(curso);
				}
				return cidades;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private Curso monta(ResultSet resultSet) {
		try {
			String nome = resultSet.getString("nome");
			Integer codigo = resultSet.getInt("codigo");

			Curso curso = new Curso(codigo, nome);

			return curso;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Curso buscaPorNome(String nome) {
		String sql = "select * from curso where nome = ?";

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

	public Curso buscaPorCodigo(Integer codigo) {
		String sql = "select * from curso where codigo = ?";

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

	public Curso atualizar(Curso curso) {
		String sql = "update curso set nome = ? where codigo = ?";

		try (PreparedStatement statement = conexao.prepareStatement(sql)) {

			statement.setString(1, curso.getNome());
			statement.setInt(2, curso.getCodigo());

			statement.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

		return curso;
	}

	public Curso deletar(Curso curso) {
		String sql = "delete from curso where codigo = ?";

		try (PreparedStatement statement = conexao.prepareStatement(sql)) {
			statement.setInt(1, curso.getCodigo());

			statement.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return curso;
	}

	public int existe(Curso curso) {
		Curso c = buscaPorCodigo(curso.getCodigo());
		if (Objects.isNull(c)) {
			return 0;
		} else {
			return c.getCodigo();
		}
	}

	public List<Curso> buscarNomeCursoPor(Aluno nomeAluno) {

		String sql = "select * from curso inner join matricula on curso.codigo = matricula.codCurso"
				+ " inner join aluno on aluno.codigo = matricula.codAluno where aluno.nome = ?";

		try (PreparedStatement statement = conexao.prepareStatement(sql)) {

			statement.setString(1, nomeAluno.getNome());

			try (ResultSet resultSet = statement.executeQuery()) {
				final List<Curso> cursos = new ArrayList<>();

				while (resultSet.next()) {
					Curso curso = monta(resultSet);
					cursos.add(curso);
				}

				return cursos;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

}
