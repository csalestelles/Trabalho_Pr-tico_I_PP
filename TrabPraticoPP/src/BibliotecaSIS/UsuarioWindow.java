package BibliotecaSIS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class UsuarioWindow {

	private static JFrame frame;
	private JTabbedPane tpAbas;
	private JScrollPane livroScroll, monografiaScroll, periodicoScroll, revistaScroll;
	private JTable table;
	
	private LivroDAO livros = new LivroDAO();
	private MonografiaDAO monografias = new MonografiaDAO();
	private PeriodicoDAO periodicos = new PeriodicoDAO();
	private RevistaDAO revistas = new RevistaDAO();
	private JMenu mnArquivo;
	private JMenuItem mntmSair;

	private int codigoUser;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					UsuarioWindow window = new UsuarioWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UsuarioWindow(int user) {
		codigoUser = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 590);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tpAbas = new JTabbedPane();
		table = new JTable();
		livroScroll = new JScrollPane();
		monografiaScroll = new JScrollPane();
		periodicoScroll = new JScrollPane();
		revistaScroll = new JScrollPane();
		
		tpAbas.add("Livros", livroScroll);
		tpAbas.add("Monografias", monografiaScroll);
		tpAbas.add("Periódicos", periodicoScroll);
		tpAbas.add("Revistas", revistaScroll);
		tpAbas.setBounds(50, 50, 600, 490);
		frame.getContentPane().add(tpAbas);
		
		livroScroll.setViewportView(table);
		livros.showLivrosTable(table);
		
		JButton btnEmprestar = new JButton("Emprestar");
		btnEmprestar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{	
				emprestarBotao(tpAbas);
			}
		});
		btnEmprestar.setBounds(50, 9, 117, 29);
		frame.getContentPane().add(btnEmprestar);
		
		JButton btnDevolver = new JButton("Devolver");
		btnDevolver.setBounds(179, 9, 117, 29);
		frame.getContentPane().add(btnDevolver);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(308, 9, 117, 29);
		frame.getContentPane().add(btnPesquisar);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		mntmSair = new JMenuItem("Sair");
		mnArquivo.add(mntmSair);
		mntmSair.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				MainWindow.main(null);
				frame.dispose();
			}
		});;
		
		
		tpAbas.addChangeListener(new javax.swing.event.ChangeListener() {
		    public void stateChanged(javax.swing.event.ChangeEvent e) {
		        int aba = tpAbas.getSelectedIndex();
		        if (aba == 0)
	        	{
	        		livroScroll.setViewportView(table);
	        		livros.showLivrosTable(table);
	        	}
	        	else if (aba == 1)
	        	{
	        		monografiaScroll.setViewportView(table);
	        		monografias.showDocsTable(table);
	        	}
	        	else if (aba == 2)
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
		});
	}
	
	
	private void emprestarBotao(JTabbedPane abas)
	{
		int valor = abas.getSelectedIndex();
		int numeroLinhas = table.getRowCount();
		int contador = 0;
		while(contador < numeroLinhas)
		{
			if (table.isRowSelected(contador))
			{
				switch(valor)
				{
					case 0:
						Emprestimo emprestimoLivro = new Emprestimo(table.getValueAt(table.getSelectedRow(), 1).toString(),
													table.getValueAt(table.getSelectedRow(), 6).toString(), "a edição", 0, 
													(int)table.getValueAt(table.getSelectedRow(), 0), codigoUser);
						emprestimoLivro.main(null);
						frame.dispose();
						break;
					case 1:
						Emprestimo emprestimoDoc = new Emprestimo(table.getValueAt(table.getSelectedRow(), 1).toString(),
								"Autor: ", table.getValueAt(table.getSelectedRow(), 2).toString(), 1, 
								(int)table.getValueAt(table.getSelectedRow(), 0), codigoUser);
						emprestimoDoc.main(null);
						frame.dispose();
						break;
					case 2:
						Emprestimo emprestimoPeriodico = new Emprestimo(table.getValueAt(table.getSelectedRow(), 1).toString(),
								"Exemplar: ", table.getValueAt(table.getSelectedRow(), 6).toString(), 2, 
								(int)table.getValueAt(table.getSelectedRow(), 0), codigoUser);
						emprestimoPeriodico.main(null);
						frame.dispose();
						break;
					case 3:
//						periodicos.editarTabela(table, periodicos);
						frame.dispose();
//						periodicos.removeDaTabela(table, periodicos);
						break;
					default: 
						System.out.println("erro\n");	
				}
			}
			contador++;
		}
	}
}
