package BibliotecaSIS;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
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
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Gerenciador de Livros e Documentos");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblBibliotecaDaFaculdade = new JLabel("Biblioteca da Faculdade de Tecnologia da FT");
		lblBibliotecaDaFaculdade.setHorizontalAlignment(SwingConstants.CENTER);
		lblBibliotecaDaFaculdade.setBounds(5, 5, 439, 69);
		frame.getContentPane().add(lblBibliotecaDaFaculdade);
		
		JButton btnAcessar = new JButton("Cadastre-se");
		btnAcessar.setBounds(160, 206, 130, 29);
		frame.getContentPane().add(btnAcessar);
		
		JButton btnNewButton = new JButton("Fazer login");
		btnNewButton.setBounds(160, 176, 130, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblUsurio = new JLabel("Usuário:");
		lblUsurio.setBounds(117, 72, 61, 29);
		frame.getContentPane().add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(117, 102, 61, 29);
		frame.getContentPane().add(lblSenha);
		
		textField = new JTextField();
		textField.setBounds(190, 73, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(190, 103, 130, 26);
		frame.getContentPane().add(passwordField);
	}
}
