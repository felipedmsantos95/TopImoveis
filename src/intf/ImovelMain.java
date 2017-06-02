/**
 * Classe Imovel Main - Trata-se da tela com as principais opções dos imóveis
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

import bd.ImovelDAO;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class ImovelMain extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	
	

	/**
	 * Create the dialog.
	 */
	public ImovelMain() {
		setBounds(100, 100, 450, 300);
		//this.setModal(true);//Impedir que outras caixas de diálogo sejam abertas
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(319, 56, 77, 84);
		
		ImageIcon foto = new ImageIcon(Principal.class.getResource("/img/house64.png"));
		Image imag = foto.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(imag));
		
		contentPanel.add(label);
		
		JButton btnCadastrarImvel = new JButton("Cadastrar Imóvel");
		btnCadastrarImvel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroImovel cadastrar = new TelaCadastroImovel();
				
				cadastrar.setVisible(true);
			}
		});
		btnCadastrarImvel.setBounds(12, 56, 216, 25);
		contentPanel.add(btnCadastrarImvel);
		
		JButton btnRemoverImvel = new JButton("Remover Imóvel");
		btnRemoverImvel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaRemoverImovel remover = new TelaRemoverImovel();
				remover.setVisible(true);
			}
		});
		btnRemoverImvel.setBounds(12, 93, 216, 25);
		contentPanel.add(btnRemoverImvel);
		
		JButton btnAlterarDados = new JButton("Alterar Dados");
		btnAlterarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAlterarImovel altera = null;
				try {
					altera = new TelaAlterarImovel();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				altera.setVisible(true);
			}
		});
		btnAlterarDados.setBounds(12, 130, 216, 25);
		contentPanel.add(btnAlterarDados);
		
		JButton btnRelatrioDeCadastrados = new JButton("Relatório de Cadastrados");
		btnRelatrioDeCadastrados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImovelDAO imovelDAO = new ImovelDAO();
				
				
				RedirectedFrame outputFrame =
					     new RedirectedFrame(false, false, null, 700, 600, JFrame.DISPOSE_ON_CLOSE);
					outputFrame.setVisible(true);
					
				System.out.println("\t\tRelatório de Imóveis Cadastrados");
					
				imovelDAO.listarImoveis();
			}
		});
		btnRelatrioDeCadastrados.setBounds(12, 167, 216, 25);
		contentPanel.add(btnRelatrioDeCadastrados);
		
		JLabel lblControleDeImveis = new JLabel("Controle de Imóveis");
		lblControleDeImveis.setBounds(154, 12, 168, 15);
		contentPanel.add(lblControleDeImveis);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Voltar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ImovelMain.this.dispose();
					}
				});
				okButton.setActionCommand("Voltar");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
