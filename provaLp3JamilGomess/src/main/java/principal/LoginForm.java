package principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class LoginForm {

	private JFrame frmLogin;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel label;
	private int tentativa = 4;

	
	public static void main(String[] args) {
		new LoginForm();
	}
	

	/**
	 * Create the application.
	 */
	public LoginForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setVisible(true);
		frmLogin.setResizable(false);
		frmLogin.getContentPane().setBackground(Color.WHITE);
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginForm.class.getResource("/icone/application.png")));
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 450, 369);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.setLocationRelativeTo(null);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setIcon(new ImageIcon(LoginForm.class.getResource("/icone/user.png")));
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome.setBounds(20, 156, 66, 20);
		frmLogin.getContentPane().add(lblNome);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setIcon(new ImageIcon(LoginForm.class.getResource("/icone/lock.png")));
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSenha.setBounds(20, 223, 66, 20);
		frmLogin.getContentPane().add(lblSenha);
		
		textField = new JTextField();
		textField.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		textField.setBounds(20, 181, 389, 20);
		frmLogin.getContentPane().add(textField);
		textField.setColumns(15);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		passwordField.setColumns(4);
		passwordField.setBounds(20, 248, 389, 20);
		frmLogin.getContentPane().add(passwordField);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(LoginForm.class.getResource("/icone/icon.png")));
		label.setBounds(118, 11, 170, 129);
		frmLogin.getContentPane().add(label);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 String pass = String.valueOf(passwordField.getPassword());
	               if(tentativa !=0 ){
	                    if(!(textField.getText().equals("admin") && pass.equals("admin")) ){
	                         tentativa --;
	                         JOptionPane.showMessageDialog(null, "Erro ao logar:\nNome ou senha incorreta! Tente novamente.\nTentativas restantes: "+ tentativa);
	                         textField.setText("");
	                         passwordField.setText("");
	                        
	                                 
	                    }else{
	                        frmLogin.dispose();
	                        new PrincipalForm();
	                    }    
	                     
	                }else{
	                   frmLogin.dispose();
	               }
	            }
			
		});
		btnOk.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnOk.setIcon(new ImageIcon(LoginForm.class.getResource("/icone/accept.png")));
		btnOk.setBounds(145, 289, 143, 30);
		frmLogin.getContentPane().add(btnOk);
	}
}
