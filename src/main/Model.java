package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Model {
	ArrayList<File> fichers = new ArrayList<File>();
	
	public String obtainEstructuraDirectori(File directori) {
		return String.format("%s%s", directori.getName(), estructuraDirectori(directori, 0));
	}
	
	public String obtainEstructuraDirectori(File directori, String paraula) {
		return String.format("%s%s", directori.getName(), estructuraDirectori(directori, 0, paraula));
	}
	
	private String estructuraDirectori(File directori, int profunditat) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String estructura = "";
		
		File[] continguts = directori.listFiles();
		
		if(continguts.length == 0) {
			return "";
		}
		
		for(File archiu : continguts) {
			if(archiu.isDirectory()) {
				if(archiu.list().length != 0) {
					estructura += String.format("%s%s\\%s", System.lineSeparator(), anyadirSeparacioRuta(profunditat), archiu.getName());
					estructura += estructuraDirectori(archiu, profunditat + 1);					
				}
			}
			else {
				fichers.add(archiu);
				estructura += String.format("%s%s %s (%.1f KB - %s)", System.lineSeparator(), anyadirSeparacioRuta(profunditat), archiu.getName(), transformarAKilobytes(archiu.length()), formatoFecha.format(new Date()));
			}
		}
		
		return estructura;
	}
	
	private String estructuraDirectori(File directori, int profunditat, String paraula) {
		String estructura = "";
		
		File[] continguts = directori.listFiles();
		
		if(continguts.length == 0) {
			return "";
		}
		
		for(File archiu : continguts) {
			if(archiu.isDirectory()) {
				if(archiu.list().length != 0) {
					estructura += String.format("%s%s\\%s", System.lineSeparator(), anyadirSeparacioRuta(profunditat), archiu.getName());
					estructura += estructuraDirectori(archiu, profunditat + 1, paraula);					
				}
			}
			else {
				fichers.add(archiu);
				estructura += String.format("%s%s %s (%d coincidències)", System.lineSeparator(), anyadirSeparacioRuta(profunditat), archiu.getName(), cantitatCoincidencies(archiu, paraula));
			}
		}
		
		return estructura;
	}
	
	private String anyadirSeparacioRuta(int profunditat) {
		String separacio = "";
		
		for(int i = 0; i < profunditat; i++) {
			separacio += "|    ";
		}
		
		separacio += "|-- ";
		
		return separacio;
	}
	
	private float transformarAKilobytes(long bytes) {
		return bytes / 1024f;
	}
	
	private int cantitatCoincidencies(File archiu, String paraula) {
		int coincidencies = 0;
		
		String text = llegirArchiu(archiu);
		
		int indicePosibleCoincidencia = text.indexOf(paraula.charAt(0));
		String posibleCoincidencia;
		while(indicePosibleCoincidencia != -1) {
			posibleCoincidencia = text.substring(indicePosibleCoincidencia, indicePosibleCoincidencia + paraula.length());

			if(paraula.equals(posibleCoincidencia))
				coincidencies++;
			
			indicePosibleCoincidencia = text.indexOf(paraula.charAt(0), indicePosibleCoincidencia + 1);
		}
		
		return coincidencies;
	}
	
	private String llegirArchiu(File archiu) {
		String text = "";
		try {
			
			FileReader fr = new FileReader(archiu);
			BufferedReader br = new BufferedReader(fr);
			
			String linea = br.readLine();
			while(linea != null) {
				text += linea;
				linea = br.readLine();
				if(linea != null)
					text += "\n";
			}
			
			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}
}
