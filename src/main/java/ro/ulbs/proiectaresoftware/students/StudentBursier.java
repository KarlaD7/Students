package ro.ulbs.proiectaresoftware.students;

public class StudentBursier extends Student {
    private double cuantumBursa;

    public StudentBursier(int id, String nume, String prenume, String grupa, double medie, double cuantumBursa) {
        super(id, nume, prenume, grupa, medie);
        this.cuantumBursa = cuantumBursa;
    }

    public double getCuantumBursa() {
        return cuantumBursa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StudentBursier that = (StudentBursier) o;
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