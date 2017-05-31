package BibliotecaSIS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTable;

import java.sql.*;

public class AdminWindow extends JPanel 
{
	private JTabbedPane tpAbas;
	private JFrame frmPesquisa;
	protected JTable table;
	private JScrollPane livroScroll, usuarioScroll, monografiaScroll, periodicoScroll, revistaScroll;
	private UsuarioDAO usuarios = new UsuarioDAO();
	private LivroDAO livros = new LivroDAO();
	private MonografiaDAO monografias = new MonografiaDAO();
	private PeriodicoDAO periodicos = new PeriodicoDAO();
	private RevistaDAO revistas = new RevistaDAO();
	
	private ArrayList<Livro> listLivros = new ArrayList<Livro>();
	
	private JButton btnAdicionar;
	

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
	public AdminWindow() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		frmPesquisa = new JFrame();
		frmPesquisa.setTitle("Pesquisa");
		frmPesquisa.setBounds(100, 100, 700, 590);
		frmPesquisa.setResizable(false);
		frmPesquisa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPesquisa.getContentPane().setLayout(null);
		
		/*
		 * Criação dos objetos da interface
		 */
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.GRAY);
		
		JMenu mnPesquisar = new JMenu("Adicionar");
		mnPesquisar.setBackground(Color.GRAY);
		
		JMenuItem menuItem_1 = new JMenuItem("Livro");
		
		JMenuItem menuItem_2 = new JMenuItem("Monografia");
		
		JMenuItem menuItem_3 = new JMenuItem("Periódico");
		
		JMenuItem menuItem_4 = new JMenuItem("Revista");
		
		JMenu mnRemover = new JMenu("Remover");
		mnRemover.setBackground(Color.GRAY);
		
		JMenuItem mntmUsurio = new JMenuItem("Usuário");
		
		JMenuItem mntmLivro = new JMenuItem("Livro");
		
		JMenuItem mntmMonografia = new JMenuItem("Monografia");
		
		JMenuItem mntmPeridico = new JMenuItem("Periódico");
		
		JMenuItem mntmRevista = new JMenuItem("Revista");
		
		JMenu mnSair = new JMenu("Sair");
		
		JMenuItem mntmLogOut = new JMenuItem("Log out");
		
		JMenu mnTeste = new JMenu("Arquivo");
		
		JMenu mnAdicionar = new JMenu("Adicionar");
		
		JMenuItem mntmMonografia_1 = new JMenuItem("Documento Acadêmico");
		
		JMenuItem mntmLivro_1 = new JMenuItem("Livro");
		
		JMenuItem mntmPeridico_1 = new JMenuItem("Periódico");
		
		JMenuItem mntmRevista_1 = new JMenuItem("Revista");
		
		JMenu mnRemover_1 = new JMenu("Remover");
		
		JMenuItem mntmUsurio_1 = new JMenuItem("Usuário");
		
		JMenuItem mntmDocumentoAcadmico = new JMenuItem("Documento Acadêmico");
		
		JMenuItem mntmLivro_2 = new JMenuItem("Livro");
		
		JMenuItem mntmPeridico_2 = new JMenuItem("Periódico");
		
		JMenuItem mntmRevista_2 = new JMenuItem("Revista");
		
		tpAbas = new JTabbedPane();
		table = new JTable();
		livroScroll = new JScrollPane();
		usuarioScroll = new JScrollPane();
		monografiaScroll = new JScrollPane();
		periodicoScroll = new JScrollPane();
		revistaScroll = new JScrollPane();
		
		JButton btnRemover = new JButton("Remover");
		btnAdicionar = new JButton("Adicionar");
		JButton btnRelatorio = new JButton("Relatório");
		JButton btnEdita = new JButton("Editar");
		
		
		
		
		/*
		 * Adicionando à interface
		 */
		
		frmPesquisa.setJMenuBar(menuBar);
		
		menuBar.add(mnPesquisar);
		menuBar.add(mnRemover);
		mnPesquisar.add(menuItem_2);
		mnPesquisar.add(menuItem_1);
		mnPesquisar.add(menuItem_3);
		mnPesquisar.add(menuItem_4);
		mnRemover.add(mntmUsurio);
		mnRemover.add(mntmLivro);
		mnRemover.add(mntmMonografia);
		mnRemover.add(mntmPeridico);
		mnRemover.add(mntmRevista);
		menuBar.add(mnSair);
		mnSair.add(mntmLogOut);
		menuBar.add(mnTeste);
		mnRemover_1.add(mntmRevista_2);
		mnTeste.add(mnAdicionar);
		mnAdicionar.add(mntmMonografia_1);
		mnAdicionar.add(mntmLivro_1);
		mnAdicionar.add(mntmPeridico_1);
		mnAdicionar.add(mntmRevista_1);
		mnTeste.add(mnRemover_1);
		mnRemover_1.add(mntmUsurio_1);
		mnRemover_1.add(mntmDocumentoAcadmico);
		mnRemover_1.add(mntmLivro_2);
		mnRemover_1.add(mntmPeridico_2);
		
		btnAdicionar.setBounds(66, 9, 104, 29);
		btnAdicionar.setEnabled(false);
		frmPesquisa.getContentPane().add(btnAdicionar);
		
		btnRemover.setBounds(182, 9, 104, 29);
		frmPesquisa.getContentPane().add(btnRemover);
		
		btnEdita.setBounds(298, 9, 104, 29);
		frmPesquisa.getContentPane().add(btnEdita);
		
		btnRelatorio.setBounds(414, 9, 104, 29);
		frmPesquisa.getContentPane().add(btnRelatorio);
		
		tpAbas.add("Usuarios", usuarioScroll);
		tpAbas.add("Livros", livroScroll);
		tpAbas.add("Monografias", monografiaScroll);
		tpAbas.add("Periódicos", periodicoScroll);
		tpAbas.add("Revistas", revistaScroll);
		tpAbas.setBounds(50, 50, 600, 490);
		frmPesquisa.getContentPane().add(tpAbas);
		
		JButton btnInfo = new JButton("Info");
		btnInfo.setBounds(530, 9, 104, 29);
		frmPesquisa.getContentPane().add(btnInfo);
		
		usuarioScroll.setViewportView(table);
		usuarios.showUsuariosTable(table);
		
		
		/*
		 * EVENTOS
		 */
		menuItem_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				AddLivro addLivro = new AddLivro();
				addLivro.main(null);
				frmPesquisa.dispose();	
			}
		});
		
		menuItem_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				AddMonografia addMonografia = new AddMonografia();
				addMonografia.main(null);
				frmPesquisa.dispose();	
			}
		});
		
		menuItem_3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				AddPeriodico addPeriodico = new AddPeriodico();
				addPeriodico.main(null);
				frmPesquisa.dispose();	
			}
		});
		
		mntmLogOut.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				AdminLogin adminLogin = new AdminLogin();
				frmPesquisa.dispose();
				adminLogin.main(null);
			}
		});
		
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				adicionarBotao(tpAbas);
			}
		});
		
		
		 tpAbas.addChangeListener(new javax.swing.event.ChangeListener() {
			    public void stateChanged(javax.swing.event.ChangeEvent e) {
			        int aba = tpAbas.getSelectedIndex();
			    	if (aba == 0) 
			        {
			    		usuarioScroll.setViewportView(table);
			            btnAdicionar.setEnabled(false);
			            usuarios.showUsuariosTable(table);
			        }
			        else
			        {
			        	btnAdicionar.setEnabled(true);
			        	if (aba == 1)
			        	{
			        		livroScroll.setViewportView(table);
			        		livros.showLivrosTable(table);
			        	}
			        	else if (aba == 2)
			        	{
			        		monografiaScroll.setViewportView(table);
			        		monografias.showDocsTable(table);
			        	}
			        	else if (aba == 3)
			        	{
			        		periodicoScroll.setViewportView(table);
			        		periodicos.showPeriodicosTable(table);
			        	}
			        	else
			        	{
			        		revistaScroll.setViewportView(table);
			        		revistas.showRevistasTable(table);
			        	}
			        }
			    }
			});
		
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					removerBotao(tpAbas);
				} 
				catch (SQLException e1) {e1.printStackTrace();}
			}
		});
	
		btnEdita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				editarBotao(tpAbas);
			}
		});
	}
	
	/*
	 * 
	 * FIM DO INITIALIZE
	 * 
	 * *******************************************************
	 * 
	 * MÉTODOS ADICIONAIS
	 * 
	 */
	
	private void adicionarBotao(JTabbedPane abas)
	{
		int valor = abas.getSelectedIndex();
		switch(valor)
		{
			case 1:
				frmPesquisa.dispose();
				AddLivro.main(null);
				break;
			case 2:
				frmPesquisa.dispose();
				AddMonografia.main(null);
				break;
			case 3:
				frmPesquisa.dispose();
				AddPeriodico.main(null);
				break;
			case 4:
				frmPesquisa.dispose();
				AddRevista.main(null);
				break;
			default: 
				System.out.println("erro\n");	
		}
	}
	
	private void removerBotao(JTabbedPane abas) throws SQLException
	{
		int valor = abas.getSelectedIndex();
		switch(valor)
		{
			case 0:
				usuarios.removeDaTabela(table, usuarios);
				break;
			case 1:
				livros.removeDaTabela(table, livros);
				break;
			case 2:
				monografias.removeDaTabela(table, monografias);
				break;
			case 3:
				periodicos.removeDaTabela(table, periodicos);
				break;
			case 4:
				revistas.removeDaTabela(table, revistas);
				break;
			default: 
				System.out.println("erro\n");	
		}
	}
	
	private void editarBotao(JTabbedPane abas)
	{
		int valor = abas.getSelectedIndex();
		switch(valor)
		{
			case 0:
				usuarios.editarTabela(table, usuarios);
				frmPesquisa.dispose();
				break;
			case 1:
				livros.editarTabela(table, livros);
				frmPesquisa.dispose();
//				livros.removeDaTabela(table, livros);
				break;
			case 2:
				monografias.editarTabela(table, monografias);
				frmPesquisa.dispose();
//				monografias.removeDaTabela(table, monografias);
				break;
			case 3:
				periodicos.editarTabela(table, periodicos);
				frmPesquisa.dispose();
//				periodicos.removeDaTabela(table, periodicos);
				break;
			case 4:
//				revistas.removeDaTabela(table, revistas);
				break;
			default: 
				System.out.println("erro\n");	
		}
	}
}
