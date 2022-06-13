package com.example.proyectosena.formats;

import com.example.proyectosena.models.entity.Nomina;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class NominaPDFExporter {
    private List<Nomina> nominas;

    public NominaPDFExporter(List<Nomina> nominas){
        this.nominas= nominas;
    }
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.black);

        cell.setPhrase(new Phrase("Cedula Empleado", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Salario Base", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Auxilio transporte", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Bono Cumpleaños", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Valor plan celular", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Descuento salud y pensión", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Total devegado", font));
        table.addCell(cell);
    }
    private void writeTableData(PdfPTable table) {
        for (Nomina nomina : nominas) {
            table.addCell(nomina.getEmpleado().getCedula());
            table.addCell(String.valueOf(nomina.getEmpleado().getSueldo()));
            table.addCell(String.valueOf(nomina.getAuxilio_transporte()));
            table.addCell(String.valueOf(nomina.getBono_cumpleanos()));
            table.addCell(String.valueOf(nomina.getVal_plan_celular()));
            table.addCell(String.valueOf(nomina.getDescuento_pension()));
            table.addCell(String.valueOf(nomina.getTotal_devegado()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(10);

        Paragraph p = new Paragraph("Lista de la liquidación de nomina", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
