package ro.ulbs.proiectaresoftware.students;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentiDinFisierText {

    private final String fileName;

    public StudentiDinFisierText(String fileName) {
        this.fileName = fileName;
    }

    public List<Student> doImport() {
        List<Student> studenti = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length >= 5) {
                    int numarMatricol = Integer.parseInt(parts[0].trim());
                    String prenume = parts[1].trim();
                    String nume = parts[2].trim();
                    String formatieDeStudiu = parts[3].trim();
                    double nota = Double.parseDouble(parts[4].trim());
                    studenti.add(new Student(numarMatricol, prenume, nume, formatieDeStudiu, nota));
                }
            }
            System.out.println("Import txt realizat cu succes: " + fileName);
        } catch (IOException e) {
            System.err.println("Eroare la import txt: " + e.getMessage());
        }
        return studenti;
    }
}