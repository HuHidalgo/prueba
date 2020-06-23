package com.unmsm.clinica.utilitario;

import java.io.*;
import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;
import javax.print.event.*;

public class Imprimir {

	public Imprimir() {
		DocFlavor flavor = DocFlavor.INPUT_STREAM.POSTSCRIPT;
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		aset.add(MediaSizeName.ISO_A4);
		aset.add(new Copies(2));
		aset.add(Sides.TWO_SIDED_LONG_EDGE);
		aset.add(Finishings.STAPLE);

		PrintService[] pservices = PrintServiceLookup.lookupPrintServices(flavor, aset);
		if (pservices.length > 0) {
			System.out.println("selected printer " + pservices[0].getName());
			DocPrintJob pj = pservices[0].createPrintJob();
			try {
				FileInputStream fis = new FileInputStream("example.ps");
				Doc doc = new SimpleDoc(fis, flavor, null);
				pj.print(doc, aset);

			} catch (IOException ie) {
				System.err.println(ie);
			} catch (PrintException e) {
				System.err.println(e);
			}
		}
	}
}
