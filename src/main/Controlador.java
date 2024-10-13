package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

/**
 * Clase que contecta la Vista i el Model
 */
public class Controlador {
	/**
	 * Instancia del objecte Vista que conte la interfaz grafica
	 */
	Vista vista;
	
	/**
	 * Instancia del objecte Model que conte la logica de la aplicacio
	 */
	Model model;
	
	/**
	 * Constructor
	 * @param vista Objecte Vista
	 * @param model Objecte Model
	 */
	public Controlador(Vista vista, Model model) {
		this.vista = vista;
		this.model = model;
		initEventHandlers();
	}
	
	/**
	 * Metode que crea els manejadors de eventos dels components de la interfaz grafica
	 */
	public void initEventHandlers() {
		vista.getBtnEstructura().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(vista.getTxtIntroduixDirectori().getText().equals("")) {
					JOptionPane.showMessageDialog(null, "No se ha introduit directori.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				File directori = new File(vista.getTxtIntroduixDirectori().getText());
				
				vista.getTxaEstructuraDirectori().setText(model.obtindreEstructuraDirectori(directori));
			}
		});
		vista.getBtnBuscar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(vista.getTxtIntroduixDirectori().getText().equals("")) {
					JOptionPane.showMessageDialog(null, "No se ha introduit directori.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(vista.getTxtBuscar().getText().equals("")) {
					JOptionPane.showMessageDialog(null, "No se ha introduit res per a buscar.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				File directori = new File(vista.getTxtIntroduixDirectori().getText());
				String paraulaBuscar = vista.getTxtBuscar().getText();
				boolean respetarMayuscules = vista.getChkRespetarMayuscules().isSelected();
				boolean respetarAcentos = vista.getChkRespetarAcentos().isSelected();
				
				vista.getTxaEstructuraDirectori().setText(model.obtindreEstructuraDirectori(directori, paraulaBuscar, respetarMayuscules, respetarAcentos));
			}
		});
		vista.getBtnReemplazar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(vista.getTxtIntroduixDirectori().getText().equals("")) {
					JOptionPane.showMessageDialog(null, "No se ha introduit directori.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(vista.getTxtBuscar().getText().equals("")) {
					JOptionPane.showMessageDialog(null, "No se ha introduit res per a buscar.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(vista.getTxtReemplazar().getText().equals("")) {
					JOptionPane.showMessageDialog(null, "No se ha introduit res per a reemplazar.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				File directori = new File(vista.getTxtIntroduixDirectori().getText());
				String paraulaReemplazar = vista.getTxtBuscar().getText();
				String paraulaNova = vista.getTxtReemplazar().getText();
				boolean respetarMayuscules = vista.getChkRespetarMayuscules().isSelected();
				boolean respetarAcentos = vista.getChkRespetarAcentos().isSelected();
				
				vista.getTxaEstructuraDirectori().setText(model.obtindreEstructuraDirectori(directori, paraulaReemplazar, paraulaNova, respetarMayuscules, respetarAcentos));
			}
		});
	}
}
