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
        String [][] kontenery = Funkcje.czytaj_z_csv_kontenery(plik_kontenery);
        String [][] statki = Funkcje.czytaj_z_csv_statki(plik_statki);
        System.out.println(Arrays.deepToString(kontenery));
        System.out.println(Arrays.deepToString(statki));
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



        System.out.println("Jaki algorytm rozlokowania chcesz użyć?\n [1]Zachlanny\n [2]Sprytny (Rozmiar/Cena)\n");
                int algorytm =Funkcje.czytaj_int();
        switch (algorytm) {
            case 1:
                String [][] raport = algorytm_zachlanny(kontenery, statki);
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
    public static String[][] algorytm_zachlanny(String [][] kontenery, String [][] statki) throws IOException {

        int status_ladowania=0;
        //Przygotowanie tabeli kontenerow
        //Dodawanie kolumn do tabeli z kontenerami + kalkulowanie pojemnosci
        for(int i=0; i<kontenery.length; i++) {

            kontenery[i] = Arrays.copyOf(kontenery[i], kontenery.length + 3);
            kontenery[i][5] = Float.toString((Float.parseFloat(kontenery[i][1]) * Float.parseFloat(kontenery[i][2]) * Float.parseFloat(kontenery[i][3]))/1000);
            kontenery[i][6] = Float.toString((Float.parseFloat(kontenery[i][4])/Float.parseFloat(kontenery[i][5])));
            //kontenery[i][7] = "0";
            //Kolumna [i][7] bedzie zawierać ID statku na ktory zostala załadowana, jesli jest null to kontener nadal nie zostal zaladowany

        }
        System.out.println("Rozszerzona tabela: \n" + Arrays.deepToString(kontenery));

        //Sortowanie kontenerów po wartości
        Arrays.sort(kontenery, new Comparator<String[]>() {
            @Override
            public int compare(String[] a, String[] b) {
                return Double.compare(Double.parseDouble(a[5]), Double.parseDouble(b[5]));
            }
        });
        System.out.println("Po sortowaniu w zachlonnym: \n" + Arrays.deepToString(kontenery));

        //Obliczanie lacznego ladunku kontenerow
        double suma_ladunku = 0;
        for(int i=0; i<kontenery.length; i++){
            suma_ladunku += Double.parseDouble(kontenery[i][5]);
        }
        System.out.println("suma ladunku: " + suma_ladunku);


        //Przygotowanie tabeli statkow
        //Dodawanie kolumn do tabeli ze statkami + kalkulowanie pojemnosci
        for(int i=0; i<statki.length; i++) {

            statki[i] = Arrays.copyOf(statki[i], statki.length + 2);
            statki[i][4] = Float.toString((Float.parseFloat(statki[i][1]) * Float.parseFloat(statki[i][2]) * Float.parseFloat(statki[i][3]))/100);
            statki[i][5]=Double.toString(0);
            //Kolumna [i][5] bedzie zawierac informacje jaka ilosc/ladunek kontenerow zostal juz zaladowany na statek
           //Kolumna [i][6] bedzie zawierać status statku - czy jest on zajety/zaladowany czy nie
        }
        System.out.println("Rozszerzona tabela: \n" + Arrays.deepToString(statki));


        //Sortowanie statkow po pojemnosci
        Arrays.sort(statki, new Comparator<String[]>() {
            @Override
            public int compare(String[] a, String[] b) {
                return Double.compare(Double.parseDouble(a[4]), Double.parseDouble(b[4]));
            }
        });
        System.out.println("Po sortowaniu w statkow: \n" + Arrays.deepToString(statki));

        while(suma_ladunku>0 && status_ladowania==0) {
            int x = 0;
            int y = 0;
            //Wybieranie statku do zaladowania
            for (int i = 0; i < statki.length; i++) {
                if(statki[i][6]==null) {
                    if ((Double.parseDouble(statki[i][4]) >= suma_ladunku)) {
                        x = i;
                        y = 4;
                        System.out.println("sprawdzam x, y" + x + "  " + y);
                    } else break;
                }
            }
            if (y == 0) {
                for(int i=0;i<statki.length;i++){
                    if((statki[i][6])==null){
                        x=i;
                        y=4;
                        System.out.println("sprawdzam drugi raz x, y" + x +"  " + y);
                    }
                    else{
                        System.out.println("Nie ma juz dostepnych statkow, niektore kontenery nie zostaly zaladowane, sprawdz raport");
                        status_ladowania=2;
                        break;
                    }
                }
            }
            //Ladujemy kontenery na statek
            System.out.println("sprawdzam 3");
            for(int i=0;i<kontenery.length;i++){
                if(kontenery[i][7]==null){
                   kontenery[i][7]=statki[x][0];
                   suma_ladunku -= Double.parseDouble(kontenery[i][5]);
                   statki[x][5] = Double.toString(Double.parseDouble(statki[x][5]) + Double.parseDouble(kontenery[i][5]));
                   if(Double.parseDouble(kontenery[i+1][5]) > (Double.parseDouble(statki[x][4]) - Double.parseDouble(statki[x][5]))){
                       statki[x][6]="1";
                   }
                   else {
                       System.out.println("sprawdzam 4");
                       break;
                   }

                }
                else break;
                System.out.println("sprawdzam 5");

            }
            if(kontenery[kontenery.length-1][7]!=null){status_ladowania=1;}
            System.out.println("sprawdzam 6" + status_ladowania);
            System.out.println("kontenery: \n" + Arrays.deepToString(kontenery));



        }

        System.out.println("status ladowania: " + status_ladowania + "\nSuma ladunku: " + suma_ladunku);
        System.out.println("Statki: \n" + Arrays.deepToString(statki));
        return kontenery;
    }


}