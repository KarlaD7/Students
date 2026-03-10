package ro.ulbs.proiectaresoftware.students;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Application {
    public static void main(String[] args) {
        List<Student> listaStudenti = new ArrayList<>();

        listaStudenti.add(new Student(112, "Ioan", "Popa", "TI21/1"));
        listaStudenti.add(new Student(112, "Maria", "Oprea", "TI21/1"));
        listaStudenti.add(new Student(120, "Alis", "Popa", "TI21/2"));
        listaStudenti.add(new Student(122, "Mihai", "Vecerdea", "TI22/1"));
        listaStudenti.add(new Student(122, "Eugen", "Uritescu", "TI22/2"));

        // tema 2.5.3


        Set<Student> setStudenti = new HashSet<>(listaStudenti);


        Student s1 = new Student(120, "Alis", "Popa", "TI21/2");
        Student s2 = new Student(112, "Maria", "Popa", "TI21/1");


        System.out.println("Studentul Alis Popa este prezent? " + setStudenti.contains(s1));
        System.out.println("Studentul Maria Popa este prezent? " + setStudenti.contains(s2));
    }
}