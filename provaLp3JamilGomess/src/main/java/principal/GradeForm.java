package principal;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import conexao.ConnectionPool;
import dao.CursoDAO;
import dao.DisciplinaDAO;
import dao.GradeDAO;
import modelo.Curso;
import modelo.Disciplina;
import modelo.Grade;

public class GradeForm {

	private JFrame frmGrade;
	private JTextField anoTxt;
	private DefaultTableModel modelo;
	private JButton btnNovo, btnAbrir, btnSalvar, btnExcluir;
	private JComboBox<Disciplina> disciplinaCb;
	private JComboBox<Curso> cursoCb;
	private JLabel lblDisciplina, lblAno, lblCurso;
	private JToolBar toolBar;
	private JTable tabela;
	private JScrollPane scrollPane;

	public static void main(String[] args) {
		new GradeForm();
	}

	/**
	 * Create the application.
	 */
	public GradeForm() {
		criarJanela();
		acoesBotoes();
	}
	
	private void aoClicarNovo() {
		cursoCb.setSelectedIndex(0);
		disciplinaCb.setSelectedIndex(0);
		anoTxt.setText("");
	}
	
	
	private void preencheTabela() {
		modelo.setNumRows(0);
		
		Curso curso = (Curso) cursoCb.getSelectedItem();
		
		DisciplinaDAO dao = new DisciplinaDAO(ConnectionPool.getConexao());
		
		List<Disciplina>disciplinas = dao.buscaDisciplinasPor(curso);
		

		System.out.println(disciplinas);
		
		if(disciplinas.size() == 0) {
			JOptionPane.showMessageDialog(null, "Não existe disciplinas cadastradas no curso selecionado.");
		}
		
		for (Disciplina disc : disciplinas) {

			modelo.addRow(new Object[] { disc.getNome(), disc.getCh() });

		}
	}
	

	private void populaComboBoxCurso() {

		List<Curso> cursos = new ArrayList<Curso>();

		CursoDAO cursoDao = new CursoDAO(ConnectionPool.getConexao());
		cursos = cursoDao.buscaTodosCursos();

		for (Curso curso : cursos) {
			cursoCb.addItem(curso);
		}
	}

	private void populaComboBoxDisciplina() {

		List<Disciplina> discs = new ArrayList<Disciplina>();

		DisciplinaDAO discDao = new DisciplinaDAO(ConnectionPool.getConexao());

		discs = discDao.buscarTodasDisciplinas();

		for (Disciplina disciplina : discs) {
			disciplinaCb.addItem(disciplina);
		}

	}

	private void criarJanela() {
		frmGrade = new JFrame();
		frmGrade.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(GradeForm.class.getResource("/icone/application_view_columns.png")));
		frmGrade.setTitle("Grade");
		frmGrade.setResizable(false);
		frmGrade.setVisible(true);
		frmGrade.setBounds(100, 100, 466, 351);
		frmGrade.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGrade.setLocationRelativeTo(null);
		frmGrade.getContentPane().setLayout(null);

		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(0, 0, 450, 28);
		frmGrade.getContentPane().add(toolBar);

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

		lblAno = new JLabel("Ano");
		lblAno.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAno.setBounds(10, 62, 46, 14);
		frmGrade.getContentPane().add(lblAno);

		anoTxt = new JTextField();
		anoTxt.setBounds(10, 79, 79, 20);
		frmGrade.getContentPane().add(anoTxt);
		anoTxt.setColumns(10);

		lblCurso = new JLabel("Curso");
		lblCurso.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurso.setBounds(140, 62, 46, 14);
		frmGrade.getContentPane().add(lblCurso);

		
		cursoCb = new JComboBox<Curso>();
		populaComboBoxCurso();
	

		cursoCb.setBounds(140, 79, 208, 20);
		frmGrade.getContentPane().add(cursoCb);

		lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDisciplina.setBounds(10, 125, 86, 14);
		frmGrade.getContentPane().add(lblDisciplina);

		
		disciplinaCb = new JComboBox<Disciplina>();
		populaComboBoxDisciplina();
		
		disciplinaCb.setBounds(10, 141, 250, 20);
		frmGrade.getContentPane().add(disciplinaCb);

		modelo = new DefaultTableModel();
		modelo.addColumn("Disciplina");
		modelo.addColumn("CH");
		modelo.setNumRows(0);

		tabela = new JTable(modelo);
		tabela.setFont(new Font("Tahoma", Font.BOLD, 12));
		tabela.getColumnModel().getColumn(0).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(10);


		

		scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(10, 192, 430, 109);
		frmGrade.getContentPane().add(scrollPane);
	}

	private void acoesBotoes() {

		
		btnNovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				aoClicarNovo();
				
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
				
				String ano             = anoTxt.getText();
				Curso curso            = (Curso) cursoCb.getSelectedItem();
				Disciplina disciplina  =  (Disciplina) disciplinaCb.getSelectedItem();
				
				System.out.println(ano +" - "+ curso +" - "+ disciplina);
				
				Grade g = new Grade(ano, curso, disciplina);
			
				 GradeDAO dao = new GradeDAO(ConnectionPool.getConexao());
				 
				 if(dao.buscaGradeDado(curso, disciplina) == null && JOptionPane.showConfirmDialog(null, "Não foi encontrado relacionamento entre curso "+ cursoCb.getSelectedItem()+" e disciplina " +disciplinaCb.getSelectedItem() + "no banco.\nDeseja cadastrar nova grade?", "Salvar",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					 
					 dao.salvar(g);
				     JOptionPane.showMessageDialog(null, "Grade Salva com Sucesso");
				     preencheTabela();
				 }else {
					 JOptionPane.showMessageDialog(null, "Não pode salvar essa grade. Ela já existe no banco de dados.");
				 }
			       
			       
			}
		});
		
		btnExcluir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String ano             = anoTxt.getText();
				Curso curso            = (Curso) cursoCb.getSelectedItem();
				Disciplina disciplina  =  (Disciplina) disciplinaCb.getSelectedItem();
				GradeDAO dao = new GradeDAO(ConnectionPool.getConexao());
				
				
				if(dao.buscaGradeDado(curso, disciplina) != null &&JOptionPane.showConfirmDialog(null, "Deseja deletar grade?", "Deletar",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					
					 Grade g = new Grade(ano, curso, disciplina);
					 dao.deletar(g);
					 JOptionPane.showMessageDialog(null, "grade deletada com sucesso!");
					 preencheTabela();
				}else {
					 JOptionPane.showMessageDialog(null, "grade selecionada não existe no banco de dados, portanto, não pode ser deletada");
				}
				
				
			}
		});
	}
}
