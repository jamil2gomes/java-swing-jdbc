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
import dao.CursoDAO;
import modelo.Curso;

public class CursoForm {

	private JFrame frmCurso;
	private JTextField codTxt, nomeTxt;
	private JButton btnAbrir, btnSalvar, btnNovo, btnExcluir;
	private JToolBar toolBar;
	private JLabel lblCdigo, lblCurso;
	
	public static void main(String[] args) {
		new CursoForm();
	}

	/**
	 * Create the application.
	 */
	public CursoForm() {
		criarJanela();
		acoesBotoes();
		btnExcluir.setEnabled(false);
	}

	private Curso buscaDadosNoFormulario() {
		
		Curso curso = new Curso(Integer.parseInt(codTxt.getText()), nomeTxt.getText());
		return curso;
	}

	private void preencheCampos(Curso curso) {
		
		
		codTxt.setEditable(false);
		nomeTxt.setText(curso.getNome());
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
	}


	
	private void criarJanela() {
		frmCurso = new JFrame();
		frmCurso.setFont(new Font("Dialog", Font.BOLD, 12));
		frmCurso.setIconImage(Toolkit.getDefaultToolkit().getImage(CursoForm.class.getResource("/icone/page_white_c.png")));
		frmCurso.setTitle("Curso");
		frmCurso.setBounds(100, 100, 466, 351);
		frmCurso.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCurso.setVisible(true);
		frmCurso.setResizable(false);
		frmCurso.setLocationRelativeTo(null);
		frmCurso.getContentPane().setLayout(null);
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setEnabled(false);
		toolBar.setBounds(0, 0, 450, 28);
		frmCurso.getContentPane().add(toolBar);

		btnNovo = new JButton("Novo");
		btnNovo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNovo.setIcon(new ImageIcon(CursoForm.class.getResource("/icone/add.png")));
		toolBar.add(btnNovo);
		toolBar.addSeparator();
		
		btnAbrir = new JButton("Abrir");
		btnAbrir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAbrir.setIcon(new ImageIcon(CursoForm.class.getResource("/icone/book_open.png")));
		toolBar.add(btnAbrir);
		toolBar.addSeparator();
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalvar.setIcon(new ImageIcon(CursoForm.class.getResource("/icone/page_save.png")));
		toolBar.add(btnSalvar);
		toolBar.addSeparator();
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExcluir.setIcon(new ImageIcon(CursoForm.class.getResource("/icone/cancel.png")));
		toolBar.add(btnExcluir);
		toolBar.addSeparator();
		
		lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCdigo.setBounds(10, 72, 46, 14);
		frmCurso.getContentPane().add(lblCdigo);

		codTxt = new JTextField();
		codTxt.setBounds(10, 89, 233, 20);
		frmCurso.getContentPane().add(codTxt);
		codTxt.setColumns(10);

		lblCurso = new JLabel("Curso");
		lblCurso.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurso.setBounds(10, 127, 46, 14);
		frmCurso.getContentPane().add(lblCurso);

		nomeTxt = new JTextField();
		nomeTxt.setBounds(10, 146, 233, 20);
		frmCurso.getContentPane().add(nomeTxt);
		nomeTxt.setColumns(10);
		

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
				
				Curso c;
				CursoDAO dao = new CursoDAO(ConnectionPool.getConexao());
				
				if (!codTxt.getText().isEmpty()) {
					aoCliclarNoBotaoAbrir();
					c = dao.buscaPorCodigo(Integer.parseInt(codTxt.getText()));

				} else {
					JOptionPane.showMessageDialog(null,"Campo código vazio.\n Impossível fazer pesquisa no banco de dados.");
					return;
				}

				preencheCampos(c);

			}
		});
		
		

		btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				 CursoDAO dao = new CursoDAO(ConnectionPool.getConexao());
				 Curso p = buscaDadosNoFormulario();
				 int id = dao.existe(p);
				 
				 if(id <= 0 && JOptionPane.showConfirmDialog(null, "Não foi encontrado curso com código "+ codTxt.getText()+" no banco. Deseja salvar novo curso?", "Salvar",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
			            dao.salvar(p);
			            JOptionPane.showMessageDialog(null, "Curso salvo com sucesso!");
			            aoClicarNoBotaoNovo();
			            
			        } else if (JOptionPane.showConfirmDialog(null, "Deseja atualizar curso?", "Atualizar",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			        	
			           
			            dao.atualizar(p);
			            JOptionPane.showMessageDialog(null, "Curso atualizado com sucesso!");
			            aoClicarNoBotaoNovo();
			        }
				 
			}
		});

	
		
		btnExcluir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
									
				Curso c = buscaDadosNoFormulario();
				
				if(JOptionPane.showConfirmDialog(null, "Deseja excluir curso?", "Excluir",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					
					CursoDAO dao = new CursoDAO(ConnectionPool.getConexao());
			        dao.deletar(c);
			        aoClicarNoBotaoNovo();
				}
			}
		});

		
	}

}
