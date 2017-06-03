package BibliotecaSIS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class RecuperaSenha extends BancoDeDados {

	private JFrame frame;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecuperaSenha window = new RecuperaSenha();
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
	public RecuperaSenha() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		 
		frame = new JFrame("Recuperação de senha");
		frame.setBounds(100, 100, 450, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRecuperaoDeSenha = new JLabel("Recuperação de senha");
		lblRecuperaoDeSenha.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblRecuperaoDeSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecuperaoDeSenha.setBounds(139, 6, 170, 16);
		frame.getContentPane().add(lblRecuperaoDeSenha);
		
		JLabel lblConfirmeSeuNome = new JLabel("Defina sua nova senha:");
		lblConfirmeSeuNome.setBounds(17, 87, 176, 16);
		frame.getContentPane().add(lblConfirmeSeuNome);
		
		JButton btnGerarNovaSenha = new JButton("Gerar nova senha");
		btnGerarNovaSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				 int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza?", "Recuperar senha", 0);
				 String password = new String(passwordField.getPassword()).trim();
				 String passwordConfirmation = new String(passwordField_1.getPassword()).trim();
				 if (confirm == 0)
				 {
					 //TRATAR ERRO DE SENHAS DIFERENTES!!!
					 checaSenha(password, passwordConfirmation, textField.getText());
					 frame.dispose();
					 MainWindow mainWindow = new MainWindow();
					 mainWindow.main(null);
				 }
				 
				
			}
		});
		btnGerarNovaSenha.setBounds(149, 200, 150, 29);
		frame.getContentPane().add(btnGerarNovaSenha);
		
		JLabel lblConfirmeANova = new JLabel("Confirme a nova senha:");
		lblConfirmeANova.setBounds(17, 115, 205, 16);
		frame.getContentPane().add(lblConfirmeANova);
		
		passwordField = new JPasswordField(6);
		passwordField.setBounds(204, 82, 217, 26);
		frame.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField(6);
		passwordField_1.setBounds(204, 110, 217, 26);
		frame.getContentPane().add(passwordField_1);
		
		JLabel lblNewLabel = new JLabel("*A nova senha deve ter entre 6 e 15 caractéres");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(17, 166, 415, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNomeDeUsurio = new JLabel("Nome de Usuário:");
		lblNomeDeUsurio.setBounds(17, 59, 121, 16);
		frame.getContentPane().add(lblNomeDeUsurio);
		
		textField = new JTextField();
		textField.setBounds(204, 54, 217, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnMais = new JMenu("Mais");
		menuBar.add(mnMais);
		
		JMenuItem mntmVoltar = new JMenuItem("Voltar");
		mnMais.add(mntmVoltar);
		mntmVoltar.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				MainWindow.main(null);
				frame.dispose();
			}
		});
	}
	
	private void checaSenha(String pass, String confirmPass, String userName)
	{
		if(pass.length() < 6 || confirmPass.length() > 15)
		 {
			 JOptionPane.showMessageDialog(null, "A senha não se encontra dentro do intervalo de caractéres ", 
					 "Erro Login", JOptionPane.ERROR_MESSAGE);
		 }
		else
		{
			if(!pass.equals(confirmPass))
			{
				JOptionPane.showMessageDialog(null, "As senhas são diferentes", 
						 "Erro Login", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				try
				{
					Statement st = conexao.createStatement();
					st.executeUpdate("UPDATE Usuarios SET Pass='" + pass + "' WHERE User='" + userName + "';");
					JOptionPane.showMessageDialog(null, "A senha foi alterado com sucesso!", 
							"Senha Alterada", JOptionPane.INFORMATION_MESSAGE);
				}
				catch(SQLException z){z.printStackTrace();}
			}
			
		}
	}
}
