package ro.ulbs.proiectaresoftware.students;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Application {
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
                        date[3].trim()
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
                        s.setNota(valoareNota);
                    }
                }
            }
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
        }
        catch (IOException e){
            System.out.println("Eroare");
            e.printStackTrace();
        }
        catch (NumberFormatException e) {
            System.out.println("Eroare la formatul datelor ");
            e.printStackTrace();
        }
    }
}