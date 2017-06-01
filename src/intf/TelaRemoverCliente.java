package intf;
import obj.*;
import bd.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Locale;
import java.awt.event.ActionEvent;

public class TelaRemoverCliente extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
//	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaRemoverCliente dialog = new TelaRemoverCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaRemoverCliente() {
		setBounds(100, 100, 450, 300);
		this.setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblRemoverCliente = new JLabel("Remover Cliente");
		lblRemoverCliente.setBounds(161, 12, 139, 15);
		contentPanel.add(lblRemoverCliente);
		
		
		//Adicionando comboBox
		
		JComboBox nome = new JComboBox();
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conecta = DriverManager.getConnection("jdbc:mysql://localhost:3306/TP1_BD", "tp1", "senha");
			
			Statement st = conecta.createStatement();
			ResultSet rs = st.executeQuery("SELECT nome FROM cliente");
			while(rs.next())
			{				
				nome.addItem(rs.getString(1));
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
		nome.setBounds(44, 100, 346, 19);
		contentPanel.add(nome);
		//Fim do textfield
		
		/*textField = new JTextField();
		textField.setBounds(30, 124, 386, 19);
		contentPanel.add(textField);
		textField.setColumns(10);*/
		
		JLabel lblNome = new JLabel("Buscar Nome:");
		lblNome.setBounds(30, 75, 108, 15);
		contentPanel.add(lblNome);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ClienteDAO remover = new ClienteDAO();
									
						if(remover.removerCliente(nome.getSelectedItem().toString())) JOptionPane.showMessageDialog(null,"Cliente removido com sucesso!");
						else JOptionPane.showMessageDialog(null,"O cliente ainda tem um imóvel alugado!");
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Voltar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TelaRemoverCliente.this.dispose();
					}
				});
				cancelButton.setActionCommand("Voltar");
				buttonPane.add(cancelButton);
			}
		}
	}
}
