package principal;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.text.MaskFormatter;

import conexao.ConnectionPool;
import dao.AlunoDAO;
import modelo.Aluno;

public class AlunoForm {

	private JFrame frmAluno;
	private JLabel lblNome, lblCodigo, lblEndereco, lblRg, lblCpf, lblTelefone;
	private JTextField codTxt, nomeTxt, endTxt, rgTxt, cpfTxt, teltxt;
	private JButton btnAbrir, btnNovo, btnSalvar, btnExcluir;
	private JToolBar toolBar;

	public static void main(String[] args) {
		new AlunoForm();
	}

	public AlunoForm() {
		constroiJanela();
		acoesBotoes();
	}

	
	private Aluno montaAlunoComDadosDoFomulário() {
		Aluno aluno = new Aluno(Integer.parseInt(codTxt.getText()),nomeTxt.getText(),cpfTxt.getText(),endTxt.getText(),rgTxt.getText(),teltxt.getText());
		return aluno;
	}
	
	
	private void preencheCampos(Aluno aluno) {

		codTxt.setEditable(false);
		endTxt.setText(aluno.getEndereco());
		nomeTxt.setText(aluno.getNome());
		cpfTxt.setText(aluno.getCpf());
		rgTxt.setText(aluno.getRg());
		teltxt.setText(aluno.getTelefone());
	}

	private void aoClicarNoBotaoNovo() {

		codTxt.setText("");
		endTxt.setText("");
		nomeTxt.setText("");
		cpfTxt.setText("");
		rgTxt.setText("");
		teltxt.setText("");
		btnAbrir.setEnabled(true);
		btnExcluir.setEnabled(false);
		codTxt.setEditable(true);
	}

