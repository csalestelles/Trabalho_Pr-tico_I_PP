package BibliotecaSIS;

import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class EditarLivro {

	private static JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private LivroDAO livros;

	private String titulo, autor, editora, idioma, ano, edicao, exemplares;
	private int codigo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					EditarLivro window = new EditarLivro();
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
	public EditarLivro(int codigo, String titulo, String autor, String editora, String idioma, String ano, String edicao, String exemplares) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.autor = autor;
		this.editora = editora;
		this.idioma = idioma;
		this.ano = ano;
		this.edicao = edicao;
		this.exemplares = exemplares;
		livros = new LivroDAO();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Editar Livro");
		frame.setBounds(100, 100, 450, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Título:");
		label.setBounds(22, 6, 93, 16);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(22, 34, 368, 26);
		textField.setText(titulo);
		frame.getContentPane().add(textField);
		
		JLabel label_1 = new JLabel("Autor:");
		label_1.setBounds(22, 72, 61, 16);
		frame.getContentPane().add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(22, 100, 230, 26);
		textField_1.setText(autor);
		frame.getContentPane().add(textField_1);
		
		JLabel label_2 = new JLabel("Idioma:");
		label_2.setBounds(264, 72, 61, 16);
		frame.getContentPane().add(label_2);
		
		String[] idiomas = {"","Espanhol", "Francês", "Inglês", "Italiano", "Português-BR", "Português"}; 
		JComboBox comboBox = new JComboBox(idiomas);
		comboBox.setBounds(264, 101, 168, 27);
		comboBox.setSelectedItem(idioma);
		frame.getContentPane().add(comboBox);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(22, 166, 212, 26);
		textField_2.setText(editora);
		frame.getContentPane().add(textField_2);
		
		JLabel label_3 = new JLabel("Editora:");
		label_3.setBounds(22, 138, 61, 16);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Total de exemplares:");
		label_4.setBounds(22, 209, 139, 16);
		frame.getContentPane().add(label_4);

		String[] anos = {"",
		        "1901", "1902", "1903", "1904", "1905", "1906", "1907", "1908", "1909", "1910",
		        "1911", "1912", "1913", "1914", "1915", "1916", "1917", "1918", "1919", "1920",
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
		JComboBox comboBox_1 = new JComboBox(anos);
		comboBox_1.setBounds(246, 165, 93, 27);
		comboBox_1.setSelectedItem(ano);
		frame.getContentPane().add(comboBox_1);
		
		String[] exemplaresVetor = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
				   "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
				   "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
				   "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
				   "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"};
		JComboBox comboBox_2 = new JComboBox(exemplaresVetor);
		comboBox_2.setBounds(173, 205, 79, 27);
		comboBox_2.setSelectedItem(exemplares);
		frame.getContentPane().add(comboBox_2);
		
		String[] edicaoVetor = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
				   "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
				   "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
				   "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
				   "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"};
		JComboBox comboBox_3 = new JComboBox(edicaoVetor);
		comboBox_3.setBounds(349, 165, 83, 27);
		comboBox_3.setSelectedItem(edicao);
		frame.getContentPane().add(comboBox_3);
		
		JLabel label_5 = new JLabel("Ano:");
		label_5.setBounds(246, 164, 61, 16);
		frame.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("Edição:");
		label_6.setBounds(346, 164, 86, 16);
		frame.getContentPane().add(label_6);
	
		JButton button = new JButton("Atualizar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				livros.livro.setCodigo(codigo);
				livros.livro.setTitulo(textField.getText());
				livros.livro.setEditora(textField_2.getText());
				livros.livro.setAutor(textField_1.getText());
				livros.livro.setIdioma(comboBox.getSelectedItem().toString());
				livros.livro.setAno(comboBox_1.getSelectedItem().toString());
				livros.livro.setEdicao(comboBox_3.getSelectedItem().toString());
				livros.livro.setExemplares(comboBox_2.getSelectedItem().toString());
				JOptionPane.showMessageDialog(null, livros.atualizar(BancoDeDados.ALTERACAO));
				frame.dispose();
				AdminWindow.main(null);
			}
		});
		button.setBounds(315, 204, 117, 29);
		frame.getContentPane().add(button);
		
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
