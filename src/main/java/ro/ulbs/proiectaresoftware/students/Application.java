package ro.ulbs.proiectaresoftware.students;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Application {

    public static float gasesteNota(String prenume, String nume, Map<String, Student> mapStudenti) {
        String cheie = prenume + " " + nume;
        Student s = mapStudenti.get(cheie);
        return (s != null) ? (float) s.getNota() : 0.0f;
    }

    static Student schimbaFormatia(Student st, String nouaFormatieDeStudiu) {
        return new Student(
                st.getNumarMatricol(),
                st.getPrenume(),
                st.getNume(),
                nouaFormatieDeStudiu,
                st.getNota()
        );
    }

    static Set<Student> imparteInDouaFormatii(Set<Student> studenti, String formatia1, String formatia2) {
        List<Student> lista = new ArrayList<>(studenti);
        Set<Student> rezultat = new HashSet<>();
        int jumatate = lista.size() / 2;

        for (int i = 0; i < lista.size(); i++) {
            if (i < jumatate) {
                rezultat.add(schimbaFormatia(lista.get(i), formatia1));
            } else {
                rezultat.add(schimbaFormatia(lista.get(i), formatia2));
            }
        }
        return rezultat;
    }

    // 8.5.4 a)
    public static void writeToXls(Set<Student> studenti, String fileName) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Studenti");
            int rowNum = 0;

            for (Student st : studenti) {
                Row row = sheet.createRow(rowNum++);
                // Scriem datele in coloane: Matricol, Prenume, Nume, Formatie, Nota [cite: 41-45, 131]
                row.createCell(0).setCellValue(st.getNumarMatricol());
                row.createCell(1).setCellValue(st.getPrenume());
                row.createCell(2).setCellValue(st.getNume());
                row.createCell(3).setCellValue(st.getFormatieDeStudiu());
                row.createCell(4).setCellValue(st.getNota());
            }

            try (FileOutputStream out = new FileOutputStream(fileName)) {
                workbook.write(out);
            }
            System.out.println("\n[Laborator 8] Fisierul " + fileName + " a fost exportat.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 8.5.4 b)
    public static List<Student> readFromXls(String fileName) {
        List<Student> listaStudenti = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(fileName);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Student s = new Student(
                        (int) row.getCell(0).getNumericCellValue(),
                        row.getCell(1).getStringCellValue(),
                        row.getCell(2).getStringCellValue(),
                        row.getCell(3).getStringCellValue(),
                        row.getCell(4).getNumericCellValue()
                );
                listaStudenti.add(s);
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea din Excel.");
        }
        return listaStudenti;
    }

    public static void main(String[] args) {
        try {
            Path pathIn = Paths.get("studenti_in.txt");
            List<String> linii = Files.readAllLines(pathIn);

            List<Student> listaStudenti = new ArrayList<>();
            Map<Integer, Student> studentMap = new HashMap<>();

            for (String linie : linii) {
                if (linie.trim().isEmpty()) continue;
                String[] date = linie.split(",");
                Student s = new Student(
                        Integer.parseInt(date[0].trim()),
                        date[1].trim(),
                        date[2].trim(),
                        date[3].trim(),
                        0.0
                );
                listaStudenti.add(s);
                studentMap.put(s.getNumarMatricol(), s);
            }

            Path pathNote = Paths.get("note_anon.txt");
            if (Files.exists(pathNote)) {
                List<String> liniiNote = Files.readAllLines(pathNote);
                for (String linie : liniiNote) {
                    if (linie.trim().isEmpty()) continue;
                    String[] dateNota = linie.split(",");

                    int matricol = Integer.parseInt(dateNota[0].trim());
                    double valoareNota = Double.parseDouble(dateNota[1].trim());

                    Student s = studentMap.get(matricol);
                    if (s != null) {
                        Student sNou = new Student(
                                s.getNumarMatricol(),
                                s.getPrenume(),
                                s.getNume(),
                                s.getFormatieDeStudiu(),
                                valoareNota
                        );
                        studentMap.put(matricol, sNou);
                        listaStudenti.remove(s);
                        listaStudenti.add(sNou);
                    }
                }
            }

            Map<String, Student> tineri = new HashMap<>();
            for (Student s : listaStudenti) {
                tineri.put(s.getPrenume() + " " + s.getNume(), s);
            }

            float notaM = gasesteNota("Bianca", "Popescu", tineri);
            float notaN = gasesteNota("Ioan", "Popa", tineri);

            System.out.println("Nota pentru Bianca Popescu: " + notaM);
            System.out.println("Nota pentru Ioan Popa: " + notaN);

            Collections.sort(listaStudenti, Comparator.comparing(Student::getFormatieDeStudiu)
                    .thenComparing(Student::getNume));

            List<String> liniiDeIesire = new ArrayList<>();
            System.out.println("Studentii actualizati cu note (Sortati):");
            for (Student s : listaStudenti) {
                String studentString = s.toString();
                System.out.println(studentString);
                liniiDeIesire.add(studentString);
            }

            Path pathOut = Paths.get("studenti_out.txt");
            Files.write(pathOut, liniiDeIesire);
            System.out.println("\n studenti_out.txt");
            Path pathOutSorted = Paths.get("studenti_out_sorted.txt");
            Files.write(pathOutSorted, liniiDeIesire);

            // 7.6.3 b) impartire in doua formatii
            Set<Student> setStudenti = new HashSet<>(listaStudenti);
            setStudenti = imparteInDouaFormatii(setStudenti, "TI 211_1", "TI 211_2");
            System.out.println("\nStudentii impartiti in doua formatii:");
            for (Student s : setStudenti) {
                System.out.println(s);
            }

            String xlsFileName = "laborator8_students.xlsx";

            // a)
            writeToXls(setStudenti, xlsFileName);

            // b)
            List<Student> studentsFromXls = readFromXls(xlsFileName);
            System.out.println("\n[Laborator 8] Studenti cititi din xlsx:");
            for (Student st : studentsFromXls) {
                System.out.println(st);
            }

        } catch (IOException e) {
            System.out.println("Eroare");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Eroare la formatul datelor");
            e.printStackTrace();
        }
    }
}