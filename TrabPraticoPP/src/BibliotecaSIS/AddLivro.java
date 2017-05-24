package BibliotecaSIS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.DropMode;
import javax.swing.SwingConstants;

import java.sql.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddLivro extends Livro {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JFormattedTextField edicaoField;
	private Livro livro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddLivro window = new AddLivro();
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
	public AddLivro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Adicionar livro na Biblioteca");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Título:");
		lblNewLabel.setBounds(6, 6, 93, 16);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(6, 34, 368, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Autor:");
		lblNewLabel_1.setBounds(6, 72, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(6, 100, 230, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEditora = new JLabel("Editora:");
		lblEditora.setBounds(6, 138, 61, 16);
		frame.getContentPane().add(lblEditora);
		
		textField_2 = new JTextField();
		textField_2.setBounds(6, 166, 230, 26);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		String[] idioma = {"","Espanhol", "Francês", "Inglês", "Italiano", "Português-BR", "Português"}; 
		JComboBox comboBox = new JComboBox(idioma);
		comboBox.setBounds(248, 101, 168, 27);
		frame.getContentPane().add(comboBox);
		
		JLabel lblIdioma = new JLabel("Idioma:");
		lblIdioma.setBounds(248, 72, 61, 16);
		frame.getContentPane().add(lblIdioma);
		
		JLabel lblAno = new JLabel("Ano:");
		lblAno.setBounds(248, 138, 61, 16);
		frame.getContentPane().add(lblAno);
		
		String[] ano = {"",
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
		JComboBox comboBox_1 = new JComboBox(ano);
		comboBox_1.setBounds(248, 167, 93, 27);
		frame.getContentPane().add(comboBox_1);
		
		JLabel lblEdio = new JLabel("Edição:");
		lblEdio.setBounds(350, 138, 61, 16);
		frame.getContentPane().add(lblEdio);
		
		try    //FORMATA O TEXTFIELD PARA SOMENTE NUMEROS
		{
			 javax.swing.text.MaskFormatter edicao = new javax.swing.text.MaskFormatter("###");

			 edicaoField = new javax.swing.JFormattedTextField(edicao);
			 edicaoField.setHorizontalAlignment(SwingConstants.CENTER);
			 edicaoField.setBounds(353, 166, 63, 26);
		   	 frame.getContentPane().add(edicaoField);
			 edicaoField.setColumns(10);
		}catch(Exception e){}
		
		
		JLabel lblTotalDeExxemplares = new JLabel("Total de exemplares:");
		lblTotalDeExxemplares.setBounds(6, 209, 139, 16);
		frame.getContentPane().add(lblTotalDeExxemplares);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setBounds(159, 204, 77, 26);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					int edicao = Integer.parseInt(edicaoField.getText());
					int ano = Integer.parseInt(comboBox_1.getSelectedItem().toString());
					int numExemplares = Integer.parseInt(textField_4.getText());
					livro.setLivro(textField.getText(), textField_2.getText(), textField_1.getText(), 
							comboBox_1.getSelectedItem().toString(), ano,
							edicao, numExemplares);
					Statement st =conexao.createStatement();
					ResultSet rs = st.executeQuery("SELECT * FROM Livros WHERE Titulo='" + textField.getText() 
													+ "' AND Edicao='" + textField_3.getText() + "';");
					livro.setCodigo(rs.getInt(1));
					if(!rs.first()) //SE NÃO HOUVER O LIVRO NO BD, ADICIONAR
					{
						
						livro.atualizar(INCLUSAO);
					}
					else
					{
						int numeroDeExemplaresAdicionados = Integer.parseInt(edicaoField.getText()) + rs.getInt(8);
						livro.setNumExemplaresDisponiveis(numeroDeExemplaresAdicionados);
						livro.atualizar(ALTERACAO);
						
					}
				}
				catch(Exception f){f.printStackTrace();}
			}
		});
		btnAdicionar.setBounds(299, 204, 117, 29);
		frame.getContentPane().add(btnAdicionar);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnMais = new JMenu("Mais");
		menuBar.add(mnMais);
		
		JMenuItem mntmVoltar = new JMenuItem("Cancelar");
		mnMais.add(mntmVoltar);
		mntmVoltar.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
				AdminWindow adminWindow = new AdminWindow();
				adminWindow.main(null);
			}
		});
	}
}
