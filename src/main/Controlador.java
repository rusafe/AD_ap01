package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

public class Controlador {
	Vista vista;
	Model model;
	
	public Controlador(Vista vista, Model model) {
		this.vista = vista;
		this.model = model;
		initEventHandlers();
	}
	
	public void initEventHandlers() {
		vista.getBtnEstructura().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(vista.getTxtIntroduixDirectori().getText().equals("")) {
					JOptionPane.showMessageDialog(null, "No se ha introduit directori.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				File directori = new File(vista.getTxtIntroduixDirectori().getText());
				
				vista.getTxaEstructuraDirectori().setText(model.obtainEstructuraDirectori(directori));
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
				
				vista.getTxaEstructuraDirectori().setText(model.obtainEstructuraDirectori(directori, paraulaBuscar, respetarMayuscules, respetarAcentos));
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
				
				vista.getTxaEstructuraDirectori().setText(model.obtainEstructuraDirectori(directori, paraulaReemplazar, paraulaNova, respetarMayuscules, respetarAcentos));
			}
		});
	}
}
