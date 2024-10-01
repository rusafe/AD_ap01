package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
				File directori = new File(vista.getTxtIntroduixDirectori().getText());
				
				vista.getTxaEstructuraDirectori().setText(model.estructuraDirectori(directori, 0));
			}
		});
	}
}
