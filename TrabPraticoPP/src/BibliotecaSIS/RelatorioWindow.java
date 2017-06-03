package BibliotecaSIS;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class RelatorioWindow {

	private JFrame frame;

//	private RelatorioDAO relatorios;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RelatorioWindow window = new RelatorioWindow();
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
	public RelatorioWindow() {
		try 
		{
			RelatorioDAO.acessarRelatorio();
		} 
		catch (SQLException e) {e.printStackTrace();}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Relatório");
		frame.setBounds(100, 100, 450, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuários cadastrados no sistema:");
		lblNewLabel.setBounds(23, 34, 212, 24);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblAdministradoresCadastradosNo = new JLabel("Administradores cadastrados no sistema:");
		lblAdministradoresCadastradosNo.setBounds(23, 70, 261, 24);
		frame.getContentPane().add(lblAdministradoresCadastradosNo);
		
		JLabel lblEmprstimosRealizados = new JLabel("Empréstimos realizados:");
		lblEmprstimosRealizados.setBounds(23, 142, 212, 24);
		frame.getContentPane().add(lblEmprstimosRealizados);
		
		JLabel lblEmprstimosEmAndamento = new JLabel("Empréstimos em andamento:");
		lblEmprstimosEmAndamento.setBounds(23, 178, 183, 24);
		frame.getContentPane().add(lblEmprstimosEmAndamento);
		
		JLabel lblDevoluesAtrasadas = new JLabel("Devoluções atrasadas:");
		lblDevoluesAtrasadas.setBounds(23, 214, 141, 24);
		frame.getContentPane().add(lblDevoluesAtrasadas);
		
		JLabel lblUsers = new JLabel("New label");
		lblUsers.setText(""+RelatorioDAO.valores[0]);
		lblUsers.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsers.setBounds(337, 38, 86, 24);
		frame.getContentPane().add(lblUsers);
		
		JLabel lblAdmins = new JLabel("New label");
		lblAdmins.setText(""+(RelatorioDAO.valores[1]+1));
		lblAdmins.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdmins.setBounds(337, 74, 86, 24);
		frame.getContentPane().add(lblAdmins);
		
		JLabel lblEmpReal = new JLabel("New label");
		lblEmpReal.setText(""+RelatorioDAO.valores[2]);
		lblEmpReal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmpReal.setBounds(337, 146, 86, 24);
		frame.getContentPane().add(lblEmpReal);
		
		JLabel lblEmpreAnd = new JLabel("New label");
		lblEmpreAnd.setText(""+RelatorioDAO.valores[3]);
		lblEmpreAnd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmpreAnd.setBounds(337, 182, 86, 24);
		frame.getContentPane().add(lblEmpreAnd);
		
		JLabel lblDevAtras = new JLabel("New label");
		lblDevAtras.setText(""+RelatorioDAO.valores[4]);
		lblDevAtras.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDevAtras.setBounds(337, 214, 86, 24);
		frame.getContentPane().add(lblDevAtras);
		
		JLabel lblRelatrioDeAcessos = new JLabel("Relatório de acessos do Sistema");
		lblRelatrioDeAcessos.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelatrioDeAcessos.setBounds(124, 6, 202, 16);
		frame.getContentPane().add(lblRelatrioDeAcessos);
		
		JLabel lblExemplaresCadastrados = new JLabel("Exemplares cadastrados:");
		lblExemplaresCadastrados.setBounds(23, 106, 156, 24);
		frame.getContentPane().add(lblExemplaresCadastrados);
		
		JLabel lblExemplares = new JLabel("New label");
		lblExemplares.setText(""+RelatorioDAO.valores[5]);
		lblExemplares.setHorizontalAlignment(SwingConstants.RIGHT);
		lblExemplares.setBounds(337, 110, 86, 24);
		frame.getContentPane().add(lblExemplares);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnMais = new JMenu("Mais");
		menuBar.add(mnMais);
		
		JMenuItem mntmVoltar = new JMenuItem("Voltar");
		mnMais.add(mntmVoltar);
		mntmVoltar.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				AdminWindow.main(null);
				frame.dispose();
			}
		});
	}
}
