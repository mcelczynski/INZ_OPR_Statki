import java.io.*;

public class Rozlokuj {


    public static String Rozlokuj_nowy() throws IOException {
        //Wprowadanie danych wejsciowych
        String plik_kontenery = Funkcje.czytaj_kontenery();
        String baza_statki = Funkcje.czytaj_statki();
        String plik_statki = (baza_statki + ".csv");

        int ilosc_lini = Funkcje.ilosc_lini(plik_kontenery)-1;
        System.out.println("Rozpoczynam rozlokowywanie " + ilosc_lini + " kontenerow na statki z bazy " + baza_statki);
        String [][] kontenery = Funkcje.czytaj_z_csv(plik_kontenery);

        for(int x=0; x< kontenery.length; x++){
            for(int y=0; y< kontenery[x].length; y++)
                System.out.print(kontenery[x][y] + "    ");
            System.out.println();
        }


    return ("Skonczylem rozlokowanie");
    }
}
