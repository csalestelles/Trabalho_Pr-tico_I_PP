package BibliotecaSIS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class RecuperaSenha extends BancoDeDados {

	private JFrame frame;
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
	
	private void alteraSenha(Usuario user)
	{
//		UPDATE personagens SET apelido='Star­Lord' WHERE id=1;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRecuperaoDeSenha = new JLabel("Recuperação de senha");
		lblRecuperaoDeSenha.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblRecuperaoDeSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecuperaoDeSenha.setBounds(140, 28, 170, 16);
		frame.getContentPane().add(lblRecuperaoDeSenha);
		
		JLabel lblConfirmeSeuNome = new JLabel("Confirme seu nome de usuário:");
		lblConfirmeSeuNome.setBounds(19, 114, 205, 16);
		frame.getContentPane().add(lblConfirmeSeuNome);
		
		textField = new JTextField();
		textField.setBounds(239, 109, 179, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnGerarNovaSenha = new JButton("Gerar nova senha");
		btnGerarNovaSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				 int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza?", "Recuperar senha", 0);
				 Integer novoPass = 0;
				 if (confirm == 0)
				 {
					 try 
						{
							Statement st = conexao.createStatement();
							Random rand = new Random();
							novoPass = rand.nextInt(9000000) + 999999;
							st.executeUpdate("UPDATE Usuarios SET Pass='" + novoPass + "' WHERE USER='"+ 
									textField.getText() + "';");
							
						}
						catch (SQLException a){
							JOptionPane.showMessageDialog(null, "Nome de Usuário inválido!");
						}
					 JOptionPane.showMessageDialog(null, "A sua nova senha é " + novoPass, "Nova Senha", 1);
				 }
				 
				
			}
		});
		btnGerarNovaSenha.setBounds(150, 175, 150, 29);
		frame.getContentPane().add(btnGerarNovaSenha);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					frame.getDefaultCloseOperation();
			}
		});
		btnCancelar.setBounds(165, 216, 120, 29);
		frame.getContentPane().add(btnCancelar);
	}

}