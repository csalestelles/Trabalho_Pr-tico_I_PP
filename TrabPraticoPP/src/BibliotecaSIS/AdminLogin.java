package BibliotecaSIS;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JButton;

public class AdminLogin extends BancoDeDados {

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
					AdminLogin window = new AdminLogin();
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
	public AdminLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Acesso Administrador");
		frame.setBounds(100, 100, 450, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnCadastrar = new JMenu("Cadastrar");
		menuBar.add(mnCadastrar);
		
		JMenuItem mntmAdministrador = new JMenuItem("Administrador");
		mnCadastrar.add(mntmAdministrador);
		mntmAdministrador.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				CadastroAdmin cadastroAdmin = new CadastroAdmin();
				cadastroAdmin.main(null);
			}
		});
		
		JMenuItem mntmUsurio = new JMenuItem("Usuário");
		mnCadastrar.add(mntmUsurio);
		mntmUsurio.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				JanelaDeCadastro janelaDeCadastro = new JanelaDeCadastro();
				janelaDeCadastro.main(null);
			}
		});
		
		JMenu mnLogin = new JMenu("Login");
		menuBar.add(mnLogin);
		
		JMenuItem mntmAdministrados = new JMenuItem("Usuário");
		mnLogin.add(mntmAdministrados);
		mntmAdministrados.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				MainWindow mainWindow = new MainWindow();
				mainWindow.main(null);
				frame.dispose();
			}
		});
		
		JMenu mnSobre = new JMenu("Mais");
		menuBar.add(mnSobre);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mnSobre.add(mntmSobre);
		mntmSobre.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				//TRATAR O "SOBRE" DO SOFTWARE
			}
		});
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mnSobre.add(mntmSair);
		frame.getContentPane().setLayout(null);
		mntmSair.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		});
		
		JLabel label = new JLabel("Biblioteca da Faculdade de Tecnologia da FT");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(6, 0, 439, 69);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Usuário:");
		label_1.setBounds(118, 67, 61, 29);
		frame.getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(191, 68, 130, 26);
		frame.getContentPane().add(textField);
		
		JLabel label_2 = new JLabel("Senha:");
		label_2.setBounds(118, 97, 61, 29);
		frame.getContentPane().add(label_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(191, 98, 130, 26);
		frame.getContentPane().add(passwordField);
		
		JButton button = new JButton("Fazer login");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = null;
				String password = new String(passwordField.getPassword()).trim();
				try 
				{
					Statement st = conexao.createStatement();
					rs = st.executeQuery("SELECT * FROM Administradores WHERE User='" + textField.getText() 
						+ "' AND Pass='" + password + "';");
					
					if (rs.first())
					{
						AdminWindow adminWindow = new AdminWindow();
						frame.dispose();
						adminWindow.main(null);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Nome de usuário ou senha Incorreto! " 
								+ password, "Erro Login", JOptionPane.ERROR_MESSAGE);	
					}
				} 
				catch (SQLException e1) {e1.printStackTrace();}
			}
		});
		button.setBounds(161, 179, 130, 29);
		frame.getContentPane().add(button);
		
		JLabel lblNoEsqueaSua = new JLabel("*Não esqueça sua senha, administradores não podem recuperá-la!");
		lblNoEsqueaSua.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoEsqueaSua.setBounds(6, 138, 439, 29);
		frame.getContentPane().add(lblNoEsqueaSua);
		
		
	}

}
