package com.example.proyectosena.formats;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.example.proyectosena.models.entity.Nomina;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class NominaExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Nomina> nominas;

    public NominaExcelExporter(List<Nomina> nominas) {
        this.nominas = nominas;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Nominas");
        XSSFRow row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Cedula Empleado", style);
        createCell(row, 1, "Salario Base", style);
        createCell(row, 2, "Auxilio transporte", style);
        createCell(row, 3, "Bono Cumpleaños", style);
        createCell(row, 4, "Valor plan celular", style);
        createCell(row, 5, "Descuento salud y pensión", style);
        createCell(row, 6, "Total devegado", style);

    }

    private void createCell(XSSFRow row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Nomina nomina : nominas) {
            XSSFRow row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, nomina.getEmpleado().getCedula(), style);
            createCell(row, columnCount++, String.valueOf(nomina.getEmpleado().getSueldo()), style);
            createCell(row, columnCount++, String.valueOf(nomina.getAuxilio_transporte()), style);
            createCell(row, columnCount++, String.valueOf(nomina.getBono_cumpleanos()), style);
            createCell(row, columnCount++, String.valueOf(nomina.getVal_plan_celular()), style);
            createCell(row, columnCount++, String.valueOf(nomina.getDescuento_pension()), style);
            createCell(row, columnCount++, String.valueOf(nomina.getTotal_devegado()), style);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
