package main;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Model {
	ArrayList<File> fichers = new ArrayList<File>();
	
	public String estructuraDirectori(File directori, int profunditat) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String estructura = "";
		
		File[] continguts = directori.listFiles();
		
		if(continguts.length == 0) {
			return "";
		}

		estructura += String.format("%s\n%s", directori.getName(), anyadirSeparacioRuta(profunditat));
		
		for(File archiu : continguts) {
			if(archiu.isDirectory()) {
				estructuraDirectori(archiu, profunditat + 1);
			}
			else {
				fichers.add(archiu);
				estructura += String.format("%s (%.1f KB - %s)", archiu.getName(), transformarAKilobytes(archiu.length()), formatoFecha.format(new Date()));
			}
		}
		
		return estructura;
	}
	
	private String anyadirSeparacioRuta(int profunditat) {
		String separacio = "";
		
		for(int i = 0; i < profunditat; i++) {
			separacio += "|   ";
		}
		
		separacio += "|-- ";
		
		return separacio;
	}
	
	private float transformarAKilobytes(long bytes) {
		return bytes / 1024f;
	}
}
