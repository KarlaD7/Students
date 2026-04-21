package ro.ulbs.proiectaresoftware.students;

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

        } catch (IOException e) {
            System.out.println("Eroare");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Eroare la formatul datelor");
            e.printStackTrace();
        }
    }
}