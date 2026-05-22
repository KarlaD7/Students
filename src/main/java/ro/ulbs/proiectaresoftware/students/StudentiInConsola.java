package ro.ulbs.proiectaresoftware.students;

import java.util.List;

public class StudentiInConsola implements IStudentiExport {

    @Override
    public void doExport(List<Student> studenti) {
        System.out.println("=== Export studenti in consola ===");
        for (Student student : studenti) {
            System.out.println(student);
        }
    }
}
