package intf;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bd.ImovelAlugadoDAO;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JCalendar;

public class TelaRenovar extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField fimContrato;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaRenovar dialog = new TelaRenovar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaRenovar() {
		setBounds(100, 100, 450, 300);
		this.setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
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
				if(!(im.verificaDisponibilidade(rs.getInt(1)))) idImovel.addItem(rs.getInt(1));
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
		idImovel.setBounds(37, 26, 81, 19);
		contentPanel.add(idImovel);
		
		fimContrato = new JTextField();
		fimContrato.setBounds(266, 26, 114, 19);
		contentPanel.add(fimContrato);
		fimContrato.setColumns(10);
		
		JButton btnExibirData = new JButton("Verificar");
		btnExibirData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImovelAlugadoDAO im = new ImovelAlugadoDAO();		
				Locale pt = new Locale("pt", "PT");
				NumberFormat nf = NumberFormat.getInstance(pt);
				int extrai;
				try {
					extrai = nf.parse(idImovel.getSelectedItem().toString()).intValue();
					fimContrato.setText(im.getDataFinal(extrai));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		btnExibirData.setBounds(30, 51, 93, 19);
		contentPanel.add(btnExibirData);
		
		JLabel lblIdImvelAlugado = new JLabel("ID Imóvel Alugado:");
		lblIdImvelAlugado.setBounds(18, 9, 142, 15);
		contentPanel.add(lblIdImvelAlugado);
		
		
		
		JLabel lblDataDeTrmino = new JLabel("Término do Aluguel:");
		lblDataDeTrmino.setBounds(255, 8, 142, 15);
		contentPanel.add(lblDataDeTrmino);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(183, 75, 244, 159);
		contentPanel.add(calendar);
		
		JLabel lblSelecioneNovaData = new JLabel("Selecione Nova Data:");
		lblSelecioneNovaData.setBounds(184, 53, 196, 15);
		contentPanel.add(lblSelecioneNovaData);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ImovelAlugadoDAO imovel = new ImovelAlugadoDAO();
						Locale pt = new Locale("pt", "PT");
						NumberFormat nf = NumberFormat.getInstance(pt);
						java.sql.Date dataFim = new java.sql.Date(calendar.getDate().getTime());
						
						try {
							if(imovel.estenderAluguel(nf.parse(idImovel.getSelectedItem().toString()).intValue(), dataFim.toString()))
								JOptionPane.showMessageDialog(null,"Contrato renovado!");
						} catch (HeadlessException | ParseException e1) {
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
						TelaRenovar.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancelar");
				buttonPane.add(cancelButton);
			}
		}
	}
}
