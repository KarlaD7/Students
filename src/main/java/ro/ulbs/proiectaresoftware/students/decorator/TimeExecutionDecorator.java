package ro.ulbs.proiectaresoftware.students.decorator;

import ro.ulbs.proiectaresoftware.students.IStudentiExport;
import ro.ulbs.proiectaresoftware.students.Student;
import java.util.List;

public class TimeExecutionDecorator extends TimeExecution {

    private List<Student> studenti;

    public TimeExecutionDecorator(IStudentiExport exporter, List<Student> studenti) {
        super(exporter);
        this.studenti = studenti;
    }

    @Override
    public long executionTime(List<Student> studenti) {
        long execTime = super.executionTime(studenti);
        return execTime;
    }
}