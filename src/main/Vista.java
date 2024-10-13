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

/**
 * Clase on es crea la interfaz grafica
 */
public class Vista extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Component JPanel principal que conte el resto de components de la interfaz grafica
	 */
	private JPanel contentPane;
	
	/**
	 * Component JTextField que conte la ruta al directori raiz aon fer les operacions
	 */
	private JTextField txtIntroduixDirectori;
	
	/**
	 * Component JTextArea aon es mostra el output del programa
	 */
	private JTextArea txaEstructuraDirectori;
	
	/**
	 * Component JButton per a obtindre la estructura de directoris del directori introduit
	 */
	private JButton btnEstructura;
	
	/**
	 * Component JTextField que conte el text a buscar en els fichers
	 */
	private JTextField txtBuscar;
	
	/**
	 * Component JButton per a buscar coincidencies de text en els fichers
	 */
	private JButton btnBuscar;
	
	/**
	 * Component JCheckBox que indica si es tenen que respetar les mayuscules
	 */
	private JCheckBox chkRespetarMayuscules;
	
	/**
	 * Component JCheckBox que indica si es tenen que respetar els acentos
	 */
	private JCheckBox chkRespetarAcentos;
	
	/**
	 * Component JTextField que conte el nou text que es reemplazara en els fichers
	 */
	private JTextField txtReemplazar;
	
	/**
	 * Component JButton per a buscar i reemplazar coincidencies de text en els fichers
	 */
	private JButton btnReemplazar;

	/**
	 * Constructor
	 */
	public Vista() {
		initComponents();
	}
	
	/**
	 * Metode que inicialitza els components de la interfaz grafica
	 */
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 781, 612);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIntroduixDirectori = new JLabel("Introduix directori:");
		lblIntroduixDirectori.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblIntroduixDirectori.setBounds(24, 11, 239, 55);
		contentPane.add(lblIntroduixDirectori);
		
		txtIntroduixDirectori = new JTextField();
		txtIntroduixDirectori.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtIntroduixDirectori.setBounds(217, 23, 355, 33);
		contentPane.add(txtIntroduixDirectori);
		txtIntroduixDirectori.setColumns(10);
		
		JLabel lblEstructuraDirectori = new JLabel("Estructura de directoris:");
		lblEstructuraDirectori.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblEstructuraDirectori.setBounds(24, 87, 293, 33);
		contentPane.add(lblEstructuraDirectori);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 131, 647, 302);
		contentPane.add(scrollPane);
		
		txaEstructuraDirectori = new JTextArea();
		scrollPane.setViewportView(txaEstructuraDirectori);
		
		btnEstructura = new JButton("Estructura");
		btnEstructura.setBackground(new Color(192, 192, 192));
		btnEstructura.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEstructura.setBounds(578, 23, 98, 33);
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
		
		JLabel lblReemplazar = new JLabel("Reemplazar:");
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
	
	/**
	 * Getter de component
	 * @return Component JTextField que conte la ruta al directori raiz aon fer les operacions
	 */
	public JTextField getTxtIntroduixDirectori() {
		return txtIntroduixDirectori;
	}
	
	/**
	 * Getter de component
	 * @return Component JTextArea aon es mostra el output del programa
	 */
	public JTextArea getTxaEstructuraDirectori() {
		return txaEstructuraDirectori;
	}

	/**
	 * Getter de component
	 * @return Component JButton per a obtindre la estructura de directoris del directori introduit
	 */
	public JButton getBtnEstructura() {
		return btnEstructura;
	}
	
	/**
	 * Getter de component
	 * @return Component JTextField que conte el text a buscar en els fichers
	 */
	public JTextField getTxtBuscar() {
		return txtBuscar;
	}
	
	/**
	 * Getter de component
	 * @return Component JButton per a buscar coincidencies de text en els fichers
	 */
	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	/**
	 * Getter de component
	 * @return Component JCheckBox que indica si es tenen que respetar les mayuscules
	 */
	public JCheckBox getChkRespetarMayuscules() {
		return chkRespetarMayuscules;
	}

	/**
	 * Getter de component
	 * @return Component JCheckBox que indica si es tenen que respetar els acentos
	 */
	public JCheckBox getChkRespetarAcentos() {
		return chkRespetarAcentos;
	}
	
	/**
	 * Getter de component
	 * @return Component JTextField que conte el nou text que es reemplazara en els fichers
	 */
	public JTextField getTxtReemplazar() {
		return txtReemplazar;
	}

	/**
	 * Getter de component
	 * @return Component JButton per a buscar i reemplazar coincidencies de text en els fichers
	 */
	public JButton getBtnReemplazar() {
		return btnReemplazar;
	}
}
