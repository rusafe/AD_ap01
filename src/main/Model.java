package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.SimpleDateFormat;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class Model {
	/**
	 * Métode per a obtindre la estructura de un directori de forma recursiva i obtindre informacio dels fichers que es troben en els directoris
	 * @param directori Directori del cual obtindre la estructura
	 * @return La estructura del directori introduit
	 */
	public String obtindreEstructuraDirectori(File directori) {
		return String.format("%s%s", directori.getName(), estructuraDirectori(directori, 0));
	}
	
	/**
	 * Métode per a obtindre la estructura de un directori de forma recursiva i buscar coincidencies de textos en els fichers que es troben en els directoris
	 * @param directori Directori del cual obtindre la estructura
	 * @param paraula Paraula a buscar
	 * @param respetarMayuscules Si hi ha que respetar les mayuscules al buscar
	 * @param respetarAcentos Si hi ha que respetar els acentos al buscar
	 * @return La estructura del directori introduit
	 */
	public String obtindreEstructuraDirectori(File directori, String paraula, boolean respetarMayuscules, boolean respetarAcentos) {
		return String.format("%s%s", directori.getName(), estructuraDirectori(directori, 0, paraula, respetarMayuscules, respetarAcentos));
	}
	
	/**
	 * Métode per a obtindre la estructura de un directori de forma recursiva i reemplazar textos dels fichers que es troben en els directoris
	 * @param directori Directori del cual obtindre la estructura
	 * @param paraulaReemplazar Paraula que es va a sustituir en els fichers
	 * @param paraulaNova Paraula que es va posar en els fichers
	 * @param respetarMayuscules Si hi ha que respetar les mayuscules al buscar y reemplazar
	 * @param respetarAcentos Si hi ha que respetar els acentos al buscar y reemplazar
	 * @return La estructura del directori introduit
	 */
	public String obtindreEstructuraDirectori(File directori, String paraulaReemplazar, String paraulaNova, boolean respetarMayuscules, boolean respetarAcentos) {
		return String.format("%s%s", directori.getName(), estructuraDirectori(directori, 0, paraulaReemplazar, paraulaNova, respetarMayuscules, respetarAcentos));
	}
	
	/**
	 * Métode per a obtindre la estructura de un directori de forma recursiva i obtindre informacio dels fichers que es troben en els directoris
	 * @param directori Directori del cual obtindre la estructura
	 * @param profunditat Profunditat a la cual es troba desde el directori base
	 * @return La estructura del directori introduit
	 */
	private String estructuraDirectori(File directori, int profunditat) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
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
				estructura += String.format("%s%s %s (%.1f KB - %s)", System.lineSeparator(), anyadirSeparacioRuta(profunditat), archiu.getName(), transformarAKilobytes(archiu.length()), formatoFecha.format(archiu.lastModified()));
			}
		}
		
		return estructura;
	}
	
	/**
	 * Métode per a obtindre la estructura de un directori de forma recursiva i buscar coincidencies de textos en els fichers que es troben en els directoris
	 * @param directori Directori del cual obtindre la estructura
	 * @param profunditat Profunditat a la cual es troba desde el directori base
	 * @param paraula Paraula a buscar
	 * @param respetarMayuscules Si hi ha que respetar les mayuscules al buscar
	 * @param respetarAcentos Si hi ha que respetar els acentos al buscar
	 * @return La estructura del directori introduit
	 */
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
	
	/**
	 * Métode per a obtindre la estructura de un directori de forma recursiva i reemplazar textos dels fichers que es troben en els directoris
	 * @param directori Directori del cual obtindre la estructura
	 * @param profunditat Profunditat a la cual es troba desde el directori base
	 * @param paraulaReemplazar Paraula que es va a sustituir en els fichers
	 * @param paraulaNova Paraula que es va posar en els fichers
	 * @param respetarMayuscules Si hi ha que respetar les mayuscules al buscar y reemplazar
	 * @param respetarAcentos Si hi ha que respetar els acentos al buscar y reemplazar
	 * @return La estructura del directori introduit
	 */
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
	
	/**
	 * Métode que crea la separacio dels fichers/directoris en base a la profunditat que se troben del directori base
	 * @param profunditat Profunditat desde el directori base
	 * @return La separacio que es te que anyadir
	 */
	private String anyadirSeparacioRuta(int profunditat) {
		String separacio = "";
		
		for(int i = 0; i < profunditat; i++) {
			separacio += "|    ";
		}
		
		separacio += "|-- ";
		
		return separacio;
	}
	
	/**
	 * Métode que transforma bytes a kilobytes
	 * @param bytes Cantitat en bytes
	 * @return Cantitat en kilobytes
	 */
	private float transformarAKilobytes(long bytes) {
		return bytes / 1024f;
	}
	
	/**
	 * 
	 * @param archiu Ficher en el cual es van a buscar les coincidencies
	 * @param paraula Paraula a buscar
	 * @param respetarMayuscules Si hi ha que respetar les mayuscules al buscar
	 * @param respetarAcentos Si hi ha que respetar els acentos al buscar
	 * @return Cantitat de coincidencies
	 */
	private int cantitatCoincidencies(File archiu, String paraula, boolean respetarMayuscules, boolean respetarAcentos) {
		String text;
		try {
			text = archiu.getName().endsWith(".pdf") ? llegirPdf(archiu) : llegirArchiu(archiu);
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
	
	/**
	 * Métode que crea una copia del ficher pasat y sustituix el text introduit per uno nou en la copia
	 * @param archiu Ficher del cual es va a crear una copia i sustituir el text
	 * @param paraulaReemplazar Paraula que es va a sustituir
	 * @param paraulaNova Paraula que es va posar
	 * @param respetarMayuscules Si hi ha que respetar les mayuscules al buscar y reemplazar
	 * @param respetarAcentos Si hi ha que respetar els acentos al buscar y reemplazar
	 * @return Cantitat de reemplazos
	 */
	private int reemplazar(File archiu, String paraulaReemplazar, String paraulaNova, boolean respetarMayuscules, boolean respetarAcentos) {
		String ruta = archiu.getParentFile().getPath();
		String nouNom = String.format("MOD_%s", archiu.getName());
		
		String textReemplazar;
		String textComparar;
		int cantitatReemplazos = 0;
		int desplasamentPerCanviParaula = 0;
		
		try {
			textReemplazar = llegirArchiu(archiu);
			textComparar = textReemplazar;
		} catch (Exception e) {
			return 0;
		}
		
		if(!respetarMayuscules) {
			paraulaReemplazar = paraulaReemplazar.toLowerCase();
			textComparar = textComparar.toLowerCase();
		}
		if(!respetarAcentos) {
			paraulaReemplazar = llevarAcento(paraulaReemplazar);
			textComparar = llevarAcento(textComparar);
		}

		int indicePosibleCoincidencia = textComparar.indexOf(paraulaReemplazar.charAt(0));
		String posibleCoincidencia;
		while(indicePosibleCoincidencia != -1) {
			if(indicePosibleCoincidencia + paraulaReemplazar.length() > textComparar.length())
				break;
			
			posibleCoincidencia = textComparar.substring(indicePosibleCoincidencia, indicePosibleCoincidencia + paraulaReemplazar.length());
			
			if(paraulaReemplazar.equals(posibleCoincidencia)) {
				textReemplazar = textReemplazar.substring(0, indicePosibleCoincidencia + desplasamentPerCanviParaula) + paraulaNova + textReemplazar.substring(indicePosibleCoincidencia + paraulaReemplazar.length() + desplasamentPerCanviParaula);
				desplasamentPerCanviParaula += paraulaNova.length() - paraulaReemplazar.length();
				cantitatReemplazos++;
			}
			
			indicePosibleCoincidencia = textComparar.indexOf(paraulaReemplazar.charAt(0), indicePosibleCoincidencia + 1);
		}
		
		if(cantitatReemplazos == 0)
			return 0;

		try {
			FileWriter fw = new FileWriter(String.format("%s%s%s", ruta, File.separator, nouNom));	
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(textReemplazar);
			
			bw.close();
			fw.close();
		} catch (Exception e) {
			return 0;
		}

		return cantitatReemplazos;
	}
	
	/**
	 * Métode que llig y retorna el contingut de un archiu de text
	 * @param archiu Objecte file que conté el archiu de text que es va a llegir
	 * @return El contingut del archiu com un string
	 * @throws Exception Error al llegir el archiu de text
	 */
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
	
	/**
	 * Métode que llig y retorna el contingut de un pdf
	 * @param archiu Objecte file que conté el pdf que es va a llegir
	 * @return El contingut del pdf com un string
	 * @throws Exception Error al llegir el pdf
	 */
	private String llegirPdf(File archiu) throws Exception {
		String text = "";
		
		try {
			PDDocument pdf = Loader.loadPDF(archiu);
			PDFTextStripper pdfTextStripper = new PDFTextStripper();
			text = pdfTextStripper.getText(pdf);
			pdf.close();	
		} catch (Exception e) {
			throw e;
		}
		
		return text;
	}
	
	/**
	 * Métode que elimina els acentos de un text
	 * @param paraula Text al cual eliminar els acentos
	 * @return Text sense acentos
	 */
	private String llevarAcento(String paraula) {
		return Normalizer.normalize(paraula, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}
}
