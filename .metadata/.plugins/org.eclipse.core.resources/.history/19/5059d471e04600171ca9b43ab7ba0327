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

public class EditarPeriodico {

	private static JFrame frame;
	
	private PeriodicoDAO periodicos;
	
	private String nome, periodo, editora, tema, ano, exemplar, exemplares;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					EditarPeriodico window = new EditarPeriodico();
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
	public EditarPeriodico(String nome, String periodo, String editora, String tema, String ano, String exemplar, String exemplares)
	{
		this.nome = nome;
		this.periodo = periodo;
		this.editora = editora;
		this.tema = tema;
		this.ano = ano;
		this.exemplar = exemplar;
		this.exemplares = exemplares;
		periodicos = new PeriodicoDAO();
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
		textField.setBounds(26, 60, 368, 26);
		textField.setText(nome);
		frame.getContentPane().add(textField);
		
		JLabel label = new JLabel("Nome:");
		label.setBounds(26, 43, 93, 16);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Editora:");
		label_1.setBounds(26, 98, 61, 16);
		frame.getContentPane().add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(26, 117, 230, 26);
		textField_1.setText(editora);
		frame.getContentPane().add(textField_1);
		
		JLabel label_2 = new JLabel("Tema:");
		label_2.setBounds(26, 155, 61, 16);
		frame.getContentPane().add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(26, 175, 230, 26);
		textField_2.setText(tema);
		frame.getContentPane().add(textField_2);
		
		JLabel label_3 = new JLabel("Quantidade:");
		label_3.setBounds(26, 218, 93, 16);
		frame.getContentPane().add(label_3);
		
		String[] exemplaresVetor = {"", " 1", " 2", " 3", " 4", " 5", " 6", " 7", " 8", " 9", 
				  "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
				  "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
				  "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
				  "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", 
				  "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", 
				  "60", "61", "62", "63", "64", "65", "66", "67", "68", "69",
				  "70", "71", "72", "73", "74", "75", "76", "77", "78", "79",
				  "80", "81", "82", "83", "84", "85", "86", "87", "88", "89",
				  "90", "91", "92", "93", "94", "95", "96", "97", "98", "99"};
		JComboBox comboBox = new JComboBox(exemplaresVetor);
		comboBox.setBounds(131, 213, 81, 26);
		comboBox.setSelectedItem(exemplares);
		frame.getContentPane().add(comboBox);
		
		String[] exemplarVetor = {"", " 1", " 2", " 3", " 4", " 5", " 6", " 7", " 8", " 9", 
				  "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
				  "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
				  "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
				  "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", 
				  "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", 
				  "60", "61", "62", "63", "64", "65", "66", "67", "68", "69",
				  "70", "71", "72", "73", "74", "75", "76", "77", "78", "79",
				  "80", "81", "82", "83", "84", "85", "86", "87", "88", "89",
				  "90", "91", "92", "93", "94", "95", "96", "97", "98", "99"};
		JComboBox comboBox_1 = new JComboBox(exemplarVetor);
		comboBox_1.setBounds(337, 191, 92, 26);
		comboBox_1.setSelectedItem(exemplar);
		frame.getContentPane().add(comboBox_1);
		
		JLabel label_4 = new JLabel("Exemplar:");
		label_4.setBounds(268, 196, 68, 16);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("Ano:");
		label_5.setBounds(268, 160, 61, 16);
		frame.getContentPane().add(label_5);
		
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
		JComboBox comboBox_2 = new JComboBox(anoVetor);
		comboBox_2.setBounds(337, 155, 92, 26);
		comboBox_2.setSelectedItem(ano);
		frame.getContentPane().add(comboBox_2);
		
		String[] periodoVetor = {"", "Anual", "Semestral", "trimestral", "Mensal", "Quinzenal"};
		JComboBox comboBox_3 = new JComboBox(periodoVetor);
		comboBox_3.setBounds(268, 118, 147, 27);
		comboBox_3.setSelectedItem(periodo);
		frame.getContentPane().add(comboBox_3);
		
		JLabel label_6 = new JLabel("Período:");
		label_6.setBounds(275, 98, 61, 16);
		frame.getContentPane().add(label_6);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				periodicos.periodico.setNome(textField.getText());
				periodicos.periodico.setPeriodo(comboBox_3.getSelectedItem().toString());
				periodicos.periodico.setEditora(textField_1.getText());
				periodicos.periodico.setTema(textField_2.getText());
				periodicos.periodico.setAno(comboBox_2.getSelectedItem().toString());
				periodicos.periodico.setExemplar(comboBox_1.getSelectedItem().toString());
				periodicos.periodico.setExemplares(comboBox.getSelectedItem().toString());
				JOptionPane.showMessageDialog(null, periodicos.atualizar(BancoDeDados.ALTERACAO));
				frame.dispose();
				AdminWindow.main(null);
			}
		});
		btnAtualizar.setBounds(268, 243, 117, 29);
		frame.getContentPane().add(btnAtualizar);
	}
}
