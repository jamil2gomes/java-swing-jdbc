package principal;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import conexao.ConnectionPool;
import dao.ProfessorDAO;
import modelo.Professor;

public class ProfessorForm {

	private JFrame frmProfessor;
	private JTextField codTxt, nomeTxt;
	private JButton btnNovo, btnAbrir, btnSalvar, btnExcluir;
	private JToolBar toolBar;
	private JLabel lblCdigo, lblNome;

	public static void main(String[] args) {
		new ProfessorForm();
	}

	/**
	 * Create the application.
	 */
	public ProfessorForm() {
		criarJanela();
		acoesBotoes();
		btnExcluir.setEnabled(false);
	}

	private void preencheCampos(Professor prof) {

		codTxt.setText(String.valueOf(prof.getCodigo()));
		nomeTxt.setText(prof.getNome());
	}

	private Professor buscaDadosNoFormulario() {

		Professor prof = new Professor(Integer.parseInt(codTxt.getText()), nomeTxt.getText());

		return prof;
	}

	private void aoClicarNoBotaoNovo() {

		codTxt.setText("");
		nomeTxt.setText("");
		btnAbrir.setEnabled(true);
		btnExcluir.setEnabled(false);
		codTxt.setEditable(true);

	}

	private void aoCliclarNoBotaoAbrir() {

		btnExcluir.setEnabled(true);
		btnAbrir.setEnabled(false);
		codTxt.setEditable(false);

	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void criarJanela() {
		frmProfessor = new JFrame();
		frmProfessor.setFont(new Font("Dialog", Font.BOLD, 12));
		frmProfessor.setIconImage(
				Toolkit.getDefaultToolkit().getImage(ProfessorForm.class.getResource("/icone/user_suit.png")));
		frmProfessor.setTitle("Professor");
		frmProfessor.setBounds(100, 100, 466, 351);
		frmProfessor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmProfessor.setVisible(true);
		frmProfessor.setResizable(false);
		frmProfessor.setLocationRelativeTo(null);
		frmProfessor.getContentPane().setLayout(null);

		lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCdigo.setBounds(10, 55, 46, 14);
		frmProfessor.getContentPane().add(lblCdigo);

		codTxt = new JTextField();
		codTxt.setBounds(10, 73, 248, 20);
		frmProfessor.getContentPane().add(codTxt);
		codTxt.setColumns(10);

		lblNome = new JLabel("Professor");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome.setBounds(10, 104, 71, 14);
		frmProfessor.getContentPane().add(lblNome);

		nomeTxt = new JTextField();
		nomeTxt.setBounds(10, 124, 248, 20);
		frmProfessor.getContentPane().add(nomeTxt);
		nomeTxt.setColumns(10);

		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setEnabled(false);
		toolBar.setBounds(0, 0, 450, 28);
		frmProfessor.getContentPane().add(toolBar);

		btnNovo = new JButton("Novo");
		btnNovo.setIcon(new ImageIcon(ProfessorForm.class.getResource("/icone/add.png")));
		btnNovo.setFont(new Font("Tahoma", Font.BOLD, 12));
		toolBar.add(btnNovo);
		toolBar.addSeparator();

		btnAbrir = new JButton("Abrir");
		btnAbrir.setIcon(new ImageIcon(ProfessorForm.class.getResource("/icone/book_open.png")));
		btnAbrir.setFont(new Font("Tahoma", Font.BOLD, 12));
		toolBar.add(btnAbrir);
		toolBar.addSeparator();

		btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(ProfessorForm.class.getResource("/icone/page_save.png")));
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 12));
		toolBar.add(btnSalvar);
		toolBar.addSeparator();

		btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(ProfessorForm.class.getResource("/icone/cancel.png")));
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 12));
		toolBar.add(btnExcluir);
		toolBar.addSeparator();
	}

	private void acoesBotoes() {

		btnNovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				aoClicarNoBotaoNovo();

			}
		});

		btnAbrir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Professor p;
				ProfessorDAO dao = new ProfessorDAO(ConnectionPool.getConexao());

				if (!codTxt.getText().isEmpty()) {
					
					p = dao.buscaPorCodigo(Integer.parseInt(codTxt.getText()));
					aoCliclarNoBotaoAbrir();

				}else {
					JOptionPane.showMessageDialog(null,"Campo código vazio.\n Impossível fazer pesquisa no banco de dados.");
					return;
				}

				preencheCampos(p);
			}

		});

		btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProfessorDAO dao = new ProfessorDAO(ConnectionPool.getConexao());
				Professor p = buscaDadosNoFormulario();

				int codigo = dao.existe(p);

				if (codigo <= 0 && JOptionPane.showConfirmDialog(null, "Não foi encontrado professor com código "+ codTxt.getText()+" no banco. Deseja salvar novo professor?", "Salvar",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					
					dao.salvar(p);
					JOptionPane.showMessageDialog(null, "Professor salvo com sucesso.");
					
				} else if (JOptionPane.showConfirmDialog(null, "Deseja atualizar professor?", "Atualizar",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					
					dao.atualizar(p);
					JOptionPane.showMessageDialog(null, "Professor atualizado com sucesso.");
				}

			}
		});

		btnExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProfessorDAO dao = new ProfessorDAO(ConnectionPool.getConexao());
				Professor p = buscaDadosNoFormulario();

				if (JOptionPane.showConfirmDialog(null, "Deseja excluir professor?", "Excluir",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					dao.deletar(p);
					aoClicarNoBotaoNovo();
				}

			}
		});

	}

}
