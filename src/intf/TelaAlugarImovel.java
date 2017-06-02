/**
 * Classe Tela Alugar Imóvel - Tela que relaciona cliente com imóvel e realiza o processo de alugar
 * @author Felipe Santos &lt; felipedmsantos95@gmail.com&gt;
 * @version 1.0, 2017-06-24
 */

package intf;
import bd.*;
import obj.*;
import java.sql.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import com.toedter.calendar.JDateChooser;

public class TelaAlugarImovel extends JDialog {

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
	public TelaAlugarImovel() {
		setBounds(100, 100, 450, 300);
		this.setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAlugarImvel = new JLabel("Alugar Imóvel");
		lblAlugarImvel.setBounds(175, 12, 101, 31);
		//Adicionando o comboBox de id imovel
		JComboBox idImovel = new JComboBox();
		try
		{
			ImovelAlugadoDAO im = new ImovelAlugadoDAO();
			Class.forName("com.mysql.jdbc.Driver");
			Connection conecta = DriverManager.getConnection("jdbc:mysql://localhost:3306/TP1_BD", "tp1", "senha");
			
			Statement st = conecta.createStatement();
			ResultSet rs = st.executeQuery("SELECT id FROM imovel");
			while(rs.next())//Aqui apenas mostramos imoveis disponiveis
			{				
				if(im.verificaDisponibilidade(rs.getInt(1))) idImovel.addItem(rs.getInt(1));
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
		idImovel.setBounds(298, 100, 81, 19);
		contentPanel.add(idImovel);
		
		//Adicionando o comboBox de Nome de cliente
		
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
		nome.setBounds(44, 100, 196, 19);
		contentPanel.add(nome);
		
		
		
		contentPanel.add(lblAlugarImvel);
		
		JLabel lblSelecioneONome = new JLabel("Nome do cliente cadastrado:");
		lblSelecioneONome.setBounds(43, 79, 219, 19);
		contentPanel.add(lblSelecioneONome);
		
		JLabel lblIdDoImvel = new JLabel("ID do Imóvel:");
		lblIdDoImvel.setBounds(295, 79, 101, 19);
		contentPanel.add(lblIdDoImvel);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(44, 153, 121, 19);
		contentPanel.add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(258, 153, 121, 19);
		contentPanel.add(dateChooser_1);
		
		JLabel lblDataDeIncio = new JLabel("Início do Contrato:");
		lblDataDeIncio.setBounds(38, 138, 142, 15);
		contentPanel.add(lblDataDeIncio);
		
		JLabel lblTrminoDoContrato = new JLabel("Término do Contrato:");
		lblTrminoDoContrato.setBounds(245, 138, 157, 15);
		contentPanel.add(lblTrminoDoContrato);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ImovelAlugadoDAO aluga = new ImovelAlugadoDAO();
						Locale pt = new Locale("pt", "PT");
						NumberFormat nf = NumberFormat.getInstance(pt);
						java.sql.Date dataInicio = new java.sql.Date(dateChooser.getDate().getTime());
						java.sql.Date dataFim = new java.sql.Date(dateChooser_1.getDate().getTime());
						
						try {
							if(aluga.alugarImovel(nf.parse(idImovel.getSelectedItem().toString()).intValue(), dataInicio.toString(), dataFim.toString(), nome.getSelectedItem().toString())) JOptionPane.showMessageDialog(null,"Parabéns! Você acaba de alugar um imóvel top! Agradecemos a preferência!");
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
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TelaAlugarImovel.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancelar");
				buttonPane.add(cancelButton);
			}
		}
	}
}
