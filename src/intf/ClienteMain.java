/**
 * Classe Cliente Main - Trata-se da tela com as principais opções dos clientes
 * @author Felipe Santos &lt; felipedmsantos95@gmail.com&gt;
 * @version 1.0, 2017-06-24
 */

package intf;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bd.ClienteDAO;
import bd.ImovelDAO;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClienteMain extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public ClienteMain() {
		setBounds(100, 100, 450, 300);
		//this.setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(183, 12, 70, 15);
		contentPanel.add(lblCliente);
		
		JLabel label = new JLabel("");		
		label.setBounds(312, 79, 85, 85);

		ImageIcon foto = new ImageIcon(Principal.class.getResource("/img/cliente64.png"));
		Image imag = foto.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(imag));
		contentPanel.add(label);
		
		JButton btnCadastrarCliente = new JButton("Cadastrar Cliente");
		btnCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroCliente cadastra = new TelaCadastroCliente();
				cadastra.setVisible(true);
			}
		});
		btnCadastrarCliente.setBounds(12, 58, 191, 25);
		contentPanel.add(btnCadastrarCliente);
		
		JButton btnRemoverCliente = new JButton("Remover Cliente");
		btnRemoverCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaRemoverCliente remover = new TelaRemoverCliente();
				remover.setVisible(true);
			}
		});
		btnRemoverCliente.setBounds(12, 95, 191, 25);
		contentPanel.add(btnRemoverCliente);
		
		JButton alugar = new JButton("Alugar Imóvel");
		alugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAlugarImovel imovel = new TelaAlugarImovel();
				imovel.setVisible(true);
			}
		});
		alugar.setBounds(12, 132, 191, 25);
		contentPanel.add(alugar);
		
		JButton btnRenovarContrato = new JButton("Renovar Contrato");
		btnRenovarContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaRenovar tela = new TelaRenovar();
				tela.setVisible(true);
			}
		});
		btnRenovarContrato.setBounds(12, 169, 191, 25);
		contentPanel.add(btnRenovarContrato);
		
		JButton btnRelatrioDeClientes = new JButton("Relatório de Clientes");
		btnRelatrioDeClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				ClienteDAO clienteDAO = new ClienteDAO();
				
				
				RedirectedFrame outputFrame =
					     new RedirectedFrame(false, false, null, 700, 600, JFrame.DISPOSE_ON_CLOSE);
					outputFrame.setVisible(true);
				System.out.println("--Relatório de Clientes\n");	
				clienteDAO.relatorioGeral();
			}
		});
		btnRelatrioDeClientes.setBounds(12, 206, 191, 25);
		contentPanel.add(btnRelatrioDeClientes);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Voltar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ClienteMain.this.dispose();
					}
				});
				cancelButton.setActionCommand("Voltar");
				buttonPane.add(cancelButton);
			}
		}
	}
}
