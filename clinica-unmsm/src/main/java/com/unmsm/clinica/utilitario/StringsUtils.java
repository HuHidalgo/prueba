package com.unmsm.clinica.utilitario;

import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import com.google.common.primitives.Chars;

public class StringsUtils {
	private StringsUtils() {
		throw new UnsupportedOperationException(ConstantesExcepciones.NO_INSTANCIAR_CLASE_ESTATICA);
	}

	public static String obtenerCadenaDespuesDePunto(String cadenaConPunto) {
		return cadenaConPunto.substring(cadenaConPunto.lastIndexOf('.') + 1);
	}

	public static String concatenarCadena(Object... objetos) {
		StringBuilder sb = new StringBuilder();
		for (Object objeto : objetos) {
			sb.append(objeto.toString());
		}
		return sb.toString();
	}

	public static List<String> obtenerTokens(String cadena, String separador) {
		String cadenaSinEspacios = cadena.replaceAll(Regex.ESPACIOS_EN_BLANCO, "");
		return Collections.list(new StringTokenizer(cadenaSinEspacios, separador)).stream().map(token -> (String) token)
				.collect(Collectors.toList());
	}

	public static String obtenerCadenaDespuesDe(String cadena, String separador) {
		if (cadena != null && cadena.length() > 0) {
			return cadena.substring(cadena.lastIndexOf(separador) + 1);
		}
		return cadena;
	}

	public static String obtenerCadenaAntesDe(String cadena, String separador) {
		if (cadena != null && cadena.length() > 0) {
			return cadena.substring(0, cadena.lastIndexOf(separador));
		}
		return cadena;
	}

	public static String removerUltimoCaracter(String cadena) {
		if (cadena != null && cadena.length() > 0) {
			cadena = cadena.substring(0, cadena.length() - 1);
		}
		return cadena;
	}

	public static boolean esNumero(String numero) {
		return numero.matches(Regex.SOLO_DIGITOS);
	}

	public static List<String> fullJustify(String words, int maxWidth) {

		return fullJustify(words.split(" "), maxWidth);
	}

	public static String[] singleChars(String s) {
		return Lists.transform(Chars.asList(s.toCharArray()), Functions.toStringFunction())
				.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
	}

	public static List<String> singleChars2(String s) {
		String[] listaString = s.split(" ");
		List<String> lista = new ArrayList<>();
		int i = 0;
		while (i < listaString.length) {
			if (i == listaString.length - 1) {
				lista.add(listaString[i]);
			} else {
				lista.add(listaString[i]);
				lista.add(" ");
				/*
				 * if(listaString[i].compareTo("N°")==0) { lista.add(listaString[i] + " " +
				 * listaString[i+1] + " "); i++; } else { lista.add(listaString[i]);
				 * lista.add(" "); }
				 */

			}
			i++;
		}
		return lista;
	}

	public static String singleChars3(List<String> lista, int inicio, int fin) {
		String texto = "";
		for (int i = inicio; i <= fin; i++) {
			texto += lista.get(i);
		}
		return texto;
	}

	public static int devolverPosicion(List<String> lista, int cant, FontMetrics f) {
		int suma = 0;
		int pos = -1;
		for (String auxiliar : lista) {
			if ((suma + f.stringWidth(auxiliar)) <= cant) {
				suma += f.stringWidth(auxiliar);
				pos++;
			} else {
				return pos;
			}
			/*
			 * if((auxiliar.length()) == 0) { if(suma + 1 <=cant) { suma += 1; pos++; } }
			 * else { if((suma + auxiliar.length()) <= cant) { suma += auxiliar.length();
			 * pos++; } }
			 */

		}
		return pos;
	}

	public static String cadenaTexto(List<String> lista) {
		return String.join("", lista);
	}

	public static List<String> fullJustify(String[] words, int maxWidth) {
		int n = words.length;
		List<String> justifiedText = new ArrayList<>();
		int currLineIndex = 0;
		int nextLineIndex = getNextLineIndex(currLineIndex, maxWidth, words);
		while (currLineIndex < n) {
			StringBuilder line = new StringBuilder();
			for (int i = currLineIndex; i < nextLineIndex; i++) {
				line.append(words[i] + " ");
			}
			currLineIndex = nextLineIndex;
			nextLineIndex = getNextLineIndex(currLineIndex, maxWidth, words);
			justifiedText.add(line.toString());
		}
		for (int i = 0; i < justifiedText.size() - 1; i++) {
			String fullJustifiedLine = getFullJustifiedString(justifiedText.get(i).trim(), maxWidth);
			justifiedText.remove(i);
			justifiedText.add(i, fullJustifiedLine);
		}
		String leftJustifiedLine = getLeftJustifiedLine(justifiedText.get(justifiedText.size() - 1).trim(), maxWidth);
		justifiedText.remove(justifiedText.size() - 1);
		justifiedText.add(leftJustifiedLine);
		return justifiedText;
	}

	public static int getNextLineIndex(int currLineIndex, int maxWidth, String[] words) {
		int n = words.length;
		int width = 0;
		int count = 0;
		while (currLineIndex < n && width < maxWidth) {
			width += words[currLineIndex++].length() + 1;
			count++;
		}
		if (width > maxWidth + 1 && count > 1)
			currLineIndex--;

		return currLineIndex;
	}

	public static String getFullJustifiedString(String line, int maxWidth) {
		StringBuilder justifiedLine = new StringBuilder();
		String[] words = line.split(" ");
		int occupiedCharLength = 0;
		for (String word : words) {
			occupiedCharLength += word.length();
		}
		int remainingSpace = maxWidth - occupiedCharLength;
		int spaceForEachWordSeparation = words.length > 1 ? remainingSpace / (words.length - 1) : remainingSpace;
		int extraSpace = remainingSpace - spaceForEachWordSeparation * (words.length - 1);
		for (int j = 0; j < words.length - 1; j++) {
			justifiedLine.append(words[j]);
			for (int i = 0; i < spaceForEachWordSeparation; i++)
				justifiedLine.append(" ");
			if (extraSpace > 0) {
				justifiedLine.append(" ");
				extraSpace--;
			}
		}
		justifiedLine.append(words[words.length - 1]);
		for (int i = 0; i < extraSpace; i++)
			justifiedLine.append(" ");
		return justifiedLine.toString();
	}

	public static String getLeftJustifiedLine(String line, int maxWidth) {
		int lineWidth = line.length();
		StringBuilder justifiedLine = new StringBuilder(line);
		for (int i = 0; i < maxWidth - lineWidth; i++)
			justifiedLine.append(" ");
		return justifiedLine.toString();
	}

}