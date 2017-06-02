/**
 * Classe Sobre - Trata-se da tela com informações sobre a aplicação
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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sobre extends JDialog {

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
	public Sobre() {
		setBounds(500, 200, 450, 300);
		this.setModal(true);//Impedir que outras caixas de diálogo sejam abertas
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTopimveisV = new JLabel("TopImóveis v1.0");
			lblTopimveisV.setBounds(169, 12, 133, 15);
			contentPanel.add(lblTopimveisV);
		}
		{
			JLabel lblDesenvolvidoPorFelipe = new JLabel("Desenvolvido por Felipe Santos\n");
			lblDesenvolvidoPorFelipe.setBounds(12, 72, 322, 27);
			contentPanel.add(lblDesenvolvidoPorFelipe);
		}
		{
			JLabel lblEmailFelipedmsantosgmailcom = new JLabel("Email: felipedmsantos95@gmail.com");
			lblEmailFelipedmsantosgmailcom.setBounds(12, 101, 261, 21);
			contentPanel.add(lblEmailFelipedmsantosgmailcom);
		}
		{
			JLabel lblTrabalhoPrticoDa = new JLabel("Trabalho Prático da Disciplina Projetos de Programas");
			lblTrabalhoPrticoDa.setBounds(12, 172, 400, 15);
			contentPanel.add(lblTrabalhoPrticoDa);
		}
		{
			JLabel lblProfessorHorcioFernandes = new JLabel("Professor: Horácio Fernandes");
			lblProfessorHorcioFernandes.setBounds(12, 196, 261, 15);
			contentPanel.add(lblProfessorHorcioFernandes);
		}
		
		JLabel label = new JLabel("");
		label.setBounds(346, 78, 54, 62);
		
		ImageIcon foto = new ImageIcon(Principal.class.getResource("/img/top64.png"));
		Image imag = foto.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(imag));
		contentPanel.add(label);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Sobre.this.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
