package ro.ulbs.proiectaresoftware.students;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        // a) Crearea unei liste din lista de instante Student
        List<Student> listaStudenti = new ArrayList<>();

        listaStudenti.add(new Student(112, "Ioan", "Popa", "TI21/1"));
        listaStudenti.add(new Student(112, "Maria", "Oprea", "TI21/1"));
        listaStudenti.add(new Student(120, "Alis", "Popa", "TI21/2"));
        listaStudenti.add(new Student(122, "Mihai", "Vecerdea", "TI22/1"));
        listaStudenti.add(new Student(122, "Eugen", "Uritescu", "TI22/2"));

        System.out.println("lista de studenti");
        for (Student s : listaStudenti) {
            System.out.println(s);
        }

        // b) si c) Verificarea prezentei studentilor
        Student sCautantB = new Student(120, "Alis", "Popa", "TI21/2");
        Student sCautantC = new Student(112, "Maria", "Popa", "TI21/1");

        System.out.println("\nRezultate verificare:");
        System.out.println("Studentul Alis Popa (b) este prezent? " + existaStudent(listaStudenti, sCautantB));
        System.out.println("Studentul Maria Popa (c) este prezent? " + existaStudent(listaStudenti, sCautantC));
    }


    public static boolean existaStudent(List<Student> lista, Student s) {
        for (Student studentDinLista : lista) {
            if (studentDinLista.getPrenume().equals(s.getPrenume()) &&
                    studentDinLista.getNume().equals(s.getNume()) &&
                    studentDinLista.getFormatieDeStudiu().equals(s.getFormatieDeStudiu())) {
                return true;
            }
        }
        return false;
    }
}