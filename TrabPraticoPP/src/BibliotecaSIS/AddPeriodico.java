package BibliotecaSIS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

import java.sql.*;

public class AddPeriodico 
{

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JFormattedTextField anoField, exemplarField, quantidadeField;
	
	private PeriodicoDAO periodicos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPeriodico window = new AddPeriodico();
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
	public AddPeriodico() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		periodicos = new PeriodicoDAO();
		
		frame = new JFrame("Adicionar Periodico na Biblioteca");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(6, 6, 93, 16);
		frame.getContentPane().add(lblNome);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(6, 23, 368, 26);
		frame.getContentPane().add(textField);
		
		JLabel lblEditora = new JLabel("Editora:");
		lblEditora.setBounds(6, 61, 61, 16);
		frame.getContentPane().add(lblEditora);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(6, 80, 230, 26);
		frame.getContentPane().add(textField_1);
		
		JLabel lblTema = new JLabel("Tema:");
		lblTema.setBounds(6, 118, 61, 16);
		frame.getContentPane().add(lblTema);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(6, 138, 230, 26);
		frame.getContentPane().add(textField_2);
		
		JLabel lblPerodo = new JLabel("Período:");
		lblPerodo.setBounds(255, 61, 61, 16);
		frame.getContentPane().add(lblPerodo);
		
		String[] periodo = {"", "Anual", "Semestral", "trimestral", "Mensal", "Quinzenal"};
		JComboBox comboBox = new JComboBox(periodo);
		comboBox.setBounds(248, 81, 147, 27);
		frame.getContentPane().add(comboBox);
		
		JLabel lblAno = new JLabel("Ano:");
		lblAno.setBounds(248, 123, 61, 16);
		frame.getContentPane().add(lblAno);
		
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
		JComboBox cbAno = new JComboBox(anoVetor);
		cbAno.setBounds(317, 118, 92, 26);
		frame.getContentPane().add(cbAno);
		
		JLabel lblExemplar = new JLabel("Exemplar:");
		lblExemplar.setBounds(248, 159, 68, 16);
		frame.getContentPane().add(lblExemplar);
		
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
		JComboBox cbExemplar = new JComboBox(exemplarVetor);
		cbExemplar.setBounds(317, 154, 92, 26);
		frame.getContentPane().add(cbExemplar);
		
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(6, 181, 93, 16);
		frame.getContentPane().add(lblQuantidade);
		
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
		JComboBox cbExemplares = new JComboBox(exemplaresVetor);
		cbExemplares.setBounds(111, 176, 93, 26);
		frame.getContentPane().add(cbExemplares);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					periodicos.periodico.setNome(textField.getText());
					periodicos.periodico.setPeriodo(comboBox.getSelectedItem().toString());
					periodicos.periodico.setEditora(textField_1.getText());
					periodicos.periodico.setTema(textField_2.getText());
					periodicos.periodico.setAno(cbAno.getSelectedItem().toString());
					periodicos.periodico.setExemplar(cbExemplar.getSelectedItem().toString());
					periodicos.periodico.setExemplares(cbExemplares.getSelectedItem().toString());
					JOptionPane.showMessageDialog(null, periodicos.atualizar(BancoDeDados.INCLUSAO));
					frame.dispose();
					AdminWindow adminWindow = new AdminWindow();
					adminWindow.main(null);
				}
				catch(Exception j){j.printStackTrace();}
				
			}
		});
		
		btnAdicionar.setBounds(248, 206, 117, 29);
		frame.getContentPane().add(btnAdicionar);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnMais = new JMenu("Mais");
		menuBar.add(mnMais);
		
		JMenuItem mntmCancelar = new JMenuItem("Cancelar");
		mnMais.add(mntmCancelar);
		mntmCancelar.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
				AdminWindow adminWindow = new AdminWindow();
				adminWindow.main(null);
			}
		});
	}

}
