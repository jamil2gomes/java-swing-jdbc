package principal;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import conexao.ConnectionPool;
import dao.DisciplinaDAO;
import modelo.Disciplina;

public class DisciplinaForm {

	private JFrame frmDisciplina;
	private JTextField codTxt, nomeTxt;
	private JButton btnSalvar, btnNovo, btnAbrir, btnExcluir;
	private JComboBox<String> chTxt;
	private JTextArea ementaTxt;
	private JToolBar toolBar;
	private JScrollPane sp;
	private JLabel lblCod, lblEmenta, lblDisciplina, lblCargaHorria;
	private String horarios[] = { "30", "45", "60", "90" };

	/**
	 * Create the application.
	 */

	public static void main(String[] args) {
		new DisciplinaForm();
	}

	public DisciplinaForm() {
		criaJanela();
		initialize();
		btnExcluir.setEnabled(false);
	}

	private Disciplina buscaDadosNoFormulario() {

		Disciplina disc = new Disciplina(Integer.parseInt(codTxt.getText()), nomeTxt.getText(), ementaTxt.getText(),(String) chTxt.getSelectedItem());
		return disc;
	}

	private void preencheCampos(Disciplina disc) {

		codTxt.setEditable(false);
		nomeTxt.setText(disc.getNome());
		ementaTxt.setText(disc.getEmenta());
		chTxt.setSelectedItem(disc.getCh());
	}

	private void aoClicarNoBotaoNovo() {

		codTxt.setText("");
		nomeTxt.setText("");
		ementaTxt.setText("");
		chTxt.setSelectedIndex(0);
		btnAbrir.setEnabled(true);
		btnExcluir.setEnabled(false);
		codTxt.setEditable(true);

	}

	private void aoCliclarNoBotaoAbrir() {

		btnExcluir.setEnabled(true);
		btnAbrir.setEnabled(false);

	}

	private void criaJanela() {
		frmDisciplina = new JFrame();
		frmDisciplina.setFont(new Font("Dialog", Font.BOLD, 12));
		frmDisciplina.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(DisciplinaForm.class.getResource("/icone/page_white_visualstudio.png")));
		frmDisciplina.setTitle("Disciplina");
		frmDisciplina.setBounds(100, 100, 466, 351);
		frmDisciplina.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDisciplina.setVisible(true);
		frmDisciplina.setResizable(false);
		frmDisciplina.getContentPane().setLayout(null);
		frmDisciplina.setLocationRelativeTo(null);

		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setEnabled(false);
		toolBar.setBounds(0, 0, 450, 28);
		frmDisciplina.getContentPane().add(toolBar);

		btnNovo = new JButton("Novo");
		btnNovo.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNovo.setIcon(new ImageIcon(DisciplinaForm.class.getResource("/icone/add.png")));
		toolBar.add(btnNovo);
		toolBar.addSeparator();

		btnAbrir = new JButton("Abrir");
		btnAbrir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAbrir.setIcon(new ImageIcon(DisciplinaForm.class.getResource("/icone/book_open.png")));
		toolBar.add(btnAbrir);
		toolBar.addSeparator();

		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalvar.setIcon(new ImageIcon(DisciplinaForm.class.getResource("/icone/page_save.png")));
		toolBar.add(btnSalvar);
		toolBar.addSeparator();

		btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExcluir.setIcon(new ImageIcon(DisciplinaForm.class.getResource("/icone/cancel.png")));
		toolBar.add(btnExcluir);
		toolBar.addSeparator();

		lblCod = new JLabel("C\u00F3digo");
		lblCod.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCod.setBounds(10, 62, 46, 14);
		frmDisciplina.getContentPane().add(lblCod);

		lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDisciplina.setBounds(10, 123, 64, 14);
		frmDisciplina.getContentPane().add(lblDisciplina);

		codTxt = new JTextField();
		codTxt.setBounds(10, 79, 87, 20);
		frmDisciplina.getContentPane().add(codTxt);
		codTxt.setColumns(10);

		nomeTxt = new JTextField();
		nomeTxt.setBounds(10, 140, 219, 20);
		frmDisciplina.getContentPane().add(nomeTxt);
		nomeTxt.setColumns(10);

		lblEmenta = new JLabel("Ementa");
		lblEmenta.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmenta.setBounds(10, 181, 64, 14);
		frmDisciplina.getContentPane().add(lblEmenta);

		ementaTxt = new JTextArea();
		sp = new JScrollPane(ementaTxt);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		ementaTxt.setLineWrap(true);

		sp.setBounds(10, 206, 430, 95);
		frmDisciplina.getContentPane().add(sp);

		lblCargaHorria = new JLabel("CH");
		lblCargaHorria.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCargaHorria.setBounds(142, 63, 87, 14);
		frmDisciplina.getContentPane().add(lblCargaHorria);

		chTxt = new JComboBox<String>(horarios);
		chTxt.setBounds(142, 79, 87, 20);
		frmDisciplina.getContentPane().add(chTxt);
	}

	private void initialize() {

		btnNovo.addActionListener((ActionEvent ae) -> {
			aoClicarNoBotaoNovo();
		});

		btnAbrir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				

				DisciplinaDAO dao = new DisciplinaDAO(ConnectionPool.getConexao());

				Disciplina d;

				if (!codTxt.getText().isEmpty()) {
					
					d = dao.buscaPorCodigo(Integer.parseInt(codTxt.getText()));
					aoCliclarNoBotaoAbrir();
				} else {
					
					JOptionPane.showMessageDialog(null,"Campo código vazio.\n Impossível fazer pesquisa no banco de dados.");
					return;
				}

				preencheCampos(d);

			}
		});

		btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				DisciplinaDAO dao = new DisciplinaDAO(ConnectionPool.getConexao());
				Disciplina d = buscaDadosNoFormulario();
				int id = dao.existe(d);

				if (id <= 0 && JOptionPane.showConfirmDialog(null, "Não foi encontrado disciplina com código "+ codTxt.getText()+" no banco. Deseja salvar nova disciplina?", "Salvar",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					
					dao.salvar(d);
					JOptionPane.showMessageDialog(null, "Disciplina salva com sucesso!");
					aoClicarNoBotaoNovo();
					
				} else if (JOptionPane.showConfirmDialog(null, "Deseja atualizar disciplina?", "Atualizar",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			
					dao.atualizar(d);
					JOptionPane.showMessageDialog(null, "Disciplina atualizada com sucesso!");
					aoClicarNoBotaoNovo();
				}

			}
		});

		btnExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				DisciplinaDAO dao = new DisciplinaDAO(ConnectionPool.getConexao());
				Disciplina d = buscaDadosNoFormulario();

				if (JOptionPane.showConfirmDialog(null, "Deseja excluir curso?", "Excluir",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

					dao.deletar(d);
					aoClicarNoBotaoNovo();
				}

			}
		});

	}
}
