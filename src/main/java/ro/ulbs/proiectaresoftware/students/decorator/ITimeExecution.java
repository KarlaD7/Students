package ro.ulbs.proiectaresoftware.students.decorator;

import ro.ulbs.proiectaresoftware.students.Student;
import java.util.List;

public interface ITimeExecution {
    long executionTime(List<Student> studenti);
}