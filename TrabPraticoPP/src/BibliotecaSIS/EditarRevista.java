package BibliotecaSIS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarRevista {

	private static JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	
	private RevistaDAO revistas;
	private String nome, editora, tema, edicao, ano, exemplares;
	private int codigo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					EditarRevista window = new EditarRevista();
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
	public EditarRevista(int codigo, String nome, String editora, String tema, String edicao, String ano, String exemplares)
	{
		this.codigo = codigo;
		this.nome = nome;
		this.editora = editora;
		this.tema = tema;
		this.edicao = edicao;
		this.ano = ano;
		this.exemplares = exemplares;
		
		revistas = new RevistaDAO();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Editar Revista");
		frame.setBounds(100, 100, 450, 280);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Nome:");
		label.setBounds(43, 6, 61, 16);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setText(nome);
		textField.setBounds(43, 34, 248, 26);
		
		frame.getContentPane().add(textField);
		
		JLabel label_1 = new JLabel("Editora:");
		label_1.setBounds(43, 72, 61, 16);
		frame.getContentPane().add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setText(editora);
		textField_1.setBounds(43, 100, 154, 26);
		frame.getContentPane().add(textField_1);
		
		String[] temaVetor = {"", "Científica", "Culinária", "Curiosidades", "Educativa", 
				 "Esportes", "Financeira", "Fofoca", 
			 	 "Humor", "Política"};
		JComboBox comboBox = new JComboBox(temaVetor);
		comboBox.setBounds(249, 101, 154, 27);
		comboBox.setSelectedItem(tema);
		frame.getContentPane().add(comboBox);
		
		JLabel label_2 = new JLabel("Tema:");
		label_2.setBounds(249, 72, 61, 16);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Edição:");
		label_3.setBounds(317, 6, 61, 16);
		frame.getContentPane().add(label_3);
		
		String[] edicaoVetor = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
				   "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
				   "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
				   "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
				   "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"};
		JComboBox comboBox_1 = new JComboBox(edicaoVetor);
		comboBox_1.setSelectedItem(edicao);
		comboBox_1.setBounds(317, 34, 86, 26);
		frame.getContentPane().add(comboBox_1);
		
		JLabel label_4 = new JLabel("Ano:");
		label_4.setBounds(43, 138, 61, 16);
		frame.getContentPane().add(label_4);
		
		String[] anoVetor = {"",
		        "1921", "1922", "1923", "1924", "1925", "1926", "1927", "1928", "1929", "1930",
		        "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939", "1940",
		        "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950",
		        "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960",
		        "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970",
		        "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980",
		        "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990",
		        "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000",
		        "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010",
		        "2011", "2012", "2013", "2014", "2015", "2016", "2017"};
		JComboBox comboBox_2 = new JComboBox(anoVetor);
		comboBox_2.setBounds(43, 167, 94, 27);
		frame.getContentPane().add(comboBox_2);
		
		JLabel label_5 = new JLabel("Total de exemplares:");
		label_5.setBounds(156, 138, 154, 16);
		frame.getContentPane().add(label_5);
		
		String[] exemplaresVetor = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
				   "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
				   "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
				   "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
				   "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"};
		JComboBox comboBox_3 = new JComboBox(exemplaresVetor);
		comboBox_3.setSelectedItem(exemplares);
		comboBox_3.setBounds(156, 166, 94, 26);
		frame.getContentPane().add(comboBox_3);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				revistas.revista.setCodigo(codigo);
				revistas.revista.setNome(textField.getText());
				revistas.revista.setEditora(textField_1.getText());
				revistas.revista.setTema(comboBox.getSelectedItem().toString());
				revistas.revista.setEdicao(comboBox_1.getSelectedItem().toString());
				revistas.revista.setAno(comboBox_2.getSelectedItem().toString());
				revistas.revista.setExemplares(comboBox_3.getSelectedItem().toString());
				JOptionPane.showMessageDialog(null, revistas.atualizar(BancoDeDados.ALTERACAO));
				frame.dispose();
				AdminWindow.main(null);
			}
		});
		btnAtualizar.setBounds(286, 166, 117, 29);
		frame.getContentPane().add(btnAtualizar);
		
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
