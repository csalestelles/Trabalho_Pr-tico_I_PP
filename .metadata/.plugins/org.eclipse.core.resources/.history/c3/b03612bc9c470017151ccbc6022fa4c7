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
import java.awt.event.ActionEvent;

public class AddRevista {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JFormattedTextField edicaoField, numExemplaresField;
	private RevistaDAO revistas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddRevista window = new AddRevista();
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
	public AddRevista() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		revistas = new RevistaDAO();
		
		frame = new JFrame("Adicionar Revista na Biblioteca");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(50, 6, 61, 16);
		frame.getContentPane().add(lblNome);
		
		textField = new JTextField();
		textField.setBounds(50, 34, 248, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEditora = new JLabel("Editora:");
		lblEditora.setBounds(50, 72, 61, 16);
		frame.getContentPane().add(lblEditora);
		
		textField_1 = new JTextField();
		textField_1.setBounds(50, 100, 154, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTema = new JLabel("Tema:");
		lblTema.setBounds(256, 72, 61, 16);
		frame.getContentPane().add(lblTema);
		
		String[] tema = {"", "Científica", "Culinária", "Curiosidades", "Educativa", 
							 "Esportes", "Financeira", "Fofoca", 
						 	 "Humor", "Política"};
		JComboBox comboBox = new JComboBox(tema);
		comboBox.setBounds(256, 101, 154, 27);
		frame.getContentPane().add(comboBox);
		
		JLabel lblEdio = new JLabel("Edição:");
		lblEdio.setBounds(324, 6, 61, 16);
		frame.getContentPane().add(lblEdio);
		
		String[] edicaoVetor = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
				   "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
				   "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
				   "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
				   "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"};
		JComboBox cbEdicao = new JComboBox(edicaoVetor);
		cbEdicao.setBounds(324, 34, 86, 26);
		frame.getContentPane().add(cbEdicao);
		
		JLabel lblAno = new JLabel("Ano:");
		lblAno.setBounds(50, 138, 61, 16);
		frame.getContentPane().add(lblAno);
		
		JLabel lblNmeroDeExemplares = new JLabel("Total de exemplares:");
		lblNmeroDeExemplares.setBounds(163, 138, 154, 16);
		frame.getContentPane().add(lblNmeroDeExemplares);
		
		String[] exemplaresVetor = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
				   "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
				   "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
				   "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
				   "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"};
		
		JComboBox cbExemplares = new JComboBox(exemplaresVetor);
		cbExemplares.setBounds(163, 166, 94, 26);
		frame.getContentPane().add(cbExemplares);
		
		String[] ano = {"",
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
		JComboBox comboBox_1 = new JComboBox(ano);
		comboBox_1.setBounds(50, 167, 94, 27);
		frame.getContentPane().add(comboBox_1);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					revistas.revista.setNome(textField.getText());
					revistas.revista.setEditora(textField_1.getText());
					revistas.revista.setTema(comboBox.getSelectedItem().toString());
					revistas.revista.setEdicao(cbEdicao.getSelectedItem().toString());
					revistas.revista.setAno(comboBox_1.getSelectedItem().toString());
					revistas.revista.setExemplares(cbExemplares.getSelectedItem().toString());
					
					JOptionPane.showMessageDialog(null, revistas.atualizar(BancoDeDados.INCLUSAO));
					frame.dispose();
					AdminWindow adminWindow = new AdminWindow();
					adminWindow.main(null);
				}
				catch(Exception d){};
				
			}
		});
		btnAdicionar.setBounds(293, 166, 117, 29);
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
