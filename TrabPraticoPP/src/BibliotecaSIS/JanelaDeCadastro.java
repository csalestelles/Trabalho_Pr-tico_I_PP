package BibliotecaSIS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Random;
import java.awt.event.ActionEvent;

public class JanelaDeCadastro extends BancoDeDados {

	private JFrame frame;
	private JTextField nomeCompletoField;
	private JTextField nomeUserField;
	private JTextField dataField;
	private JPasswordField passField;
	private JPasswordField confirmPassField;
	
	private UsuarioDAO usuario;
	

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
		frame = new JFrame("Cadastro de Usuário");
		frame.setBounds(100, 100, 450, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblInsiraSeusDados = new JLabel("Insira seus dados:");
		lblInsiraSeusDados.setHorizontalAlignment(SwingConstants.CENTER);
		lblInsiraSeusDados.setBounds(172, 6, 127, 31);
		frame.getContentPane().add(lblInsiraSeusDados);
		
		JLabel lblNome = new JLabel("Nome Completo:");
		lblNome.setBounds(20, 61, 106, 16);
		frame.getContentPane().add(lblNome);
		
		nomeCompletoField = new JTextField();
		nomeCompletoField.setBounds(158, 56, 267, 26);
		frame.getContentPane().add(nomeCompletoField);
		nomeCompletoField.setColumns(10);
		
		JLabel lblNomeDeUsurio = new JLabel("Nome de usuário:");
		lblNomeDeUsurio.setBounds(20, 89, 116, 16);
		frame.getContentPane().add(lblNomeDeUsurio);
		
		nomeUserField = new JTextField();
		nomeUserField.setBounds(158, 84, 267, 26);
		frame.getContentPane().add(nomeUserField);
		nomeUserField.setColumns(10);
		
		JLabel lblDataDeNascimento = new JLabel("Data de nascimento:");
		lblDataDeNascimento.setBounds(20, 117, 139, 16);
		frame.getContentPane().add(lblDataDeNascimento);
		
		MaskFormatter dataMask = null;
		try {
			dataMask = new MaskFormatter("##/##/####");
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		dataField = new JFormattedTextField(dataMask);
		dataField.setHorizontalAlignment(SwingConstants.CENTER);
		dataField.setBounds(158, 112, 116, 26);
		frame.getContentPane().add(dataField);
		dataField.setColumns(10);
		
		JLabel lblNovaSenha = new JLabel("Nova senha:");
		lblNovaSenha.setBounds(20, 147, 90, 16);
		frame.getContentPane().add(lblNovaSenha);
		
		passField = new JPasswordField();
		passField.setBounds(158, 142, 116, 26);
		frame.getContentPane().add(passField);
		
		JLabel lblConfirmeSuaSenha = new JLabel("Confirme sua senha:");
		lblConfirmeSuaSenha.setBounds(20, 178, 139, 16);
		frame.getContentPane().add(lblConfirmeSuaSenha);
		
		confirmPassField = new JPasswordField();
		confirmPassField.setBounds(158, 173, 116, 26);
		frame.getContentPane().add(confirmPassField);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(passField.getPassword()).trim();
				String passwordConfirmation = new String(confirmPassField.getPassword()).trim();
				if(password.equals(passwordConfirmation) && password.length() > 6)
				{
					try 
					{
						usuario = new UsuarioDAO();
						usuario.usuario.setNomeCompleto(nomeCompletoField.getText());
						usuario.usuario.setNomeDeUsuario(nomeUserField.getText());
						usuario.usuario.setSenha(password);
						usuario.usuario.setAnoDeNascimento(dataField.getText());
						usuario.usuario.setPodeEmprestar(BancoDeDados.SIM);
						JOptionPane.showMessageDialog(null, usuario.atualizar(BancoDeDados.INCLUSAO));
						//USUÁRIO CADASTRADO COM SUCESSO
						frame.dispose();
						RelatorioDAO.atualizarAdicao(0);
						MainWindow mainWindow = new MainWindow();
						mainWindow.main(null);
					} 
					catch (SQLException e1) {e1.printStackTrace();}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "A senha deve ter mais de 6 dígitos");
				}
			}
				
		});
		btnCadastrar.setBounds(172, 211, 117, 29);
		frame.getContentPane().add(btnCadastrar);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Arquivo");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mnNewMenu.add(mntmSair);
		mntmSair.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
				MainWindow.main(null);
			}
		});
	}

}
