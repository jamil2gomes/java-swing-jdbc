package principal;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import conexao.ConnectionPool;
import dao.AlunoDAO;
import dao.CursoDAO;
import dao.MatriculaDAO;
import modelo.Aluno;
import modelo.Curso;
import modelo.Matricula;

public class MatAlunoForm {

	private JFrame frame;
	private JComboBox<Curso> cursocb;
	private JComboBox<Aluno> alunocb;
	private JTable tabela;
	private DefaultTableModel modelo;
	private JLabel lblDisciplina;
	private JToolBar toolBar;
	private JButton btnNovo;
	private AbstractButton btnAbrir;
	private JButton btnSalvar;
	private JButton btnExcluir;
	private JLabel lblAluno;

	public static void main(String[] args) {
		new MatAlunoForm();
	}

	/**
	 * Create the application.
	 */
	public MatAlunoForm() {
		criarJanela();
		acoesBotoes();
	}

	private void populaComboBoxCurso() {

		List<Curso> cursos = new ArrayList<Curso>();

		CursoDAO cursoDao = new CursoDAO(ConnectionPool.getConexao());
		cursos = cursoDao.buscaTodosCursos();

		for (Curso curso : cursos) {
			cursocb.addItem(curso);
		}
	}
	
	private void preencheTabela() {
		
		modelo.setNumRows(0);
		Aluno aluno = (Aluno) alunocb.getSelectedItem();
		
		CursoDAO dao = new CursoDAO(ConnectionPool.getConexao());
		List<Curso> cursos = dao.buscarNomeCursoPor(aluno);
		
		
		if(cursos.size() == 0) {
			JOptionPane.showMessageDialog(null, "O aluno  "+ alunocb.getSelectedItem() +" não está matriculado em nenhum curso");
		}
		for (Curso curso : cursos) {
			
			modelo.addRow(new Object[] {
					curso,
			});
		}
		
		
		
		
	}

	private void populaComboBoxAluno() {

		List<Aluno> alunos = new ArrayList<Aluno>();

		AlunoDAO alunoDao = new AlunoDAO(ConnectionPool.getConexao());
		alunos = alunoDao.buscarTodosAlunos();

		for (Aluno aluno : alunos) {
			alunocb.addItem(aluno);
		}
	}

	private void criarJanela() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(GradeForm.class.getResource("/icone/application_view_columns.png")));
		frame.setTitle("MatAluno");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setBounds(100, 100, 466, 351);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(0, 0, 450, 28);
		frame.getContentPane().add(toolBar);

		btnNovo = new JButton("Novo");
		btnNovo.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNovo.setIcon(new ImageIcon(GradeForm.class.getResource("/icone/add.png")));
		toolBar.add(btnNovo);

		btnAbrir = new JButton("Abrir");
		btnAbrir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAbrir.setIcon(new ImageIcon(GradeForm.class.getResource("/icone/book_open.png")));
		toolBar.add(btnAbrir);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalvar.setIcon(new ImageIcon(GradeForm.class.getResource("/icone/page_save.png")));
		toolBar.add(btnSalvar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExcluir.setIcon(new ImageIcon(GradeForm.class.getResource("/icone/cancel.png")));
		toolBar.add(btnExcluir);

		lblAluno = new JLabel("Aluno");
		lblAluno.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAluno.setBounds(10, 68, 66, 14);
		frame.getContentPane().add(lblAluno);

		alunocb = new JComboBox<Aluno>();
		alunocb.setBounds(10, 82, 221, 20);
		frame.getContentPane().add(alunocb);
		populaComboBoxAluno();

		lblDisciplina = new JLabel("Curso");
		lblDisciplina.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDisciplina.setBounds(10, 135, 77, 14);
		frame.getContentPane().add(lblDisciplina);

		cursocb = new JComboBox<Curso>();
		cursocb.setBounds(10, 149, 221, 20);
		frame.getContentPane().add(cursocb);
		populaComboBoxCurso();

		modelo = new DefaultTableModel();
		modelo.addColumn("Curso");
		modelo.setNumRows(0);
		

		tabela = new JTable(modelo);
		tabela.setFont(new Font("Tahoma", Font.BOLD, 12));
		tabela.setEnabled(false);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(80);
		
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(10, 192, 430, 109);
		frame.getContentPane().add(scrollPane);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void acoesBotoes() {

		btnNovo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				cursocb.setSelectedIndex(0);
				alunocb.setSelectedIndex(0);
				modelo.setNumRows(0);

			}
		});
		
		btnAbrir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				preencheTabela();
				
			}
		});

		btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				
				Curso curso = (Curso) cursocb.getSelectedItem();
				Aluno aluno = (Aluno) alunocb.getSelectedItem();
				
				
				
				Matricula mat = new Matricula(aluno, curso);
				
				MatriculaDAO dao = new MatriculaDAO(ConnectionPool.getConexao());
				
				
				
				 if(dao.buscaMatriculaDado(curso, aluno) == null && JOptionPane.showConfirmDialog(null, "Não foi encontrado relacionamento entre curso "+ cursocb.getSelectedItem()+" e aluno " +alunocb.getSelectedItem() + " no banco.\nDeseja cadastrar nova matrícula?", "Salvar",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					 
					 dao.salvar(mat);
				     JOptionPane.showMessageDialog(null, "Grade Salva com Sucesso");
				     preencheTabela();
				     
				 }
				
				
				
				

			}
		});

		btnExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Curso curso = (Curso) cursocb.getSelectedItem();
				Aluno aluno = (Aluno) alunocb.getSelectedItem();
				
				MatriculaDAO dao = new MatriculaDAO(ConnectionPool.getConexao());
				 if(dao.buscaMatriculaDado(curso, aluno) != null && JOptionPane.showConfirmDialog(null, "Deseja excluir matrícula de curso "+ cursocb.getSelectedItem()+" e aluno " +alunocb.getSelectedItem() + "?", "Excluir",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					 
					 Matricula mat = new Matricula(aluno, curso);
					 dao.deletar(mat);
					 
				 }else {
					 JOptionPane.showMessageDialog(null, "Não pode excluir! Matrícula de aluno "+alunocb.getSelectedItem()+" e curso "+cursocb.getSelectedItem()+" não existe no banco de dados.");
				 }
				
					

			}
		});
	}

}
