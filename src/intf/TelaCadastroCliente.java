/**
 * Classe Tela Cadastro Cliente - Tela que pega infomações digitadas na interface relacionadas ao cliente e realiza o cadastro no banco 
 * @author Felipe Santos &lt; felipedmsantos95@gmail.com&gt;
 * @version 1.0, 2017-06-24
 */

package intf;
import obj.*;
import bd.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastroCliente extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;

	


	/**
	 * Create the dialog.
	 */
	public TelaCadastroCliente() {
		setBounds(100, 100, 450, 300);
		this.setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCadastrarCliente = new JLabel("Cadastrar Cliente");
			lblCadastrarCliente.setBounds(157, 12, 138, 15);
			contentPanel.add(lblCadastrarCliente);
		}
		
		txtNome = new JTextField();
		txtNome.setBounds(12, 52, 412, 19);
		contentPanel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 32, 70, 15);
		contentPanel.add(lblNome);
		
		JCheckBox chckbxChaveExtra = new JCheckBox("Chave Extra");
		chckbxChaveExtra.setBounds(12, 144, 129, 23);
		contentPanel.add(chckbxChaveExtra);
		
		JCheckBox chckbxImvelMobiliado = new JCheckBox("Imóvel Mobiliado");
		chckbxImvelMobiliado.setBounds(12, 171, 155, 23);
		contentPanel.add(chckbxImvelMobiliado);
		
		JLabel lblOpcionais = new JLabel("Opcionais:");
		lblOpcionais.setBounds(12, 86, 94, 15);
		contentPanel.add(lblOpcionais);
		
		JCheckBox chckbxSeguro = new JCheckBox("Seguro");
		chckbxSeguro.setBounds(12, 117, 129, 23);
		contentPanel.add(chckbxSeguro);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Cliente cliente = new Cliente(txtNome.getText());
						ClienteDAO cli = new ClienteDAO();
						int flag = 0;
						
						if(chckbxSeguro.isSelected()) 
						{
							cliente.addOpcionais(1);
							flag = 1;
						}
						if(chckbxChaveExtra.isSelected())
						{
							cliente.addOpcionais(2);
							flag = 1;
						}
						if(chckbxImvelMobiliado.isSelected())
						{
							cliente.addOpcionais(3);
							flag = 1;
						}							 
						if(cli.cadastrarCliente(cliente)) JOptionPane.showMessageDialog(null,"Cliente cadastrado com sucesso!");
						if(flag == 1) cli.CadastrarOpcionaisBanco(cliente);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TelaCadastroCliente.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancelar");
				buttonPane.add(cancelButton);
			}
		}
	}
}