	private void aoCliclarNoBotaoAbrir() {

		btnExcluir.setEnabled(true);
		btnAbrir.setEnabled(false);
	}

	
	private void constroiJanela() {
		frmAluno = new JFrame();
		frmAluno.setFont(new Font("Dialog", Font.BOLD, 12));
		frmAluno.setIconImage(Toolkit.getDefaultToolkit().getImage(AlunoForm.class.getResource("/icone/user.png")));
		frmAluno.setTitle("Aluno");
		frmAluno.setBounds(100, 100, 466, 351);
		frmAluno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAluno.setVisible(true);
		frmAluno.setResizable(false);
		frmAluno.setLocationRelativeTo(null);
		frmAluno.getContentPane().setLayout(null);

		lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodigo.setBounds(10, 54, 46, 14);
		frmAluno.getContentPane().add(lblCodigo);

		codTxt = new JTextField();
		codTxt.setBounds(10, 70, 322, 20);
		frmAluno.getContentPane().add(codTxt);
		codTxt.setColumns(20);

		lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome.setBounds(10, 101, 46, 14);
		frmAluno.getContentPane().add(lblNome);

		nomeTxt = new JTextField();
		nomeTxt.setBounds(10, 123, 322, 20);
		frmAluno.getContentPane().add(nomeTxt);
		nomeTxt.setColumns(20);

		lblEndereco = new JLabel("Endere\u00E7o");
		lblEndereco.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEndereco.setBounds(10, 154, 58, 14);
		frmAluno.getContentPane().add(lblEndereco);

		endTxt = new JTextField();
		endTxt.setBounds(10, 173, 322, 20);
		frmAluno.getContentPane().add(endTxt);
		endTxt.setColumns(20);

		lblRg = new JLabel("RG");
		lblRg.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRg.setBounds(10, 204, 46, 14);
		frmAluno.getContentPane().add(lblRg);

		try {
			rgTxt = new JFormattedTextField(new MaskFormatter("###########-#"));
		} catch (ParseException ex) {
			Logger.getLogger(AlunoForm.class.getName()).log(Level.SEVERE, null, ex);
		}
		rgTxt.setBounds(10, 224, 154, 20);
		frmAluno.getContentPane().add(rgTxt);
		rgTxt.setColumns(15);

		lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCpf.setBounds(183, 204, 46, 14);
		frmAluno.getContentPane().add(lblCpf);

		try {
			cpfTxt = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		} catch (ParseException ex) {
			Logger.getLogger(AlunoForm.class.getName()).log(Level.SEVERE, null, ex);
		}
		cpfTxt.setBounds(183, 224, 149, 20);
		frmAluno.getContentPane().add(cpfTxt);
		cpfTxt.setColumns(15);

		lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTelefone.setBounds(10, 255, 58, 14);
		frmAluno.getContentPane().add(lblTelefone);

		try {
			teltxt = new JFormattedTextField(new MaskFormatter("(##)####-####"));
		} catch (ParseException ex) {
			Logger.getLogger(AlunoForm.class.getName()).log(Level.SEVERE, null, ex);
		}
		teltxt.setBounds(10, 271, 154, 20);
		frmAluno.getContentPane().add(teltxt);
		teltxt.setColumns(15);

		toolBar = new JToolBar();
		toolBar.setEnabled(true);
		toolBar.setFloatable(false);
		toolBar.setBounds(0, 0, 450, 28);
		frmAluno.getContentPane().add(toolBar);

		btnNovo = new JButton("Novo");
		btnNovo.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNovo.setIcon(new ImageIcon(AlunoForm.class.getResource("/icone/add.png")));
		toolBar.add(btnNovo);
		toolBar.addSeparator();

		btnAbrir = new JButton("Abrir");
		btnAbrir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAbrir.setIcon(new ImageIcon(AlunoForm.class.getResource("/icone/book_open.png")));
		toolBar.add(btnAbrir);
		toolBar.addSeparator();

		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalvar.setIcon(new ImageIcon(AlunoForm.class.getResource("/icone/page_save.png")));
		toolBar.add(btnSalvar);
		toolBar.addSeparator();

		btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExcluir.setIcon(new ImageIcon(AlunoForm.class.getResource("/icone/cancel.png")));
		toolBar.add(btnExcluir);
		toolBar.addSeparator();
		btnExcluir.setEnabled(false);

	}

	
	private void acoesBotoes() {

		btnNovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aoClicarNoBotaoNovo();
			}
		});
		

		btnAbrir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Aluno a;

				AlunoDAO dao = new AlunoDAO(ConnectionPool.getConexao());

				if (!codTxt.getText().isEmpty()) {
					a = dao.buscaPorCodigo(Integer.parseInt(codTxt.getText()));
					
					aoCliclarNoBotaoAbrir();
					
				}else {
					JOptionPane.showMessageDialog(null,"Campo código vazio.\n Impossível fazer pesquisa no banco de dados.");
					return;
				}

				preencheCampos(a);
			}
		});

		
		btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AlunoDAO dao = new AlunoDAO(ConnectionPool.getConexao());

				Aluno a = montaAlunoComDadosDoFomulário();
				int codigo = dao.existe(a);

				if (codigo <= 0 && JOptionPane.showConfirmDialog(null, "Não foi encontrado Aluno com código "+ codTxt.getText()+" no banco. Deseja salvar novo aluno?", "Salvar",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					
					dao.salvar(a);
					JOptionPane.showMessageDialog(null, "Aluno salvo com sucesso!");
					aoClicarNoBotaoNovo();

				} else if (JOptionPane.showConfirmDialog(null, "Aluno com código "+ codTxt.getText()+" encontrado no banco. Deseja atualizá-lo?", "Atualizar",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

					dao.atualizar(a);
					JOptionPane.showMessageDialog(null, "Aluno atualizado com sucesso!");
					aoClicarNoBotaoNovo();

				}
			}
		});

		
		btnExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Aluno a = montaAlunoComDadosDoFomulário();
				AlunoDAO dao = new AlunoDAO(ConnectionPool.getConexao());
				
				if(JOptionPane.showConfirmDialog(null, "Deseja excluir aluno?", "Excluir",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					 dao.deletar(a);
					 aoClicarNoBotaoNovo();
				}
			}
		});
	}
}
