package ro.ulbs.proiectaresoftware.students;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StudentiInFisierText implements IStudentiExport {

    private final String fileName;

    public StudentiInFisierText(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void doExport(List<Student> studenti) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Student student : studenti) {
                writer.write(student.toString());
                writer.newLine();
            }
            System.out.println("Export txt realizat cu succes: " + fileName);
        } catch (IOException e) {
            System.err.println("Eroare la export txt: " + e.getMessage());
        }
    }
}