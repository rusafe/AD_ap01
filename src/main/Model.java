package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Model {
	public String obtainEstructuraDirectori(File directori) {
		return String.format("%s%s", directori.getName(), estructuraDirectori(directori, 0));
	}
	
	public String obtainEstructuraDirectori(File directori, String paraula, boolean respetarMayuscules, boolean respetarAcentos) {
		return String.format("%s%s", directori.getName(), estructuraDirectori(directori, 0, paraula, respetarMayuscules, respetarAcentos));
	}
	
	public String obtainEstructuraDirectori(File directori, String paraulaReemplazar, String paraulaNova, boolean respetarMayuscules, boolean respetarAcentos) {
		return String.format("%s%s", directori.getName(), estructuraDirectori(directori, 0, paraulaReemplazar, paraulaNova, respetarMayuscules, respetarAcentos));
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
				estructura += String.format("%s%s %s (%.1f KB - %s)", System.lineSeparator(), anyadirSeparacioRuta(profunditat), archiu.getName(), transformarAKilobytes(archiu.length()), formatoFecha.format(new Date()));
			}
		}
		
		return estructura;
	}
	
	private String estructuraDirectori(File directori, int profunditat, String paraula, boolean respetarMayuscules, boolean respetarAcentos) {
		String estructura = "";
		
		File[] continguts = directori.listFiles();
		
		if(continguts.length == 0) {
			return "";
		}
		
		for(File archiu : continguts) {
			if(archiu.isDirectory()) {
				if(archiu.list().length != 0) {
					estructura += String.format("%s%s\\%s", System.lineSeparator(), anyadirSeparacioRuta(profunditat), archiu.getName());
					estructura += estructuraDirectori(archiu, profunditat + 1, paraula, respetarMayuscules, respetarAcentos);					
				}
			}
			else {
				estructura += String.format("%s%s %s (%d coincidències)", System.lineSeparator(), anyadirSeparacioRuta(profunditat), archiu.getName(), cantitatCoincidencies(archiu, paraula, respetarMayuscules, respetarAcentos));
			}
		}
		
		return estructura;
	}
	
	private String estructuraDirectori(File directori, int profunditat, String paraulaReemplazar, String paraulaNova, boolean respetarMayuscules, boolean respetarAcentos) {
		String estructura = "";
		
		File[] continguts = directori.listFiles();
		
		if(continguts.length == 0) {
			return "";
		}
		
		for(File archiu : continguts) {
			if(archiu.isDirectory()) {
				if(archiu.list().length != 0) {
					estructura += String.format("%s%s\\%s", System.lineSeparator(), anyadirSeparacioRuta(profunditat), archiu.getName());
					estructura += estructuraDirectori(archiu, profunditat + 1, paraulaReemplazar, paraulaNova, respetarMayuscules, respetarAcentos);					
				}
			}
			else {
				estructura += String.format("%s%s %s (%d reemplazos)", System.lineSeparator(), anyadirSeparacioRuta(profunditat), archiu.getName(), reemplazar(archiu, paraulaReemplazar, paraulaNova, respetarMayuscules, respetarAcentos));
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
	
	private int cantitatCoincidencies(File archiu, String paraula, boolean respetarMayuscules, boolean respetarAcentos) {
		String text;
		try {
			text = llegirArchiu(archiu);
		} catch (Exception e) {
			return 0;
		}
		
		if(!respetarMayuscules) {
			paraula = paraula.toLowerCase();
			text = text.toLowerCase();
		}
		if(!respetarAcentos) {
			paraula = llevarAcento(paraula);
			text = llevarAcento(text);
		}
		
		int coincidencies = 0;

		int indicePosibleCoincidencia = text.indexOf(paraula.charAt(0));
		String posibleCoincidencia;
		while(indicePosibleCoincidencia != -1) {
			if(indicePosibleCoincidencia + paraula.length() > text.length())
				break;
			
			posibleCoincidencia = text.substring(indicePosibleCoincidencia, indicePosibleCoincidencia + paraula.length());
			
			if(paraula.equals(posibleCoincidencia))
				coincidencies++;
			
			indicePosibleCoincidencia = text.indexOf(paraula.charAt(0), indicePosibleCoincidencia + 1);
		}
		
		return coincidencies;
	}
	
	private int reemplazar(File archiu, String paraulaReemplazar, String paraulaNova, boolean respetarMayuscules, boolean respetarAcentos) {
		int cantitatReemplazos = cantitatCoincidencies(archiu, paraulaReemplazar, respetarMayuscules, respetarAcentos);

		if(cantitatReemplazos == 0)
			return 0;
		
		String ruta = archiu.getParentFile().getPath();
		String nouNom = String.format("MOD_%s", archiu.getName());
		
		String text; 
		
		try {
			text = llegirArchiu(archiu);			
		} catch (Exception e) {
			return 0;
		}
		
		if(!respetarMayuscules) {
			paraulaReemplazar = paraulaReemplazar.toLowerCase();
			text = text.toLowerCase();
		}
		if(!respetarAcentos) {
			paraulaReemplazar = llevarAcento(paraulaReemplazar);
			text = llevarAcento(text);
		}
		
		String textReemplazat = text.replaceAll(paraulaReemplazar, paraulaNova);

		try {
			FileWriter fw = new FileWriter(String.format("%s%s%s", ruta, File.separator, nouNom));	
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(textReemplazat);
			
			bw.close();
			fw.close();
		} catch (Exception e) {
			return 0;
		}

		return cantitatReemplazos;
	}
	
	private String llegirArchiu(File archiu) throws Exception {
		String text = "";
		try {
			FileReader fr = new FileReader(archiu);
			BufferedReader br = new BufferedReader(fr);
			
			String linea = br.readLine();
			while(linea != null) {
				text += linea;
				linea = br.readLine();
				if(linea != null)
					text += System.lineSeparator();
			}
			
			br.close();
			fr.close();
		} catch (Exception e) {
			throw e;
		}
		return text;
	}
	
	private String llevarAcento(String paraula) {
		return Normalizer.normalize(paraula, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}
}
