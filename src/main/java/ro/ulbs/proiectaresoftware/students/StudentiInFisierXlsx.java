package ro.ulbs.proiectaresoftware.students;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class StudentiInFisierXlsx implements IStudentiExport {

    private final String fileName;

    public StudentiInFisierXlsx(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void doExport(List<Student> studenti) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Studenti");

            // header
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Nr. Matricol");
            header.createCell(1).setCellValue("Prenume");
            header.createCell(2).setCellValue("Nume");
            header.createCell(3).setCellValue("Formatie de studiu");
            header.createCell(4).setCellValue("Nota");

            // date
            int rowIndex = 1;
            for (Student student : studenti) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(student.getNumarMatricol());
                row.createCell(1).setCellValue(student.getPrenume());
                row.createCell(2).setCellValue(student.getNume());
                row.createCell(3).setCellValue(student.getFormatieDeStudiu());
                row.createCell(4).setCellValue(student.getNota());
            }

            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                workbook.write(fos);
            }
            System.out.println("Export xlsx realizat cu succes: " + fileName);
        } catch (IOException e) {
            System.err.println("Eroare la export xlsx: " + e.getMessage());
        }
    }
}