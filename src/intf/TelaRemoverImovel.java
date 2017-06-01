package intf;
import bd.*;
import java.sql.*;

import obj.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;

public class TelaRemoverImovel extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaRemoverImovel dialog = new TelaRemoverImovel();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaRemoverImovel() {
		setBounds(100, 100, 450, 300);
		this.setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblRemoverImvel = new JLabel("Remover Imóvel");
		lblRemoverImvel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblRemoverImvel.setBounds(165, 12, 148, 31);
		contentPanel.add(lblRemoverImvel);
		{
			JLabel lblConsulteOId = new JLabel("Consulte o ID do imóvel que deseja remover");
			lblConsulteOId.setBounds(66, 42, 335, 47);
			contentPanel.add(lblConsulteOId);
		}
		{
			JLabel lblOIdPode = new JLabel("O ID pode ser encontrado em \"Relatório Cadastrados\"");
			lblOIdPode.setBounds(26, 66, 398, 50);
			contentPanel.add(lblOIdPode);
		}
		
		JComboBox id = new JComboBox();
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conecta = DriverManager.getConnection("jdbc:mysql://localhost:3306/TP1_BD", "tp1", "senha");
			
			Statement st = conecta.createStatement();
			ResultSet rs = st.executeQuery("SELECT id FROM imovel");
			while(rs.next())
			{				
				id.addItem(rs.getInt(1));
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
		id.setBounds(185, 116, 81, 19);
		contentPanel.add(id);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ImovelDAO remover = new ImovelDAO();
						Locale pt = new Locale("pt", "PT");
						NumberFormat nf = NumberFormat.getInstance(pt);
						
						try {
							if(remover.removerImovel(nf.parse(id.getSelectedItem().toString()).intValue())) JOptionPane.showMessageDialog(null,"Imovel removido com sucesso!");
							else JOptionPane.showMessageDialog(null,"O imóvel ainda está alugado!");
						} catch (HeadlessException | ParseException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null,"Valor inválido!");
							e1.printStackTrace();
						}
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
						TelaRemoverImovel.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancelar");
				buttonPane.add(cancelButton);
			}
		}
	}
}
