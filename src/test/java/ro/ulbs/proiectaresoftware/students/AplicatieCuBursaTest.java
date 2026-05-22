
package ro.ulbs.proiectaresoftware.students;

import org.junit.jupiter.api.*;
        import java.util.List;

public class AplicatieCuBursaTest {

    AplicatieCuBursa appCuBursa = new AplicatieCuBursa();

    @Test
    void sortTest1() {
        List<StudentBursier> lista = appCuBursa.genereaza();

        List<StudentBursier> sortata = appCuBursa.sorteaza(lista);

        for (int i = 0; i < sortata.size() - 1; i++) {
            StudentBursier s1 = sortata.get(i);
            StudentBursier s2 = sortata.get(i + 1);

            int cmpGrupa = s1.getFormatieDeStudiu().compareTo(s2.getFormatieDeStudiu());
            if (cmpGrupa != 0) {
                Assertions.assertTrue(cmpGrupa <= 0, "Grupa nu e sortata corect");
                continue;
            }

            int cmpNume = s1.getNume().compareTo(s2.getNume());
            if (cmpNume != 0) {
                Assertions.assertTrue(cmpNume <= 0, "Numele nu e sortat corect");
                continue;
            }

            int cmpPrenume = s1.getPrenume().compareTo(s2.getPrenume());
            if (cmpPrenume != 0) {
                Assertions.assertTrue(cmpPrenume <= 0, "Prenumele nu e sortat corect");
                continue;
            }

            int cmpMedie = Double.compare(s1.getNota(), s2.getNota());
            if (cmpMedie != 0) {
                Assertions.assertTrue(cmpMedie <= 0, "Media nu e sortata corect");
                continue;
            }

            Assertions.assertTrue(
                    Double.compare(s1.getCuantumBursa(), s2.getCuantumBursa()) <= 0,
                    "Cuantumul bursei nu e sortat corect"
            );
        }
    }

    @Test
    void sortTestListaGoala() {
        List<StudentBursier> listaGoala = new java.util.ArrayList<>();
        List<StudentBursier> sortata = appCuBursa.sorteaza(listaGoala);
        Assertions.assertTrue(sortata.isEmpty());
    }

    @Test
    void sortTestPrimulElementDupaGrupa() {
        List<StudentBursier> lista = appCuBursa.genereaza();
        List<StudentBursier> sortata = appCuBursa.sorteaza(lista);
        Assertions.assertEquals("ISM141/1", sortata.get(0).getFormatieDeStudiu());
    }
}