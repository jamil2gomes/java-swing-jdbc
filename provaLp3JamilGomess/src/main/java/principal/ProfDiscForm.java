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
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import conexao.ConnectionPool;
import dao.DisciplinaDAO;
import dao.ProfessorDAO;
import modelo.Disciplina;
import modelo.Professor;

public class ProfDiscForm {

	private JFrame frmProfDisc;
	private JToolBar toolBar;
	private JButton btnNovo;
	private JButton btnAbrir;
	private JButton btnSalvar;
	private JButton btnExcluir;
	private JLabel lblProf;
	private JComboBox<Professor> profcb;
	private JComboBox<Disciplina> disccb;
	private JTable tabela;
	private DefaultTableModel modelo;
	private JLabel lblDisciplina;
	private JScrollPane scrollPane;

	public static void main(String[] args) {
		new ProfDiscForm();
	}

	/**
	 * Create the application.
	 */
	public ProfDiscForm() {
		initialize();
	}

	private void populaComboBoxDisciplina() {

		List<Disciplina> discs = new ArrayList<Disciplina>();

		DisciplinaDAO discDao = new DisciplinaDAO(ConnectionPool.getConexao());

		discs = discDao.buscarTodasDisciplinas();

		for (Disciplina disciplina : discs) {
			disccb.addItem(disciplina);
		}

	}

	private void populaComboBoxProfessor() {

		List<Professor> profs = new ArrayList<Professor>();

		ProfessorDAO profDao = new ProfessorDAO(ConnectionPool.getConexao());
		profs = profDao.buscarTodosOsProfessores();

		for (Professor p : profs) {
			profcb.addItem(p);
		}
	}

	private void populaTabela() {
		modelo.setNumRows(0);

		Professor p = (Professor) profcb.getSelectedItem();

		DisciplinaDAO dao = new DisciplinaDAO(ConnectionPool.getConexao());

		List<Disciplina> discs = dao.buscaDisciplinasPor(p);

		for (Disciplina disciplina : discs) {
			modelo.addRow(new Object[] { disciplina });
		}

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProfDisc = new JFrame();
		frmProfDisc.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(GradeForm.class.getResource("/icone/application_view_columns.png")));
		frmProfDisc.setTitle("Prof.Disc");
		frmProfDisc.setResizable(false);
		frmProfDisc.setVisible(true);
		frmProfDisc.setBounds(100, 100, 466, 351);
		frmProfDisc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmProfDisc.setLocationRelativeTo(null);
		frmProfDisc.getContentPane().setLayout(null);

		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(0, 0, 450, 28);
		frmProfDisc.getContentPane().add(toolBar);

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

		lblProf = new JLabel("Professor");
		lblProf.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProf.setBounds(10, 68, 66, 14);
		frmProfDisc.getContentPane().add(lblProf);

		profcb = new JComboBox<Professor>();
		profcb.setBounds(10, 82, 221, 20);
		frmProfDisc.getContentPane().add(profcb);
		populaComboBoxProfessor();

		lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDisciplina.setBounds(10, 135, 77, 14);
		frmProfDisc.getContentPane().add(lblDisciplina);

		disccb = new JComboBox<Disciplina>();
		disccb.setBounds(10, 149, 221, 20);
		frmProfDisc.getContentPane().add(disccb);
		populaComboBoxDisciplina();

		modelo = new DefaultTableModel();
		modelo.addColumn("Disciplina");
		modelo.setNumRows(0);

		tabela = new JTable(modelo);
		tabela.setEnabled(false);
		tabela.setFont(new Font("Tahoma", Font.BOLD, 12));
		tabela.getColumnModel().getColumn(0).setPreferredWidth(100);

		scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(10, 192, 430, 109);
		frmProfDisc.getContentPane().add(scrollPane);

		btnNovo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				disccb.setSelectedIndex(0);
				profcb.setSelectedIndex(0);

			}
		});

		btnAbrir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				populaTabela();

			}
		});

		btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Professor p = (Professor) profcb.getSelectedItem();
				Disciplina d = (Disciplina) disccb.getSelectedItem();

				DisciplinaDAO dao = new DisciplinaDAO(ConnectionPool.getConexao());

				if (dao.buscaDisciplinaDado(d, p) == null && JOptionPane.showConfirmDialog(null,
						"Não foi encontrado relacionamento entre disciplina " + disccb.getSelectedItem()
								+ " e professfor " + profcb.getSelectedItem() + "no banco.\nDeseja cadastrar?",
						"Salvar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

					dao.atualiza(d, p);
					populaTabela();
				}

			}
		});

		btnExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Professor p = (Professor) profcb.getSelectedItem();
				Disciplina d = (Disciplina) disccb.getSelectedItem();

				DisciplinaDAO dao = new DisciplinaDAO(ConnectionPool.getConexao());
				if (dao.buscaDisciplinaDado(d, p) != null && JOptionPane.showConfirmDialog(null,
						"Deseja deletar relação?", "Deletar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

					dao.atualiza(d, null);
					populaTabela();
				}
			}
		});

	}

}
