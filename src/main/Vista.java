package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;

public class Vista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIntroduixDirectori;
	private JTextArea txaEstructuraDirectori;
	private JButton btnEstructura;

	public Vista() {
		initComponents();
	}
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIntroduixDirectori = new JLabel("Introduix directori:");
		lblIntroduixDirectori.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblIntroduixDirectori.setBounds(24, 11, 181, 55);
		contentPane.add(lblIntroduixDirectori);
		
		txtIntroduixDirectori = new JTextField();
		txtIntroduixDirectori.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtIntroduixDirectori.setBounds(212, 22, 355, 33);
		contentPane.add(txtIntroduixDirectori);
		txtIntroduixDirectori.setColumns(10);
		
		JLabel lblEstructuraDirectori = new JLabel("Estructura de directoris:");
		lblEstructuraDirectori.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblEstructuraDirectori.setBounds(334, 89, 233, 33);
		contentPane.add(lblEstructuraDirectori);
		
		txaEstructuraDirectori = new JTextArea();
		txaEstructuraDirectori.setBounds(334, 131, 337, 302);
		contentPane.add(txaEstructuraDirectori);
		
		btnEstructura = new JButton("Estructura");
		btnEstructura.setBackground(new Color(192, 192, 192));
		btnEstructura.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEstructura.setBounds(573, 22, 98, 33);
		contentPane.add(btnEstructura);
		
		setVisible(true);
	}
	
	public JTextField getTxtIntroduixDirectori() {
		return txtIntroduixDirectori;
	}

	public JTextArea getTxaEstructuraDirectori() {
		return txaEstructuraDirectori;
	}

	public JButton getBtnEstructura() {
		return btnEstructura;
	}
}
