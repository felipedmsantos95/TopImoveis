/**
 * Classe Tela Alterar Imóvel - Tela que possibilita alterações nos dados de imóveis cadastrados
 * @author Felipe Santos &lt; felipedmsantos95@gmail.com&gt;
 * @version 1.0, 2017-06-24
 */

package intf;

import java.sql.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bd.ImovelDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class TelaAlterarImovel extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtCusto;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 * @throws ParseException 
	 */
	public TelaAlterarImovel() throws ParseException {
		setBounds(100, 100, 450, 300);
		this.setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAtualizarCadastroDe = new JLabel("Atualizar Cadastro de Imóvel");
		lblAtualizarCadastroDe.setBounds(111, 12, 219, 15);
		contentPanel.add(lblAtualizarCadastroDe);
		
		//Alterando para comboBox
		
		JComboBox txtid = new JComboBox();
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conecta = DriverManager.getConnection("jdbc:mysql://localhost:3306/TP1_BD", "tp1", "senha");
			
			Statement st = conecta.createStatement();
			ResultSet rs = st.executeQuery("SELECT id FROM imovel");
			while(rs.next())
			{				
				txtid.addItem(rs.getInt(1));
			}
			conecta.close();
		}		
		catch(SQLException e)
		{
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		txtid.setBounds(22, 40, 57, 19);
		contentPanel.add(txtid);
		
		
		//Salvar ID onde vão ser feitas as alterações
		Locale pt = new Locale("pt", "PT");
		NumberFormat nf = NumberFormat.getInstance(pt);
		ImovelDAO atualiza = new ImovelDAO();
		
		
		JLabel lblIdImvel = new JLabel("ID Imóvel:");
		lblIdImvel.setBounds(22, 24, 70, 15);
		contentPanel.add(lblIdImvel);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							
				try {
					int id = nf.parse(txtid.getSelectedItem().toString()).intValue();
					if(atualiza.alterarCusto(id, nf.parse(txtCusto.getText()).floatValue())) JOptionPane.showMessageDialog(null,"Custo do Aluguel Atualizado!");;
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 8));
		btnNewButton.setBounds(139, 81, 47, 19);
		contentPanel.add(btnNewButton);
		
		JButton button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = nf.parse(txtid.getSelectedItem().toString()).intValue();
					if(atualiza.alterarAreaTotal(id, nf.parse(textField.getText()).floatValue())) JOptionPane.showMessageDialog(null,"Área do Imóvel Alterada!");;
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setFont(new Font("Dialog", Font.BOLD, 8));
		button.setBounds(139, 117, 47, 19);
		contentPanel.add(button);
		
		txtCusto = new JTextField();
		txtCusto.setBounds(22, 82, 114, 19);
		contentPanel.add(txtCusto);
		txtCusto.setColumns(10);
		
		JLabel lblCusto = new JLabel("Novo Custo:");
		lblCusto.setBounds(22, 68, 99, 15);
		contentPanel.add(lblCusto);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(22, 117, 114, 19);
		contentPanel.add(textField);
		
		JLabel lblNovareaTotal = new JLabel("Nova Área Total:");
		lblNovareaTotal.setBounds(22, 103, 132, 15);
		contentPanel.add(lblNovareaTotal);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner.setBounds(257, 82, 28, 20);
		contentPanel.add(spinner);
		
		JLabel lblNovaQtdQuartos = new JLabel("Nova Qtd Quartos:");
		lblNovaQtdQuartos.setBounds(242, 68, 132, 15);
		contentPanel.add(lblNovaQtdQuartos);
		
		JButton button_1 = new JButton("OK");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int id = nf.parse(txtid.getSelectedItem().toString()).intValue();
					if(atualiza.alterarQtdQuartos(id, (Integer)spinner.getValue())) JOptionPane.showMessageDialog(null,"Quantidade de Quartos Alterada!");;
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setFont(new Font("Dialog", Font.BOLD, 8));
		button_1.setBounds(290, 83, 47, 19);
		contentPanel.add(button_1);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner_1.setBounds(257, 131, 28, 20);
		contentPanel.add(spinner_1);
		
		JLabel lblNovaQtdSutes = new JLabel("Nova Qtd Suítes:");
		lblNovaQtdSutes.setBounds(242, 117, 132, 15);
		contentPanel.add(lblNovaQtdSutes);
		
		JButton button_2 = new JButton("OK");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = nf.parse(txtid.getSelectedItem().toString()).intValue();
					if(atualiza.alterarQtdSuites(id, (Integer)spinner_1.getValue())) JOptionPane.showMessageDialog(null,"Quantidade de Suítes Alterada!");;
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_2.setFont(new Font("Dialog", Font.BOLD, 8));
		button_2.setBounds(290, 132, 47, 19);
		contentPanel.add(button_2);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner_2.setBounds(257, 177, 28, 20);
		contentPanel.add(spinner_2);
		
		JLabel lblNovaQtdVagas = new JLabel("Nova Qtd Vagas:");
		lblNovaQtdVagas.setBounds(242, 163, 132, 15);
		contentPanel.add(lblNovaQtdVagas);
		
		JButton button_3 = new JButton("OK");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = nf.parse(txtid.getSelectedItem().toString()).intValue();
					if(atualiza.alterarQtdVagas(id, (Integer)spinner_2.getValue())) JOptionPane.showMessageDialog(null,"Quantidade de Vagas de Garagem Alterada!");;
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_3.setFont(new Font("Dialog", Font.BOLD, 8));
		button_3.setBounds(290, 178, 47, 19);
		contentPanel.add(button_3);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Voltar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TelaAlterarImovel.this.dispose();
					}
				});
				cancelButton.setActionCommand("Voltar");
				buttonPane.add(cancelButton);
			}
		}
	}
}
