package BibliotecaSIS;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Emprestimo {

	private static JFrame frame;
	Date data;
	LivroDAO livros;
	MonografiaDAO documentos;
	PeriodicoDAO periodicos;
	RevistaDAO revistas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					Emprestimo window = new Emprestimo();
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
	public Emprestimo(String nome, String segundaInfo, String terceiraInfo, int infoDoc, int codigo, int codigoUser) {
		data = new Date();
		initialize(nome, segundaInfo, terceiraInfo, infoDoc, codigo, codigoUser);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String nome, String segundaInfo, String terceiraInfo, int infoDoc, int codigo, int codigoUser) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblVocEstEmprestando = new JLabel("Você está emprestando:");
		lblVocEstEmprestando.setBounds(29, 26, 150, 16);
		frame.getContentPane().add(lblVocEstEmprestando);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setText(nome);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 53, 438, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setText(segundaInfo+terceiraInfo);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(6, 81, 438, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblPeloPerodo = new JLabel("Data de devolução:");
		lblPeloPerodo.setBounds(29, 132, 120, 16);
		frame.getContentPane().add(lblPeloPerodo);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		data.setDate(data.getDate()+7);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		lblNewLabel_2.setText(dateFormat.format(data));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(6, 159, 438, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnEmprestar = new JButton("Emprestar");
		btnEmprestar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (infoDoc == 0)
				{
					livros = new LivroDAO();
					livros.livro.setCodigo(codigo);
					if(livros.localizar())
					{
						if(Integer.parseInt(livros.livro.getExemplares()) == 0)
						{
							JOptionPane.showMessageDialog(null, "Exemplar Indisponível!");
						}
						else
						{
							JOptionPane.showMessageDialog(null, livros.emprestar(codigoUser));
							
							frame.dispose();
							UsuarioWindow.main(null);
						}
						
					}
				}
				else if (infoDoc == 1)
				{
					documentos = new MonografiaDAO();
					documentos.monografia.setCodigo(codigo);
					if(documentos.localizar())
					{
						if(Integer.parseInt(documentos.monografia.getExemplares()) == 0)
						{
							JOptionPane.showMessageDialog(null, "Exemplar Indisponível!");
						}
						else
						{
							JOptionPane.showMessageDialog(null, documentos.emprestar(codigoUser));
							
							frame.dispose();
							UsuarioWindow.main(null);
						}
					}
				}
				else if (infoDoc == 2)
				{
					periodicos = new PeriodicoDAO();
					periodicos.periodico.setCodigo(codigo);
					if(periodicos.localizar())
					{
						if(Integer.parseInt(periodicos.periodico.getExemplares()) == 0)
						{
							JOptionPane.showMessageDialog(null, "Exemplar Indisponível!");
						}
						else
						{
							JOptionPane.showMessageDialog(null, periodicos.emprestar(codigoUser));
							
							frame.dispose();
							UsuarioWindow.main(null);
						}
					}
				}
				else if (infoDoc == 3)
				{
					revistas = new RevistaDAO();
					revistas.revista.setCodigo(codigo);
					if(revistas.localizar())
					{
						if(Integer.parseInt(revistas.revista.getExemplares()) == 0)
						{
							JOptionPane.showMessageDialog(null, "Exemplar Indisponível!");
						}
						else
						{
							JOptionPane.showMessageDialog(null, revistas.emprestar(codigoUser));
							
							frame.dispose();
							UsuarioWindow.main(null);
						}
					}
				}
			}
		});
		btnEmprestar.setBounds(167, 187, 117, 29);
		frame.getContentPane().add(btnEmprestar);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnMais = new JMenu("Mais");
		menuBar.add(mnMais);
		
		JMenuItem mntmVoltar = new JMenuItem("Voltar");
		mnMais.add(mntmVoltar);
		mntmVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				UsuarioWindow.main(null);
				frame.dispose();
			}
		});
		
	}
}
