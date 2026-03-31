package ro.ulbs.proiectaresoftware.students;

public class StudentBursieri extends Student{
    private double cuantumBursa;

    public StudentBursieri(int id, String nume, String prenume, String grupa, double medie, double cuantumBursa){
        super(id, nume, prenume, grupa);
        setNota(medie);
        this.cuantumBursa=cuantumBursa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StudentBursieri that = (StudentBursieri) o;
        return Double.compare(that.cuantumBursa, cuantumBursa) == 0;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), cuantumBursa);
    }

    @Override
    public String toString() {
        return super.toString() + ", cuantumBursa=" + cuantumBursa;
    }
}
