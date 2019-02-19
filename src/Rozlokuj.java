import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class Rozlokuj {


    public static String Rozlokuj_nowy() throws IOException {
        //Wprowadanie danych wejsciowych
        String plik_kontenery = Funkcje.czytaj_kontenery();
        String baza_statki = Funkcje.czytaj_statki();
        String plik_statki = (baza_statki + ".csv");

        int ilosc_lini = Funkcje.ilosc_lini(plik_kontenery)-1;
        System.out.println("Rozpoczynam rozlokowywanie " + ilosc_lini + " kontenerow na statki z bazy " + baza_statki);
        String [][] kontenery = Funkcje.czytaj_z_csv(plik_kontenery);
        System.out.println(Arrays.deepToString(kontenery));
        Arrays.sort(kontenery, new Comparator<String[]>() {
            @Override
            public int compare(String[] a, String[] b) {
                return Double.compare(Double.parseDouble(a[4]), Double.parseDouble(b[4]));
            }
        });

        System.out.println("Posortowana tabela: \n" + Arrays.deepToString(kontenery));
       // String [][] tmp = new String [kontenery.length+3][(kontenery.length+3)];
       // tmp[0][5]="1";
       // System.out.println("Tymczasowa tabela: \n" + Arrays.deepToString(tmp));

        //System.arraycopy(kontenery,0,tmp,0,(kontenery.length));
              // tmp = Arrays.copyOf(kontenery, kontenery.length);
       // tmp[0][5]="1";

        //Dodawanie kolumn do tabeli z kontenerami + kalkulowanie pojemnosci i wspolczynnika pojemnosc/cena*1000
        for(int i=0; i<kontenery.length; i++) {

            kontenery[i] = Arrays.copyOf(kontenery[i], kontenery.length + 3);
            kontenery[i][5] = Float.toString((Float.parseFloat(kontenery[i][1]) * Float.parseFloat(kontenery[i][2]) * Float.parseFloat(kontenery[i][3])));
            kontenery[i][6] = Float.toString((Float.parseFloat(kontenery[i][4])/Float.parseFloat(kontenery[i][5])*1000));
            //kontenery[i][7] = "0";
            //Kolumna [i][7] bedzie zawierać ID statku na ktory zostala załadowana, jesli jest null to kontener nadal nie zostal zaladowany

        }
        System.out.println("Rozszerzona tabela: \n" + Arrays.deepToString(kontenery));
        System.out.println("Jaki algorytm rozlokowania chcesz użyć?\n [1]Zachlanny\n [2]Rozmiar/Cena"\n)
                int algorytm =Funkcje.czytaj_int();
        switch (algorytm) {
            case 1:
                String [][] raport = algorytm_zachlanny();
                System.out.println("Raport z rozlokowywania: \n" + Arrays.deepToString(raport));
        }
       // System.out.println("Tymczasowa tabela recznie: \n" + tmp[0][0] + tmp[0][4]);


        /*    for(int y=0; y< kontenery[x].length; y++)
                System.out.print(kontenery[x][y] + "    ");
            System.out.println();
        }
*/

    return ("Skonczylem rozlokowanie");
    }
    public static String[][] algorytm_zachlanny(String [][] kontenery) throws IOException {
        Arrays.sort(kontenery, new Comparator<String[]>() {
            @Override
            public int compare(String[] a, String[] b) {
                return Double.compare(Double.parseDouble(a[4]), Double.parseDouble(b[4]));
            }
        });





    }
}