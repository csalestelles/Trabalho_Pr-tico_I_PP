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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

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
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblBibliotecaDaFaculdade = new JLabel("Biblioteca da Faculdade de Tecnologia da FT");
		lblBibliotecaDaFaculdade.setHorizontalAlignment(SwingConstants.CENTER);
		lblBibliotecaDaFaculdade.setBounds(5, 5, 439, 69);
		frame.getContentPane().add(lblBibliotecaDaFaculdade);
		
		textField = new JTextField();
		textField.setBounds(190, 73, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Fazer login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ResultSet rs = null;
				String password = new String(passwordField.getPassword()).trim();
				try 
				{
					Statement st = conexao.createStatement();
					rs = st.executeQuery("SELECT * FROM Usuarios WHERE User='" + textField.getText() 
						+ "' AND Pass='" + password + "';");
					
					if (rs.first())
					{
						UsuarioWindow userWindow = new UsuarioWindow(rs.getInt(1));
						userWindow.main(null);
						frame.dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Nome de usuário ou senha Incorreto! " 
								, "Erro Login", JOptionPane.ERROR_MESSAGE);	
					}
				} 
				catch (SQLException e1) {e1.printStackTrace();}
				
			}
		});
		btnNewButton.setBounds(159, 177, 130, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblUsurio = new JLabel("Usuário:");
		lblUsurio.setBounds(117, 72, 61, 29);
		frame.getContentPane().add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(117, 102, 61, 29);
		frame.getContentPane().add(lblSenha);
		
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
				frame.dispose();
			}
		});
		lblNewLabel.setBounds(200, 130, 106, 16);
		frame.getContentPane().add(lblNewLabel);
		
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
				frame.dispose();
			}
		});
		
		JMenuItem mntmUsurio = new JMenuItem("Usuário");
		mnCadastrar.add(mntmUsurio);
		mntmUsurio.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				JanelaDeCadastro janelaDeCadastro = new JanelaDeCadastro();
				janelaDeCadastro.main(null);
				frame.dispose();
			}
		});
		
		JMenu mnLogin = new JMenu("Login");
		menuBar.add(mnLogin);
		
		JMenuItem mntmAdministrados= new JMenuItem("Administrador");
		mnLogin.add(mntmAdministrados);
		mntmAdministrados.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				AdminLogin adminLogin = new AdminLogin();
				adminLogin.main(null);
				frame.dispose();
			}
		});
		
		JMenu mnSobre = new JMenu("Mais\n");
		menuBar.add(mnSobre);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mnSobre.add(mntmSobre);
		mntmSobre.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				SobreWindow.main(null);;
			}
		});
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mnSobre.add(mntmSair);
		mntmSair.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		});
	}
}
