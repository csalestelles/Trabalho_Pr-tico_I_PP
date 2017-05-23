package BibliotecaSIS;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.Color;
import java.sql.*;

public class MainWindow extends BancoDeDados {

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
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JanelaDeCadastro janelaDeCadastro = new JanelaDeCadastro();
				janelaDeCadastro.main(null);
			}
		});
		
		btnAcessar.setBounds(159, 218, 130, 29);
		frame.getContentPane().add(btnAcessar);
		
		JButton btnNewButton = new JButton("Fazer login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ResultSet rs = null;
				String password = new String(passwordField.getPassword()).trim();
				if(textField.getText() == "admin" && password == "admin")
				{
					//ABRIR JANELA DO ADMINISTRADOR
				}
				try 
				{
					Statement st = conexao.createStatement();
					rs = st.executeQuery("SELECT * FROM Usuarios WHERE User='" + textField.getText() 
						+ "' AND Pass='" + password + "';");
				} 
				catch (SQLException e1) {e1.printStackTrace();}
				try 
				{
					if (!rs.next())
						JOptionPane.showMessageDialog(null, "Nome de usuário ou senha Incorreto!" 
								+ password, "Erro Login", JOptionPane.ERROR_MESSAGE);
					else
					{
						//ABRIR JANELA DE EMPRÉSTIMO E DOAÇÃO DE LIVROS
					}
						
				} 
				catch (SQLException e1) {e1.printStackTrace();}
				
			}
		});
		btnNewButton.setBounds(159, 188, 130, 29);
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
		
		JLabel lblNewLabel = new JLabel("Esqueci a senha");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RecuperaSenha recuperaSenha = new RecuperaSenha();
				recuperaSenha.main(null);
			}
		});
		lblNewLabel.setBounds(200, 130, 106, 16);
		frame.getContentPane().add(lblNewLabel);
	}
}
