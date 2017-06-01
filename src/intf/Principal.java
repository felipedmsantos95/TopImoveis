package intf;

import java.awt.EventQueue;

import javax.swing.JFrame;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal {

	private JFrame TopImoveis;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.TopImoveis.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		TopImoveis = new JFrame();
		TopImoveis.setResizable(false);
		TopImoveis.setTitle("TopImóveis v1.0");
		TopImoveis.setBounds(100, 100, 450, 300);
		TopImoveis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TopImoveis.getContentPane().setLayout(null);
		
		JLabel lblTopimveis = new JLabel("TopImóveis");
		lblTopimveis.setFont(new Font("DialogInput", Font.BOLD, 24));
		lblTopimveis.setBounds(151, 12, 174, 37);
		TopImoveis.getContentPane().add(lblTopimveis);
		
		JLabel imagem = new JLabel("");
		imagem.setBounds(284, 99, 96, 91);
		//Adicionando Imagem ao projeto
		ImageIcon foto = new ImageIcon(Principal.class.getResource("/img/top64.png"));
		Image imag = foto.getImage().getScaledInstance(imagem.getWidth(), imagem.getHeight(), Image.SCALE_SMOOTH);
		imagem.setIcon(new ImageIcon(imag));
		//Fim adicionar imagem
		TopImoveis.getContentPane().add(imagem);
		
		JButton btnOpesCliente = new JButton("Opções Cliente");
		btnOpesCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteMain cliente = new ClienteMain();
				cliente.setVisible(true);
			}
		});
		btnOpesCliente.setBounds(25, 89, 174, 31);
		TopImoveis.getContentPane().add(btnOpesCliente);
		
		JButton btnImveis = new JButton("Imóveis");
		btnImveis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImovelMain imovel = new ImovelMain();
				imovel.setVisible(true);
			}
		});
		btnImveis.setBounds(25, 132, 174, 31);
		TopImoveis.getContentPane().add(btnImveis);
		
		JButton btnSobre = new JButton("Sobre");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
					
				sobre.setVisible(true);
				
			}
		});
		btnSobre.setBounds(25, 175, 174, 31);
		TopImoveis.getContentPane().add(btnSobre);
		
		JLabel lblAluguelDeImvel = new JLabel("Aluguel de Imóvel Top é aqui!");
		lblAluguelDeImvel.setBounds(118, 49, 207, 15);
		TopImoveis.getContentPane().add(lblAluguelDeImvel);
	}
}
