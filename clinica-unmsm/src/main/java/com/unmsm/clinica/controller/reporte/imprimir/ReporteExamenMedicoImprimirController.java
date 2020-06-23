package com.unmsm.clinica.controller.reporte.imprimir;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unmsm.clinica.controller.excepcion.anotacion.Vista;
import com.unmsm.clinica.controller.reporte.rest.ReporteDetalleResultadoExamenMedicoRestController;
import com.unmsm.clinica.model.criterio.CriterioBusquedaReporteExamenMedico;
import com.unmsm.clinica.model.mantenimiento.Medico;
import com.unmsm.clinica.service.IMedicoService;
import com.unmsm.clinica.service.IReporteExamenMedicoService;
import com.unmsm.clinica.utilitario.ConstantesGenerales;
import com.unmsm.clinica.utilitario.StringsUtils;

@Vista
@RequestMapping("imprimir/examenMedico")
public @Controller class ReporteExamenMedicoImprimirController
		extends ReporteDetalleResultadoExamenMedicoRestController {
	private @Autowired IMedicoService medicoService;
	private @Autowired IReporteExamenMedicoService reporteExamenMedicoService;

	@GetMapping(params = "accion=imprimirIngresantes")
	public void imprimirReporteExamenMedicoIngresante(
			CriterioBusquedaReporteExamenMedico criterioBusquedaReporteExamenMedico) {

		PrinterJob job = PrinterJob.getPrinterJob();
		// Create a landscape page format
		PageFormat pfl = job.defaultPage();

		// Set up a book
		Book bk = new Book();
		bk.append(new ImprimirCertificados(), pfl, listarAlumnosIngresantes().size());
		// Pass the book to the PrinterJob
		job.setPageable(bk);
		// Put up the dialog box
		if (job.printDialog()) {
			// Print the job if the user didn't cancel printing
			try {
				job.print();
			} catch (Exception e) {
				/* handle exception */ }
		}
	}

	public class ImprimirCertificados implements Printable {

		Medico director = medicoService.buscarDirector();

		public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
			Image image = new ImageIcon(getClass().getResource("logo_unmsm.png")).getImage();
			// imagen para la marca de agua
			Image image2 = new ImageIcon(getClass().getResource("marca_agua_unmsm.png")).getImage();

			Font fnt1 = new Font("Segoe UI", Font.BOLD, 12);
			Font fnt2 = new Font("Segoe UI", Font.PLAIN, 12);
			Font fnt3 = new Font("Segoe UI", Font.BOLD, 14);
			Font fnt4 = new Font("Segoe UI", Font.PLAIN, 14);
			Font fnt5 = new Font("Segoe UI", Font.PLAIN, 10);

			Graphics2D g1d = (Graphics2D) g;
			Graphics2D g2d = (Graphics2D) g;
			Graphics2D g3d = (Graphics2D) g;
			Graphics2D g4d = (Graphics2D) g;
			Graphics2D g5d = (Graphics2D) g;

			// marca de agua
			Graphics2D g6d = (Graphics2D) g;

			g2d.drawImage(image, 265, 72, 65, 80, null);
			g1d.translate(pf.getImageableX(), pf.getImageableY()); // 80 120
			g1d.setFont(fnt1);
			g1d.setColor(Color.black);
			g1d.drawString("UNIVERSIDAD NACIONAL MAYOR DE SAN MARCOS ", 80, 110);
			String datoAlumno = listarAlumnosIngresantes().get(pageIndex).getApellidoPaterno() + " "
					+ listarAlumnosIngresantes().get(pageIndex).getApellidoMaterno() + ", "
					+ listarAlumnosIngresantes().get(pageIndex).getNombres();
			drawCenteredString(datoAlumno, 468, 235, g1d);
			/*
			 * g1d.drawString(listarAlumnosIngresantes().get(pageIndex).getApellidoPaterno()
			 * +" "+ listarAlumnosIngresantes().get(pageIndex).getApellidoMaterno()+", "+
			 * listarAlumnosIngresantes().get(pageIndex).getNombres(), 120, 235);
			 */
			g1d.drawString("EXAMEN DE LABORATORIO                             EXAMEN PSICOLÓGICO", 0, 300);
			g1d.drawString("EXAMEN DE RADIOLÓGICO                              EXAMEN ODONTOLÓGICO", 0, 380);
			g1d.drawString("EXAMEN CLÍNICO", 0, 430);
			if (listarAlumnosIngresantes().get(pageIndex).getIdResultadoPsicologico().equals("O")) {
				g1d.drawString("C. Psic.:", 0, 540);
			}
			g2d.translate(pf.getImageableX(), pf.getImageableY());
			g2d.setFont(fnt2);
			g2d.setColor(Color.black);
			g2d.drawString("Universidad del Perú, Decana de América", 45, 55);
			g2d.drawString(
					"El  Médico  Director  de  la  Clínica  Universitaria  Servicios  Médicos  certifica  que  se  ha",
					-73, 120);
			g2d.drawString("evaluado(a) el alumno(a).", -73, 138);

			/*
			 * g2d.drawString(texto, -73, 185);
			 * g2d.drawString("Estudiante de la "+listarAlumnosIngresantes().get(pageIndex).
			 * getDescripcionEscuela()+", con el código", -73, 185);
			 * g2d.drawString("N° "+listarAlumnosIngresantes().get(pageIndex).
			 * getCodigoAlumno()+" con el siguiente resultado", -73, 203);
			 */
			FontMetrics fm = g2d.getFontMetrics(fnt2);
			String textos = "Estudiante de la " + listarAlumnosIngresantes().get(pageIndex).getDescripcionEscuela()
					+ ", con el código " + "N° " + listarAlumnosIngresantes().get(pageIndex).getCodigoAlumno()
					+ " con el siguiente resultado";

			if (fm.stringWidth(textos) > 469) {
				List<String> listaTexto = StringsUtils.singleChars2(textos);
				// System.out.println("CantidadLISTA : "+listaTexto.size() );
				int pos = StringsUtils.devolverPosicion(listaTexto, 468, fm);

				String text1 = StringsUtils.singleChars3(listaTexto, 0, pos);
				// text1 =
				// StringsUtils.cadenaTexto(StringsUtils.fullJustify(StringsUtils.singleChars(text1),
				// 40));
				String text2 = StringsUtils.singleChars3(listaTexto, pos + 1, listaTexto.size() - 1);
				// text2 =
				// StringsUtils.cadenaTexto(StringsUtils.fullJustify(StringsUtils.singleChars(text2),
				// 60));
				drawJustifyString(text1, g2d, 469, -73, 170);
				// g2d.drawString(text1, -73, 185);
				// g2d.drawString(text2, -73, 203);
				drawJustifyString(text2, g2d, 469, -73, 188);
			} else {
				/*
				 * textos =
				 * StringsUtils.cadenaTexto(StringsUtils.fullJustify(StringsUtils.singleChars(
				 * textos), 40)); g2d.drawString(textos, -73, 185);
				 */
				drawJustifyString(textos, g2d, 469, -73, 180);
			}

			g2d.drawString("SEROLÓGICO: " + listarAlumnosIngresantes().get(pageIndex).getDescripcionRPR(), -72, 245);
			g2d.drawString(
					"RESULTADO: " + listarAlumnosIngresantes().get(pageIndex).getDescripcionResultadoPsicologico(), 182,
					245);
			g2d.drawString("GRUPO SANGUÍNEO: " + listarAlumnosIngresantes().get(pageIndex).getDescGrupoSanguineo(), -72,
					263);
			g2d.drawString("FACTOR Rh: " + listarAlumnosIngresantes().get(pageIndex).getDescFactorRh(), -72, 281);
			g2d.drawString(
					"RESULTADO: " + listarAlumnosIngresantes().get(pageIndex).getDescripcionResultadoRadiologico(), -72,
					325);
			g2d.drawString("REVISADO", 182, 325);
			g2d.drawString("RESULTADO:", -72, 375);
			if (listarAlumnosIngresantes().get(pageIndex).getResultado().equals("N")) {
				g2d.drawString("NORMAL", -60, 392);
			} else {
				g2d.drawString("CONSULTA: " + listarAlumnosIngresantes().get(pageIndex).getDiagnostico(), -60, 392);
			}
			g2d.drawString("ALERGIAS: " + listarAlumnosIngresantes().get(pageIndex).getAlergia(), -60, 410);
			g2d.drawString("Se expide el presente a solicitud del interesado(a).", -72, 450);
			if (listarAlumnosIngresantes().get(pageIndex).getIdResultadoPsicologico().equals("O")) {
				g2d.drawString("Se recomienda consulta psicológica.", -25, 468);
			}

			g3d.translate(pf.getImageableX(), pf.getImageableY());
			g3d.setFont(fnt3);
			g3d.setColor(Color.black);
			g3d.drawString("CLINICA UNIVERSITARIA SERVICIOS MÉDICOS", -68, 2);

			g4d.translate(pf.getImageableX(), pf.getImageableY());
			g4d.setFont(fnt4);
			g4d.setColor(Color.black);
			g4d.drawString("CERTIFICADO DE CHEQUEO MÉDICO", -105, -50);

			g5d.translate(pf.getImageableX(), pf.getImageableY());
			g5d.setFont(fnt5);
			g5d.setColor(Color.black);
			g5d.drawString("_______________________________________________________", -20, 315);
			g5d.drawString("Dr. " + director.getNombres() + " " + director.getApellidoPaterno() + " "
					+ director.getApellidoMaterno(), 10, 329);
			g5d.drawString("DIRECTOR(a)", 40, 343);
			g5d.drawString(
					"CMP N° " + director.getColegioMedico() + " RNE: " + director.getRegistroNacionalEspecialidad(), 10,
					357);

			// marca de agua

			AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.08f);
			g6d.setComposite(alphaChannel);
			g6d.drawImage(image2, -290, -200, 470, 560, null);

			return Printable.PAGE_EXISTS;
		}

		public void drawCenteredString(String s, int w, int h, Graphics2D g) {
			FontMetrics fm = g.getFontMetrics();
			int x = (w - fm.stringWidth(s)) / 2;
			g.drawString(s, x, h);
		}

		// -73, 185
		public void drawJustifyString(String s, Graphics2D g, int d, int x1, int y1) {
			StringTokenizer st = new StringTokenizer(s);

			FontMetrics fm = g.getFontMetrics();
			int space = fm.stringWidth(" ");
			int x = 0;
			int nextx;
			int y = y1;
			String word, sp;
			int wordCount = 0;
			String line = "";
			int ascent = fm.getAscent();
			int fh = ascent + fm.getDescent();

			while (st.hasMoreTokens()) {
				word = st.nextToken();

				int w = fm.stringWidth(word);
				if ((nextx = (x + space + w)) > d) {
					drawString(g, line, wordCount, fm.stringWidth(line), y + ascent, fm, d, space, x1);
					line = "";
					wordCount = 0;
					x = 0;
					y = y + fh;
				}
				if (x != 0) {
					sp = " ";
				} else {
					sp = "";
				}
				line = line + sp + word;
				x = x + space + w;
				wordCount++;
			}
			drawString(g, line, wordCount, fm.stringWidth(line), y + ascent, fm, d, space, x1);
		}

		public void drawString(Graphics g, String line, int wc, int lineWidth, int y, FontMetrics fm, int d, int space,
				int x1) {
			if (lineWidth < (int) (d * .75)) {
				g.drawString(line, -73, y);
			} else {
				int toFill = (int) ((d - lineWidth) / wc);
				int nudge = d - lineWidth - (toFill * wc);
				StringTokenizer st = new StringTokenizer(line);
				int x = -73;
				while (st.hasMoreTokens()) {
					String word = st.nextToken();
					g.drawString(word, x, y);
					if (nudge > 0) {
						x = x + fm.stringWidth(word) + space + toFill + 1;
						nudge--;
					} else {
						x = x + fm.stringWidth(word) + space + toFill;
					}
				}
			}
		}
	}

}
