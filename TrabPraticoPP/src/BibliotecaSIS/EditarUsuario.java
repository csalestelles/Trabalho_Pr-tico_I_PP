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
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarUsuario {

	private static JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private UsuarioDAO usuarios;
	
	private String nomeCompleto, nomeUsuario, senha, dataNascimento;
	private int codigo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					EditarUsuario window = new EditarUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public EditarUsuario(int codigo, String nomeCompleto, String nomeUsuario, String senha, String dataNascimento)
	{
		this.codigo = codigo;
		this.nomeCompleto = nomeCompleto;
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
		usuarios = new UsuarioDAO();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Editar Usuario");
		frame.setBounds(100, 100, 450, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDados = new JLabel("Atualize as informações:");
		lblDados.setHorizontalAlignment(SwingConstants.CENTER);
		lblDados.setBounds(148, 6, 155, 31);
		frame.getContentPane().add(lblDados);
		
		JLabel label_1 = new JLabel("Nome Completo:");
		label_1.setBounds(24, 61, 106, 16);
		frame.getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(162, 56, 267, 26);
		textField.setText(nomeCompleto);
		frame.getContentPane().add(textField);
		
		JLabel label_2 = new JLabel("Nome de usuário:");
		label_2.setBounds(24, 89, 116, 16);
		frame.getContentPane().add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(162, 84, 267, 26);
		textField_1.setText(nomeUsuario);
		frame.getContentPane().add(textField_1);
		
		JLabel label_3 = new JLabel("Data de nascimento:");
		label_3.setBounds(24, 117, 139, 16);
		frame.getContentPane().add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setColumns(10);
		textField_2.setBounds(162, 112, 116, 26);
		textField_2.setText(dataNascimento);
		frame.getContentPane().add(textField_2);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(24, 147, 90, 16);
		frame.getContentPane().add(lblSenha);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				usuarios.usuario.setCodigo(codigo);
				usuarios.usuario.setNomeCompleto(textField.getText());
				usuarios.usuario.setNomeDeUsuario(textField_1.getText());
				usuarios.usuario.setSenha(textField_3.getText());
				usuarios.usuario.setAnoDeNascimento(textField_2.getText());
				JOptionPane.showMessageDialog(null, usuarios.atualizar(BancoDeDados.ALTERACAO));
				frame.dispose();
				AdminWindow.main(null);
			}
		});
		btnAtualizar.setBounds(176, 194, 117, 29);
		frame.getContentPane().add(btnAtualizar);
		
		textField_3 = new JTextField();
		textField_3.setBounds(162, 145, 116, 26);
		frame.getContentPane().add(textField_3);
		textField_3.setText(senha);
		textField_3.setColumns(10);
		
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
				AdminWindow.main(null);
			}
		});
	}
}
