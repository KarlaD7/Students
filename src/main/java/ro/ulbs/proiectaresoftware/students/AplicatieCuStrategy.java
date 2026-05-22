package ro.ulbs.proiectaresoftware.students;

import java.util.Arrays;
import java.util.List;

public class AplicatieCuStrategy {

    public static void main(String[] args) {

        List<Student> studenti = Arrays.asList(
                new Student(1025, "Andrei", "Popa", "ISM141/2", 8.70),
                new Student(1024, "Ioan", "Mihalcea", "ISM141/1", 10),
                new Student(1026, "Anamaria", "Prodan", "TI131/1", 8.90),
                new Student(1029, "Bianca", "Popescu", "TI131/1", 10),
                new Student(1029, "Maria", "Pana", "TI131/,", 4.10),
                new Student(1029, "Gabriela", "Mohanu", "TI131/2", 7.33),
                new Student(1029, "Marius", "Nasta", "TI131/2", 3.20),
                new Student(1029, "Marius", "Nasta", "TI131/1", 5.12),
                new Student(1029, "Andrei", "Dobrescu", "TI131/2", 2.22)
        );

        Exporter exporter = new Exporter();

        // a) afisare in consola
        IStudentiExport strategyConsole = new StudentiInConsola();
        exporter.startExport(strategyConsole, studenti);

        // b) export in fisier txt
        String fileTxt = "studentiStrategyText.txt";
        StudentiInFisierText strategyFisierText = new StudentiInFisierText(fileTxt);
        exporter.startExport(strategyFisierText, studenti);

        // c) export in fisier xlsx
        String fileXlsx = "studentiStrategyExcel.xlsx";
        StudentiInFisierXlsx strategyFisierExcel = new StudentiInFisierXlsx(fileXlsx);
        exporter.startExport(strategyFisierExcel, studenti);

        // d) citire din fisier txt
        StudentiDinFisierText importTxt = new StudentiDinFisierText(fileTxt);
        List<Student> studentiDinTxt = importTxt.doImport();
        System.out.println("\n=== Studenti cititi din txt ===");
        studentiDinTxt.forEach(System.out::println);

        // e) citire din fisier xlsx
        StudentiDinFisierXlsx importXlsx = new StudentiDinFisierXlsx(fileXlsx);
        List<Student> studentiDinXlsx = importXlsx.doImport();
        System.out.println("\n=== Studenti cititi din xlsx ===");
        studentiDinXlsx.forEach(System.out::println);
    }
}