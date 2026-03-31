package ro.ulbs.proiectaresoftware.students;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainBursieri {
    public static void main(String[] args) {
        List<StudentBursieri> bursieri = new ArrayList<>();

        bursieri.add(new StudentBursieri(1025, "Andrei", "Popa", "ISM141/2", 8.70, 725.50));
        bursieri.add(new StudentBursieri(1024, "Ioan", "Mihalcea", "ISM141/1", 9.80, 801.10));
        bursieri.add(new StudentBursieri(1026, "Anamaria", "Prodan", "TI131/1", 8.90, 745.50));
        bursieri.add(new StudentBursieri(1029, "Bianca", "Popescu", "TI131/1,", 9.10, 780.80));

        writeToFile("bursieri_out.txt", bursieri);
    }
        public static void writeToFile(String filename, Collection<? extends Student> lista) {
            try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
                for (Student s : lista) {
                    out.println(s.toString());
                }
                System.out.println("Fisierul " + filename + " a fost generat cu succes.");
            } catch (IOException e) {
                System.err.println("Eroare la scriere: " + e.getMessage());
            }
    }
}
