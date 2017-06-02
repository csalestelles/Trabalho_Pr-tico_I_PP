package BibliotecaSIS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarMonografia {

	private static JFrame frame;
	
	private String titulo, autor, orientador, tema, tipo, instituicao, ano, exemplares;
	private int codigo;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	private MonografiaDAO monografias;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.getContentPane().setLayout(null);
//					EditarMonografia window = new EditarMonografia();
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
	public EditarMonografia(int codigo, String titulo, String autor, String orientador, String tema, String tipo,
			String instituicao, String ano, String exemplares) 
	{
		this.codigo = codigo;
		this.titulo = titulo;
		this.autor = autor;
		this.orientador = orientador;
		this.tema = tema;
		this.tipo = tipo;
		this.instituicao = instituicao;
		this.ano = ano;
		this.exemplares = exemplares;
		monografias = new MonografiaDAO();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(21, 51, 368, 26);
		textField.setText(titulo);
		frame.getContentPane().add(textField);
		
		JLabel label = new JLabel("Título:");
		label.setBounds(21, 34, 93, 16);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Autor:");
		label_1.setBounds(21, 80, 61, 16);
		frame.getContentPane().add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(21, 98, 230, 26);
		textField_1.setText(autor);
		frame.getContentPane().add(textField_1);
		
		JLabel label_2 = new JLabel("Orientador:");
		label_2.setBounds(21, 133, 93, 16);
		frame.getContentPane().add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(21, 156, 168, 26);
		textField_2.setText(orientador);
		frame.getContentPane().add(textField_2);
		
		
		String[] anoVetor = {"",
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

		JComboBox comboBox = new JComboBox(anoVetor);
		comboBox.setBounds(201, 157, 93, 27);
		comboBox.setSelectedItem(ano);
		frame.getContentPane().add(comboBox);
		
		JLabel label_3 = new JLabel("Ano:");
		label_3.setBounds(201, 136, 61, 16);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Tipo:");
		label_4.setBounds(263, 80, 61, 16);
		frame.getContentPane().add(label_4);
		
		String[] tipoVetor = {"", "Bacharelado", "Licenciatura", "Dissertação", "Tese"};
		JComboBox comboBox_1 = new JComboBox(tipoVetor);
		comboBox_1.setBounds(263, 99, 168, 27);
		comboBox_1.setSelectedItem(tipo);
		frame.getContentPane().add(comboBox_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(114, 189, 317, 26);
		textField_3.setText(instituicao);
		frame.getContentPane().add(textField_3);
		
		JLabel label_5 = new JLabel("Instituição:");
		label_5.setBounds(21, 194, 81, 16);
		frame.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("Tema:");
		label_6.setBounds(21, 222, 61, 16);
		frame.getContentPane().add(label_6);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(21, 243, 230, 26);
		textField_4.setText(tema);
		frame.getContentPane().add(textField_4);
		
		String[] exemplaresVetor = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
				   "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
				   "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
				   "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
				   "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"};
		
		JComboBox comboBox_2 = new JComboBox(exemplaresVetor);
		comboBox_2.setBounds(314, 157, 93, 27);
		frame.getContentPane().add(comboBox_2);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				monografias.monografia.setCodigo(codigo);
				monografias.monografia.setTitulo(textField.getText());
				monografias.monografia.setAutor(textField_1.getText());
				monografias.monografia.setOrientador(textField_2.getText());
				monografias.monografia.setTema(textField_4.getText());
				monografias.monografia.setTipo((String) comboBox_1.getSelectedItem());
				monografias.monografia.setInstituicao(textField_3.getText());
				monografias.monografia.setAno((String) comboBox.getSelectedItem());
				monografias.monografia.setExemplares((String) comboBox_2.getSelectedItem());
				JOptionPane.showMessageDialog(null, monografias.atualizar(BancoDeDados.ALTERACAO));
				frame.dispose();
				AdminWindow.main(null);
			}
		});
		btnAtualizar.setBounds(314, 243, 117, 29);
		frame.getContentPane().add(btnAtualizar);
		
		JLabel lblExemplares = new JLabel("Exemplares:");
		lblExemplares.setBounds(314, 133, 93, 16);
		frame.getContentPane().add(lblExemplares);
	}
}
