package BibliotecaSIS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTable;

import java.sql.*;

public class AdminWindow extends JPanel 
{
	private JTabbedPane tpAbas;
	private JFrame frmPesquisa;
	private JPanel livroPanel, monografiaPanel, periodicoPanel, usuarioPanel, revistaPanel;
	private JScrollPane usuarioScroll, monografiaScroll, periodicoScroll, livroScroll, revistaScroll;
	
	private LivroDAO livros = new LivroDAO();
	private MonografiaDAO monografias;
	private PeriodicoDAO periodicos;
	private RevistaDAO revistas;
	private JTable table;

	private PreparedStatement statement;
	private ResultSet resultSet;
	
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
		frmPesquisa.setBounds(100, 100, 600, 690);
		frmPesquisa.setResizable(false);
		frmPesquisa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
				AddLivro addLivro = new AddLivro();
				addLivro.main(null);
				frmPesquisa.dispose();	
			}
		});
		
		JMenuItem menuItem_2 = new JMenuItem("Monografia");
		mnPesquisar.add(menuItem_2);
		menuItem_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				AddMonografia addMonografia = new AddMonografia();
				addMonografia.main(null);
				frmPesquisa.dispose();	
			}
		});
		
		JMenuItem menuItem_3 = new JMenuItem("Periódico");
		mnPesquisar.add(menuItem_3);
		menuItem_3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				AddPeriodico addPeriodico = new AddPeriodico();
				addPeriodico.main(null);
				frmPesquisa.dispose();	
			}
		});
		
		JMenuItem menuItem_4 = new JMenuItem("Revista");
		mnPesquisar.add(menuItem_4);
		
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
		
		JMenu mnSair = new JMenu("Sair");
		menuBar.add(mnSair);
		
		JMenuItem mntmLogOut = new JMenuItem("Log out");
		mnSair.add(mntmLogOut);
		
		JMenu mnTeste = new JMenu("Arquivo");
		menuBar.add(mnTeste);
		
		JMenu mnAdicionar = new JMenu("Adicionar");
		mnTeste.add(mnAdicionar);
		
		JMenuItem mntmMonografia_1 = new JMenuItem("Documento Acadêmico");
		mnAdicionar.add(mntmMonografia_1);
		
		JMenuItem mntmLivro_1 = new JMenuItem("Livro");
		mnAdicionar.add(mntmLivro_1);
		
		JMenuItem mntmPeridico_1 = new JMenuItem("Periódico");
		mnAdicionar.add(mntmPeridico_1);
		
		JMenuItem mntmRevista_1 = new JMenuItem("Revista");
		mnAdicionar.add(mntmRevista_1);
		
		JMenu mnRemover_1 = new JMenu("Remover");
		mnTeste.add(mnRemover_1);
		
		JMenuItem mntmUsurio_1 = new JMenuItem("Usuário");
		mnRemover_1.add(mntmUsurio_1);
		
		JMenuItem mntmDocumentoAcadmico = new JMenuItem("Documento Acadêmico");
		mnRemover_1.add(mntmDocumentoAcadmico);
		
		JMenuItem mntmLivro_2 = new JMenuItem("Livro");
		mnRemover_1.add(mntmLivro_2);
		
		JMenuItem mntmPeridico_2 = new JMenuItem("Periódico");
		mnRemover_1.add(mntmPeridico_2);
		
		JMenuItem mntmRevista_2 = new JMenuItem("Revista");
		mnRemover_1.add(mntmRevista_2);
		frmPesquisa.getContentPane().setLayout(null);
		mntmLogOut.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				AdminLogin adminLogin = new AdminLogin();
				frmPesquisa.dispose();
				adminLogin.main(null);
			}
		});
		
//		String[] itens = {"", "Usuário", "Livro", "Monografia", "Periódico", "Revista", "Tese"};
		tpAbas = new JTabbedPane();
		
		usuarioScroll = new JScrollPane();
		tpAbas.addTab("Usuários", usuarioScroll);
		
		livroScroll = new JScrollPane();
		try 
		{
			livros.setTabela(table, livroScroll);
		} 
		catch (SQLException e1) {e1.printStackTrace();}
		tpAbas.add("Livros", livroScroll);
		
		tpAbas.add("Monografias", monografiaPanel);
		monografiaPanel = new JPanel();
		monografiaScroll = new JScrollPane();
		monografiaScroll.setBounds(50, 50, 500, 590);
		monografiaPanel.add(monografiaScroll);
		
		tpAbas.add("Periódicos", periodicoPanel);
		periodicoPanel = new JPanel();
		periodicoScroll = new JScrollPane();
		periodicoScroll.setBounds(50, 50, 500, 590);
		periodicoPanel.add(periodicoScroll);
		
		tpAbas.add("Revistas", revistaPanel);
		revistaPanel = new JPanel();
		revistaScroll = new JScrollPane();
		revistaScroll.setBounds(50, 50, 500, 590);
		revistaPanel.add(revistaScroll);
		
		tpAbas.setBounds(50, 50, 500, 590);
		frmPesquisa.getContentPane().add(tpAbas);
		
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(50, 9, 117, 29);
		frmPesquisa.getContentPane().add(btnAdicionar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setBounds(179, 9, 117, 29);
		frmPesquisa.getContentPane().add(btnRemover);
		
		JButton btnEdita = new JButton("Editar");
		btnEdita.setBounds(308, 9, 117, 29);
		frmPesquisa.getContentPane().add(btnEdita);
		
		JButton btnRelatorio = new JButton("Relatório");
		btnRelatorio.setBounds(437, 9, 117, 29);
		frmPesquisa.getContentPane().add(btnRelatorio);
	}
}
