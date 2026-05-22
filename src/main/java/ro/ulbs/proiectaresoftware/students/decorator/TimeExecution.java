package ro.ulbs.proiectaresoftware.students.decorator;

import ro.ulbs.proiectaresoftware.students.IStudentiExport;
import ro.ulbs.proiectaresoftware.students.Student;
import java.util.List;

public abstract class TimeExecution implements ITimeExecution {

    protected IStudentiExport exporter;

    public TimeExecution(IStudentiExport exporter) {
        this.exporter = exporter;
    }

    @Override
    public long executionTime(List<Student> studenti) {
        long start = System.currentTimeMillis();
        exporter.doExport(studenti);
        long end = System.currentTimeMillis();
        return end - start;
    }
}