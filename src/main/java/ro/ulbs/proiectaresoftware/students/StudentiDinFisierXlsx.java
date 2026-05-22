package ro.ulbs.proiectaresoftware.students;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentiDinFisierXlsx {

    private final String fileName;

    public StudentiDinFisierXlsx(String fileName) {
        this.fileName = fileName;
    }

    public List<Student> doImport() {
        List<Student> studenti = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(fileName);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                int numarMatricol = (int) row.getCell(0).getNumericCellValue();
                String prenume = row.getCell(1).getStringCellValue();
                String nume = row.getCell(2).getStringCellValue();
                String formatieDeStudiu = row.getCell(3).getStringCellValue();
                double nota = row.getCell(4).getNumericCellValue();

                studenti.add(new Student(numarMatricol, prenume, nume, formatieDeStudiu, nota));
            }
            System.out.println("Import xlsx realizat cu succes: " + fileName);
        } catch (IOException e) {
            System.err.println("Eroare la import xlsx: " + e.getMessage());
        }
        return studenti;
    }
}