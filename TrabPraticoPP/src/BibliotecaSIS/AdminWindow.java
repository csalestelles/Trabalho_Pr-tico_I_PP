package BibliotecaSIS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;

public class AdminWindow {

	private JFrame frmPesquisa;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminWindow window = new AdminWindow();
					window.frmPesquisa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPesquisa = new JFrame();
		frmPesquisa.setTitle("Pesquisa");
		frmPesquisa.setBounds(100, 100, 450, 300);
		frmPesquisa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.GRAY);
		frmPesquisa.setJMenuBar(menuBar);
		
		JMenu mnPesquisar = new JMenu("Adicionar");
		mnPesquisar.setBackground(Color.GRAY);
		menuBar.add(mnPesquisar);
		
		JMenuItem menuItem_1 = new JMenuItem("Livro");
		mnPesquisar.add(menuItem_1);
		menuItem_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				frmPesquisa.dispose();
				AddLivro addLivro = new AddLivro();
				addLivro.main(null);
				
			}
		});
		
		JMenuItem menuItem_2 = new JMenuItem("Monografia");
		mnPesquisar.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("Periódico");
		mnPesquisar.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("Revista");
		mnPesquisar.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem("Tese");
		mnPesquisar.add(menuItem_5);
		
		JMenu mnRemover = new JMenu("Remover");
		mnRemover.setBackground(Color.GRAY);
		menuBar.add(mnRemover);
		
		JMenuItem mntmUsurio = new JMenuItem("Usuário");
		mnRemover.add(mntmUsurio);
		
		JMenuItem mntmLivro = new JMenuItem("Livro");
		mnRemover.add(mntmLivro);
		
		JMenuItem mntmMonografia = new JMenuItem("Monografia");
		mnRemover.add(mntmMonografia);
		
		JMenuItem mntmPeridico = new JMenuItem("Periódico");
		mnRemover.add(mntmPeridico);
		
		JMenuItem mntmRevista = new JMenuItem("Revista");
		mnRemover.add(mntmRevista);
		
		JMenuItem mntmTese = new JMenuItem("Tese");
		mnRemover.add(mntmTese);
		
		JMenu mnSair = new JMenu("Sair");
		menuBar.add(mnSair);
		
		JMenuItem mntmLogOut = new JMenuItem("Log out");
		mnSair.add(mntmLogOut);
		frmPesquisa.getContentPane().setLayout(null);
		mntmLogOut.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				AdminLogin adminLogin = new AdminLogin();
				frmPesquisa.dispose();
				adminLogin.main(null);
			}
		});
		
		JTextPane txtpnSelecione = new JTextPane();
		txtpnSelecione.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		txtpnSelecione.setForeground(new Color(0, 0, 0));
		txtpnSelecione.setEnabled(false);
		txtpnSelecione.setEditable(false);
		txtpnSelecione.setBackground(SystemColor.window);
		txtpnSelecione.setText("Selecione o que você deseja presquisar");
		txtpnSelecione.setBounds(29, 102, 328, 17);
		frmPesquisa.getContentPane().add(txtpnSelecione);
		
		JTextPane txtpnSelecioneOQue = new JTextPane();
		txtpnSelecioneOQue.setText("Informações adicionais");
		txtpnSelecioneOQue.setEnabled(false);
		txtpnSelecioneOQue.setEditable(false);
		txtpnSelecioneOQue.setBackground(SystemColor.window);
		txtpnSelecioneOQue.setBounds(26, 169, 331, 16);
		frmPesquisa.getContentPane().add(txtpnSelecioneOQue);
		
		String[] itens = {"", "Usuário", "Livro", "Monografia", "Periódico", "Revista", "Tese"};
		JComboBox comboBox = new JComboBox(itens);
		comboBox.setBounds(26, 49, 178, 27);
		frmPesquisa.getContentPane().add(comboBox);
		comboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				mudarLabel(txtpnSelecione, comboBox.getSelectedItem().toString(), txtpnSelecioneOQue, textField_1);
			}
		});
		
		JButton btnNewButton = new JButton("Pesquisar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//TRATAR PESQUISA NO BANCO DE DADOS E CRIAR JANELA DE RELATÓRIO
			}
		});
		btnNewButton.setBounds(260, 207, 161, 29);
		frmPesquisa.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(26, 131, 331, 26);
		frmPesquisa.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(26, 197, 178, 26);
		frmPesquisa.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnListar = new JButton("Listar");
		btnListar.setBounds(260, 48, 117, 29);
		frmPesquisa.getContentPane().add(btnListar);
	}
	
	private void mudarLabel(JTextPane label, String i, JTextPane textPane, JTextField textField)
	{
		switch(i)
		{
			case "Usuário":
				label.setText("Insira o nome de usuário:");
				textPane.setText("Data de Nascimento:");
				break;
			case "Livro":
				label.setText("Insira o nome do livro:");
				textPane.setText("Edição:");
				break;
			case "Monografia":
				label.setText("Insira o título da monografia:");
				textPane.setText("Autor:");
				break;
			case "Periódico":
				label.setText("Insira o nome do periódico:");
				textPane.setText("Edição:");
				break;
			case "Revista":
				label.setText("Insira o nome da revista:");
				textPane.setText("Editora:");
				break;
			case "Tese":
				label.setText("Insira o título da tese:");
				textPane.setText("Autor:");
				break;
			default:
				label.setText("Selecione o que você deseja presquisar");
				textPane.setText("");
				textField_1.setVisible(false);
		}
	}
}