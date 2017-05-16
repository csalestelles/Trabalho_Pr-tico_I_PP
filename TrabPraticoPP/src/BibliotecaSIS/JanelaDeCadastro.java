package BibliotecaSIS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class JanelaDeCadastro {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaDeCadastro window = new JanelaDeCadastro();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JanelaDeCadastro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblInsiraSeusDados = new JLabel("Insira seus dados:");
		lblInsiraSeusDados.setHorizontalAlignment(SwingConstants.CENTER);
		lblInsiraSeusDados.setBounds(171, 23, 127, 31);
		frame.getContentPane().add(lblInsiraSeusDados);
		
		JLabel lblNome = new JLabel("Nome Completo:");
		lblNome.setBounds(19, 78, 106, 16);
		frame.getContentPane().add(lblNome);
		
		textField = new JTextField();
		textField.setBounds(157, 73, 267, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNomeDeUsurio = new JLabel("Nome de usuário:");
		lblNomeDeUsurio.setBounds(19, 106, 116, 16);
		frame.getContentPane().add(lblNomeDeUsurio);
		
		textField_1 = new JTextField();
		textField_1.setBounds(157, 101, 267, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDataDeNascimento = new JLabel("Data de nascimento:");
		lblDataDeNascimento.setBounds(19, 134, 139, 16);
		frame.getContentPane().add(lblDataDeNascimento);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBounds(157, 129, 116, 26);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNovaSenha = new JLabel("Nova senha:");
		lblNovaSenha.setBounds(19, 164, 90, 16);
		frame.getContentPane().add(lblNovaSenha);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(157, 159, 116, 26);
		frame.getContentPane().add(passwordField);
		
		JLabel lblConfirmeSuaSenha = new JLabel("Confirme sua senha:");
		lblConfirmeSuaSenha.setBounds(19, 195, 139, 16);
		frame.getContentPane().add(lblConfirmeSuaSenha);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(157, 190, 116, 26);
		frame.getContentPane().add(passwordField_1);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(171, 228, 117, 29);
		frame.getContentPane().add(btnCadastrar);
	}

}
