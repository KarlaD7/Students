package ro.ulbs.proiectaresoftware.students;

public class Application {public static void main(String[] args) {
    Student s1 = new Student(112, "Ioan", "Popa", "TI21/1");
    Student s2 = new Student(112, "Maria", "Oprea", "TI21/1");
    Student s3 = new Student(120, "Alis",  "Popa", "TI21/2");
    Student s4 = new Student(122, "Mihai", "Vecerdea", "TI22/1");
    Student s5 = new Student(122, "Eugen", "Uritescu", "TI22/2");

    System.out.println(String.format("%15s %15s %15s %15s", "numar matricol", "prenume", "nume", "formatieDeStudiu"));

    System.out.println(s1);
    System.out.println(s2);
    System.out.println(s3);
    System.out.println(s4);
    System.out.println(s5);
}
}
