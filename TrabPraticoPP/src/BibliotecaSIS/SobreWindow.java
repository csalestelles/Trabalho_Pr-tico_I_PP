package BibliotecaSIS;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

public class SobreWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SobreWindow window = new SobreWindow();
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
	public SobreWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Sobre");
		frame.setBounds(100, 100, 450, 200);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSobreOBibliotecasis = new JLabel("Sobre o BibliotecaSIS");
		lblSobreOBibliotecasis.setHorizontalAlignment(SwingConstants.CENTER);
		lblSobreOBibliotecasis.setBounds(6, 18, 438, 16);
		frame.getContentPane().add(lblSobreOBibliotecasis);
		
		JLabel lblDesenvolvidoPor = new JLabel("Desenvolvido por:");
		lblDesenvolvidoPor.setBounds(16, 46, 265, 16);
		frame.getContentPane().add(lblDesenvolvidoPor);
		
		JLabel lblCaioArthurMatrcula = new JLabel("Caio Arthur          21453444");
		lblCaioArthurMatrcula.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaioArthurMatrcula.setBounds(6, 74, 438, 16);
		frame.getContentPane().add(lblCaioArthurMatrcula);
		
		JLabel lblPrimeiroTrabalhoPrtico = new JLabel("Primeiro trabalho prático em Projeto de Programas");
		lblPrimeiroTrabalhoPrtico.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrimeiroTrabalhoPrtico.setBounds(16, 121, 428, 16);
		frame.getContentPane().add(lblPrimeiroTrabalhoPrtico);
		
		JLabel label = new JLabel("");
		label.setBounds(6, 137, 61, 16);
		frame.getContentPane().add(label);
	}

}
