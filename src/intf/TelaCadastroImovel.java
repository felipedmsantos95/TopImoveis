/**
 * Classe Tela Cadastro Imóvel - Tela que pega infomações digitadas na interface relacionadas ao imóvel e realiza o cadastro no banco 
 * @author Felipe Santos &lt; felipedmsantos95@gmail.com&gt;
 * @version 1.0, 2017-06-24
 */

package intf;
import bd.*;
import java.util.*;
import java.text.*;
import obj.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;

public class TelaCadastroImovel extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtreaTotal;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public TelaCadastroImovel() {
		setBounds(100, 100, 450, 300);
		this.setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCadastrarImvel = new JLabel("Cadastrar Imóvel");
		lblCadastrarImvel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCadastrarImvel.setBounds(134, 12, 151, 15);
		contentPanel.add(lblCadastrarImvel);
		
		textField = new JTextField();
		textField.setBounds(12, 59, 424, 19);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblEndereo = new JLabel("Endereço:");
		lblEndereo.setBounds(12, 40, 88, 15);
		contentPanel.add(lblEndereo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Casa Padrão", "Casa Condomínio", "Apartamento", "Kitchenette"}));
		comboBox.setBounds(12, 96, 151, 19);
		contentPanel.add(comboBox);
		
		JLabel lblCategoriaDoImvel = new JLabel("Categoria do Imóvel:");
		lblCategoriaDoImvel.setBounds(12, 78, 151, 15);
		contentPanel.add(lblCategoriaDoImvel);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner.setBounds(381, 96, 28, 20);
		contentPanel.add(spinner);
		
		JLabel lblQtdQuartos = new JLabel("Qtd Quartos:");
		lblQtdQuartos.setBounds(348, 78, 100, 15);
		contentPanel.add(lblQtdQuartos);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner_1.setBounds(381, 147, 28, 20);
		contentPanel.add(spinner_1);
		
		JLabel lblQtdSutes = new JLabel("Qtd Suítes:");
		lblQtdSutes.setBounds(360, 128, 88, 15);
		contentPanel.add(lblQtdSutes);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner_2.setBounds(82, 134, 28, 20);
		contentPanel.add(spinner_2);
		
		JLabel lblQtdVagasDe = new JLabel("Qtd Vagas de Garagem:");
		lblQtdVagasDe.setBounds(12, 119, 184, 15);
		contentPanel.add(lblQtdVagasDe);
		
		textField_1 = new JTextField();
		textField_1.setBounds(204, 96, 114, 19);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCusto = new JLabel("Custo do Aluguel:");
		lblCusto.setBounds(204, 78, 132, 15);
		contentPanel.add(lblCusto);
		
		JLabel lblAdicionais = new JLabel("Adicionais:");
		lblAdicionais.setBounds(12, 162, 88, 15);
		contentPanel.add(lblAdicionais);
		
		JCheckBox chckbxPiscina = new JCheckBox("Piscina");
		chckbxPiscina.setBounds(12, 176, 129, 23);
		contentPanel.add(chckbxPiscina);
		
		JCheckBox chckbxChurrasqueira = new JCheckBox("Churrasqueira");
		chckbxChurrasqueira.setBounds(12, 196, 129, 23);
		contentPanel.add(chckbxChurrasqueira);
		
		JCheckBox chckbxPlayground = new JCheckBox("Playground");
		chckbxPlayground.setBounds(12, 215, 129, 23);
		contentPanel.add(chckbxPlayground);
		
		txtreaTotal = new JTextField();
		txtreaTotal.setBounds(204, 147, 114, 19);
		contentPanel.add(txtreaTotal);
		txtreaTotal.setColumns(10);
		
		JLabel lblreaTotal = new JLabel("Área Total:");
		lblreaTotal.setBounds(208, 128, 100, 15);
		contentPanel.add(lblreaTotal);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Locale pt = new Locale("pt", "PT");
						NumberFormat nf = NumberFormat.getInstance(pt);
						
						try {
							Imovel imovel = new Imovel((comboBox.getSelectedIndex() + 1), textField.getText(), nf.parse(txtreaTotal.getText()).floatValue(), (Integer)spinner.getValue(), (Integer)spinner_1.getValue(), (Integer)spinner_2.getValue(), nf.parse(textField_1.getText()).floatValue());
							ImovelDAO im = new ImovelDAO();
							int flag = 0;
							if(chckbxPiscina.isSelected()) 
							{
								imovel.addAdicionais(1);
								flag = 1;
							}
							if(chckbxChurrasqueira.isSelected())
							{
								imovel.addAdicionais(2);
								flag = 1;
							}
							if(chckbxPlayground.isSelected())
							{
								imovel.addAdicionais(3);
								flag = 1;
							}							 
							if(im.adicionarImovel(imovel)) JOptionPane.showMessageDialog(null,"Imóvel cadastrado com sucesso!");
							if(flag == 1) im.CadastrarAdicionaisBanco(imovel);							
							TelaCadastroImovel.this.dispose();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TelaCadastroImovel.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
