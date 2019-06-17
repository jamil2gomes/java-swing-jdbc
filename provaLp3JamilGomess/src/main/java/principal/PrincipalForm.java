package principal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class PrincipalForm {

	private JFrame frmPrincipal;



	/**
	 * Create the application.
	 */
	public PrincipalForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
		frmPrincipal = new JFrame();
		frmPrincipal.setFont(new Font("Dialog", Font.BOLD, 12));
		frmPrincipal.setResizable(false);
		frmPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmPrincipal.setIconImage(Toolkit.getDefaultToolkit().getImage(PrincipalForm.class.getResource("/icone/application_home.png")));
		frmPrincipal.setTitle("Principal");
		frmPrincipal.getContentPane().setBackground(Color.WHITE);
		frmPrincipal.setBounds(100, 100, dimension.width, dimension.height);
		frmPrincipal.setLocationRelativeTo(null);
        frmPrincipal.setVisible(true);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PrincipalForm.class.getResource("/icone/SistemaQuality1411-1077.png")));
		lblNewLabel.setBounds(0, 0, dimension.width, dimension.height);
		frmPrincipal.getContentPane().add(lblNewLabel);
		
		JMenuBar menuBar = new JMenuBar();
		frmPrincipal.setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		mnArquivo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnArquivo.setIcon(new ImageIcon(PrincipalForm.class.getResource("/icone/page_white_text.png")));
		menuBar.add(mnArquivo);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int resposta =  JOptionPane.showConfirmDialog(null, "Deseja sair?", "Sair", JOptionPane.YES_NO_OPTION);
	               
	               if(resposta == JOptionPane.YES_OPTION){
	                   frmPrincipal.dispose();
	               }
			}
		});
		mntmSair.setIcon(new ImageIcon(PrincipalForm.class.getResource("/icone/door_out.png")));
		mnArquivo.add(mntmSair);
		
		JMenu mnTabelas = new JMenu("Tabelas");
		mnTabelas.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnTabelas.setIcon(new ImageIcon(PrincipalForm.class.getResource("/icone/table.png")));
		menuBar.add(mnTabelas);
		
		JMenuItem mntmAlunos = new JMenuItem("Alunos");
		mntmAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AlunoForm();
			}
		});
		mntmAlunos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmAlunos.setIcon(new ImageIcon(PrincipalForm.class.getResource("/icone/user.png")));
		mnTabelas.add(mntmAlunos);
		
		JMenuItem mntmProfessores = new JMenuItem("Professores");
		mntmProfessores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ProfessorForm();
			}
		});
		mntmProfessores.setIcon(new ImageIcon(PrincipalForm.class.getResource("/icone/user_suit.png")));
		mntmProfessores.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnTabelas.add(mntmProfessores);
		
		JMenuItem mntmDisciplinas = new JMenuItem("Disciplinas");
		mntmDisciplinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DisciplinaForm();
			}
		});
		mntmDisciplinas.setIcon(new ImageIcon(PrincipalForm.class.getResource("/icone/page_white_visualstudio.png")));
		mntmDisciplinas.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnTabelas.add(mntmDisciplinas);
		
		JMenuItem mntmCursos = new JMenuItem("Cursos");
		mntmCursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CursoForm();
			}
		});
		mntmCursos.setIcon(new ImageIcon(PrincipalForm.class.getResource("/icone/page_white_c.png")));
		mntmCursos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnTabelas.add(mntmCursos);
		
		JMenu mnSpf = new JMenu("SPF");
		mnSpf.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnSpf.setIcon(new ImageIcon(PrincipalForm.class.getResource("/icone/application_side_boxes.png")));
		menuBar.add(mnSpf);
		
		JMenuItem mntmGrade = new JMenuItem("Grade");
		mntmGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GradeForm();
			}
		});
		mntmGrade.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmGrade.setIcon(new ImageIcon(PrincipalForm.class.getResource("/icone/application_view_columns.png")));
		mnSpf.add(mntmGrade);
		
		JMenuItem mntmProfdisc = new JMenuItem("Professor/Disciplina");
		mntmProfdisc.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmProfdisc.setIcon(new ImageIcon(PrincipalForm.class.getResource("/icone/application_view_columns.png")));
		mntmProfdisc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ProfDiscForm();
			}
		});
		mnSpf.add(mntmProfdisc);
		
		JMenuItem mntmMataluno = new JMenuItem("Aluno/Curso");
		mntmMataluno.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmMataluno.setIcon(new ImageIcon(PrincipalForm.class.getResource("/icone/application_view_columns.png")));
		mntmMataluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MatAlunoForm(); 
			}
		});
		mnSpf.add(mntmMataluno);
		
		JMenu mnSobre = new JMenu("Ajuda");
		mnSobre.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnSobre.setIcon(new ImageIcon(PrincipalForm.class.getResource("/icone/help.png")));
		menuBar.add(mnSobre);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Nome: Aplicativo Aleat�rio para Prova de LP3\nVers�o: 1.4\nAno:2019\nAutor: Jamil Gomes");
			}
		});
		mntmSobre.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmSobre.setIcon(new ImageIcon(PrincipalForm.class.getResource("/icone/information.png")));
		mnSobre.add(mntmSobre);
		
		
	}
}
