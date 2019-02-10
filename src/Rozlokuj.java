import java.io.*;
import java.io.File;

public class Rozlokuj {


    public static String Rozlokuj_nowy() throws IOException {
        int status = 0;
        String plik_kontenery = Funkcje.czytaj_kontenery();
        String baza_statki = Funkcje.czytaj_statki();
        String plik_statki = (baza_statki + ".csv");

        int ilosc_lini = Funkcje.ilosc_lini(plik_kontenery)-1;
        System.out.println("Rozpoczynam rozlokowywanie " + ilosc_lini + " kontenerow na statki z bazy " + baza_statki);


    return ("Skonczylem rozlokowanie");
    }
}
