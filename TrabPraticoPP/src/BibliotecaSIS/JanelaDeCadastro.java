package BibliotecaSIS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.awt.event.ActionEvent;

public class JanelaDeCadastro extends BancoDeDados {

	private JFrame frame;
	private JTextField nomeCompletoField;
	private JTextField nomeUserField;
	private JTextField dataField;
	private JPasswordField passField;
	private JPasswordField confirmPassField;
	

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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblInsiraSeusDados = new JLabel("Insira seus dados:");
		lblInsiraSeusDados.setHorizontalAlignment(SwingConstants.CENTER);
		lblInsiraSeusDados.setBounds(171, 23, 127, 31);
		frame.getContentPane().add(lblInsiraSeusDados);
		
		JLabel lblNome = new JLabel("Nome Completo:");
		lblNome.setBounds(19, 78, 106, 16);
		frame.getContentPane().add(lblNome);
		
		nomeCompletoField = new JTextField();
		nomeCompletoField.setBounds(157, 73, 267, 26);
		frame.getContentPane().add(nomeCompletoField);
		nomeCompletoField.setColumns(10);
		
		JLabel lblNomeDeUsurio = new JLabel("Nome de usuário:");
		lblNomeDeUsurio.setBounds(19, 106, 116, 16);
		frame.getContentPane().add(lblNomeDeUsurio);
		
		nomeUserField = new JTextField();
		nomeUserField.setBounds(157, 101, 267, 26);
		frame.getContentPane().add(nomeUserField);
		nomeUserField.setColumns(10);
		
		JLabel lblDataDeNascimento = new JLabel("Data de nascimento:");
		lblDataDeNascimento.setBounds(19, 134, 139, 16);
		frame.getContentPane().add(lblDataDeNascimento);
		
		dataField = new JTextField();
		dataField.setHorizontalAlignment(SwingConstants.CENTER);
		dataField.setBounds(157, 129, 116, 26);
		frame.getContentPane().add(dataField);
		dataField.setColumns(10);
		
		JLabel lblNovaSenha = new JLabel("Nova senha:");
		lblNovaSenha.setBounds(19, 164, 90, 16);
		frame.getContentPane().add(lblNovaSenha);
		
		passField = new JPasswordField();
		passField.setBounds(157, 159, 116, 26);
		frame.getContentPane().add(passField);
		
		JLabel lblConfirmeSuaSenha = new JLabel("Confirme sua senha:");
		lblConfirmeSuaSenha.setBounds(19, 195, 139, 16);
		frame.getContentPane().add(lblConfirmeSuaSenha);
		
		confirmPassField = new JPasswordField();
		confirmPassField.setBounds(157, 190, 116, 26);
		frame.getContentPane().add(confirmPassField);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement st;
				String password = new String(passField.getPassword()).trim();
				String passwordConfirmation = new String(confirmPassField.getPassword()).trim();
				if(password.equals(passwordConfirmation) && password.length() >= 6)
				{
					try 
					{
						st = conexao.createStatement();
						st.executeUpdate("INSERT INTO Usuarios VALUES (NULL, '" + nomeCompletoField.getText() + "', '" 
								+ nomeUserField.getText() + "', '" + password + "', '" 
								+ dataField.getText() + "');");
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
					//TRATAR SENHA MENOR QUE SEIS DIGITOS
				}
			}
				
		});
		btnCadastrar.setBounds(171, 228, 117, 29);
		frame.getContentPane().add(btnCadastrar);
	}

}
