package com.mycompany.clases;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.formularios.frmReporteFacturas;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reporte {

    public static void reporteFacturas(String archivo, ResultSet rs) {
        Document documento = new Document();    
        try {
            PdfWriter.getInstance(documento, new FileOutputStream(archivo));

            // Colocamos el título del reporte
            String texto = "REPORTE FACTURAS";
            Paragraph parrafo = new Paragraph(texto);
            documento.open();
            documento.add(parrafo);
            parrafo = new Paragraph(" ");
            documento.add(parrafo);

            boolean hayRegistros = rs.next();
            PdfPTable tabla;

            // Declaramos variables de totales
            int totCanFac = 0;
            int totValFac = 0;
            int totCanGen = 0;
            int totValGen = 0;

            // Iniciamos el cuerpo del reporte
            while (hayRegistros) {
                // Imprimimos encabezado de la factura
                tabla = new PdfPTable(2);
                tabla.addCell("Factura #:");
                tabla.addCell(rs.getString("idFactura"));
                tabla.addCell("ID Cliente:");
                tabla.addCell(rs.getString("idCliente"));
                tabla.addCell("Nombre:");
                tabla.addCell(rs.getString("nombreFull"));
                tabla.addCell("Fecha:");
                tabla.addCell(rs.getString("fecha"));

                parrafo = new Paragraph();
                parrafo.add(tabla);
                documento.add(parrafo);
                parrafo = new Paragraph(" ");
                documento.add(parrafo);

                // Inicializamos totales de factura
                totCanFac = 0;
                totValFac = 0;

                // Colocamos los encabezados del detalle
                tabla = new PdfPTable(6);
                tabla.addCell("Linea");
                tabla.addCell("ID Producto");
                tabla.addCell("Descripción");
                tabla.addCell("Precio");
                tabla.addCell("Cantidad");
                tabla.addCell("Valor");
                parrafo = new Paragraph();
                parrafo.add(tabla);
                documento.add(parrafo);

                int facturaActual = rs.getInt("idFactura");
                while (hayRegistros && facturaActual == rs.getInt("idFactura")) {
                    tabla = new PdfPTable(6);
                    tabla.addCell(rs.getString("idLinea"));
                    tabla.addCell(rs.getString("idProducto"));
                    tabla.addCell(rs.getString("descripcion"));
                    tabla.addCell(rs.getString("precio"));
                    tabla.addCell(rs.getString("cantidad"));
                    tabla.addCell(rs.getString("valor"));
                    parrafo = new Paragraph();
                    parrafo.add(tabla);
                    documento.add(parrafo);

                    totCanFac += rs.getInt("cantidad");
                    totValFac += rs.getInt("valor");

                    hayRegistros = rs.next();
                }

                // Imprimir totales
                tabla = new PdfPTable(6);
                tabla.addCell(" ");
                tabla.addCell(" ");
                tabla.addCell(" ");
                tabla.addCell("TOTAL FACTURA:");
                tabla.addCell("" + totCanFac);
                tabla.addCell("" + totValFac);
                parrafo = new Paragraph();
                parrafo.add(tabla);
                documento.add(parrafo);

                // Acumulamos totales generales
                totCanGen += totCanFac;
                totValGen += totValFac;

                // Colocamos un espacio en blanco
                parrafo = new Paragraph(" ");
                documento.add(parrafo);
            }

            // Imprimir totales generales
            tabla = new PdfPTable(6);
            tabla.addCell(" ");
            tabla.addCell(" ");
            tabla.addCell(" ");
            tabla.addCell("TOTAL GENERAL:");
            tabla.addCell("" + totCanGen);
            tabla.addCell("" + totValGen);
            parrafo = new Paragraph();
            parrafo.add(tabla);
            documento.add(parrafo);
        } catch (Exception ex) {
            Logger.getLogger(frmReporteFacturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            documento.close();
        }
    }
}
