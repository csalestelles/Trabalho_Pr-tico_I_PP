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

	private JFrame frame;
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
	public AdminWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.GRAY);
		frame.setJMenuBar(menuBar);
		
		JMenu mnPesquisar = new JMenu("Adicionar");
		mnPesquisar.setBackground(Color.GRAY);
		menuBar.add(mnPesquisar);
		
		JMenuItem menuItem = new JMenuItem("Usuário");
		mnPesquisar.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Livro");
		mnPesquisar.add(menuItem_1);
		
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
		frame.getContentPane().setLayout(null);
		
		String[] itens = {"", "Usuário", "Livro", "Monografia", "Periódico", "Revista", "Tese"};
		JComboBox comboBox = new JComboBox(itens);
		comboBox.setBounds(26, 49, 178, 27);
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Pesquisar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//TRATAR PESQUISA NO BANCO DE DADOS E CRIAR JANELA DE RELATÓRIO
			}
		});
		btnNewButton.setBounds(260, 207, 161, 29);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(26, 117, 331, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JTextPane txtpnSelecione = new JTextPane();
		txtpnSelecione.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		txtpnSelecione.setForeground(new Color(0, 0, 0));
		txtpnSelecione.setEnabled(false);
		txtpnSelecione.setEditable(false);
		txtpnSelecione.setBackground(SystemColor.window);
		txtpnSelecione.setText("Selecione o que você deseja presquisar");
		txtpnSelecione.setBounds(29, 88, 328, 17);
		frame.getContentPane().add(txtpnSelecione);
		
		JTextPane textPane = new JTextPane();
		textPane.setEnabled(false);
		textPane.setEditable(false);
		textPane.setBackground(SystemColor.window);
		textPane.setBounds(26, 155, 331, 16);
		frame.getContentPane().add(textPane);
		
		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				mudarLabel(txtpnSelecione, comboBox.getSelectedItem().toString(), textPane, textField_1);
				
			}
		});
		btnSelecionar.setBounds(240, 48, 117, 29);
		frame.getContentPane().add(btnSelecionar);
		
		textField_1 = new JTextField();
		textField_1.setBounds(26, 183, 178, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setVisible(false);
		
	}
	
	private void mudarLabel(JTextPane label, String i, JTextPane textPane, JTextField textField)
	{
		textField_1.setVisible(true);
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
				textPane.setText("Instituição:");
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
