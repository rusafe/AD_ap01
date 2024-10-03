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
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;

public class Vista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIntroduixDirectori;
	private JTextArea txaEstructuraDirectori;
	private JButton btnEstructura;
	private JScrollPane scrollPane;
	private JTextField txtBuscar;
	private JButton btnBuscar;
	private JCheckBox chkRespetarMayuscules;
	private JCheckBox chkRespetarAcentos;
	private JLabel lblReemplazar;
	private JTextField txtReemplazar;
	private JButton btnReemplazar;

	public Vista() {
		initComponents();
	}
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 612);
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
		lblEstructuraDirectori.setBounds(24, 87, 233, 33);
		contentPane.add(lblEstructuraDirectori);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 131, 647, 302);
		contentPane.add(scrollPane);
		
		txaEstructuraDirectori = new JTextArea();
		scrollPane.setViewportView(txaEstructuraDirectori);
		
		btnEstructura = new JButton("Estructura");
		btnEstructura.setBackground(new Color(192, 192, 192));
		btnEstructura.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEstructura.setBounds(573, 22, 98, 33);
		contentPane.add(btnEstructura);
		
		txtBuscar = new JTextField();
		txtBuscar.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtBuscar.setColumns(10);
		txtBuscar.setBounds(107, 455, 175, 33);
		contentPane.add(txtBuscar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBuscar.setBackground(Color.LIGHT_GRAY);
		btnBuscar.setBounds(290, 456, 98, 33);
		contentPane.add(btnBuscar);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblBuscar.setBounds(24, 444, 73, 55);
		contentPane.add(lblBuscar);
		
		chkRespetarMayuscules = new JCheckBox("Respetar mayuscules");
		chkRespetarMayuscules.setBounds(396, 461, 157, 23);
		contentPane.add(chkRespetarMayuscules);
		
		chkRespetarAcentos = new JCheckBox("Respetar acentos");
		chkRespetarAcentos.setBounds(555, 461, 131, 23);
		contentPane.add(chkRespetarAcentos);
		
		lblReemplazar = new JLabel("Reemplazar:");
		lblReemplazar.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblReemplazar.setBounds(24, 496, 131, 55);
		contentPane.add(lblReemplazar);
		
		txtReemplazar = new JTextField();
		txtReemplazar.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtReemplazar.setColumns(10);
		txtReemplazar.setBounds(165, 507, 175, 33);
		contentPane.add(txtReemplazar);
		
		btnReemplazar = new JButton("Reemplazar");
		btnReemplazar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReemplazar.setBackground(Color.LIGHT_GRAY);
		btnReemplazar.setBounds(350, 507, 123, 33);
		contentPane.add(btnReemplazar);
		
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
	
	public JTextField getTxtBuscar() {
		return txtBuscar;
	}
	
	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public JCheckBox getChkRespetarMayuscules() {
		return chkRespetarMayuscules;
	}

	public JCheckBox getChkRespetarAcentos() {
		return chkRespetarAcentos;
	}
	
	public JTextField getTxtReemplazar() {
		return txtReemplazar;
	}

	public JButton getBtnReemplazar() {
		return btnReemplazar;
	}
}
