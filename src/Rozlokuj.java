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
                Raporty.generuj_raport(raport);
            case 2:
                String [][] raport2 = algorytm_sprytny(kontenery, statki);
                System.out.println("Raport z rozlokowywania: \n" + Arrays.deepToString(raport2));
                Raporty.generuj_raport(raport2);


            default: System.out.println("UWAGA!!!\nNiestety wybrany algorytm nie istnieje!\n Sprobujmy raz jeszcze\n!!!UWAGA\n");
        }



    return ("Skonczylem rozlokowanie");
    }
    public static String[][] algorytm_zachlanny(String [][] kontenery, String [][] statki) throws IOException {

        int status_ladowania=0;
        //Przygotowanie tabeli kontenerow
        //Dodawanie kolumn do tabeli z kontenerami + kalkulowanie pojemnosci
        for(int i=0; i<kontenery.length; i++) {

            kontenery[i] = Arrays.copyOf(kontenery[i], kontenery[i].length + 3);
            kontenery[i][5] = Double.toString((Double.parseDouble(kontenery[i][1]) * Double.parseDouble(kontenery[i][2]) * Double.parseDouble(kontenery[i][3])));
            kontenery[i][6] = Double.toString((Double.parseDouble(kontenery[i][4])/Double.parseDouble(kontenery[i][5]))*1.00);
            //kontenery[i][7] = "0";
            //Kolumna [i][7] bedzie zawierać ID statku na ktory zostala załadowana, jesli jest null to kontener nadal nie zostal zaladowany

        }
        System.out.println("Rozszerzona tabela: \n" + Arrays.deepToString(kontenery));

        //Sortowanie kontenerów po wartości
        Arrays.sort(kontenery, new Comparator<String[]>() {
            @Override
            public int compare(String[] a, String[] b) {
                return Double.compare(Double.parseDouble(b[4]), Double.parseDouble(a[4]));
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

            statki[i] = Arrays.copyOf(statki[i], statki[i].length + 3);
            statki[i][4] = Float.toString((Float.parseFloat(statki[i][1]) * Float.parseFloat(statki[i][2]) * Float.parseFloat(statki[i][3])));
            statki[i][5]=Double.toString(0);
            statki[i][6]="0";
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
        System.out.println("Po sortowaniu statkow: \n" + Arrays.deepToString(statki));

        //Ladowanie
        while(suma_ladunku>Double.parseDouble("0")){
            int x = 0;
            int y = 0;
            //Wybieranie statku do zaladowania
            for (int i = 0; i < statki.length; i++) {
                if(statki[i][6]=="0") {
                    if ((Double.parseDouble(statki[i][4]) - (Double.parseDouble(statki[i][5]))) >= suma_ladunku) {
                        x = i;
                        y = 4;
                        //System.out.println("sprawdzam x, y: " + x + ",  " + y);
                        break;
                    } else continue;
                }
            }
            if (y == 0) {
                for(int i=0;i<statki.length;i++){
                    if((statki[i][6])!="1"){
                        x=i;
                        y=4;
                        //System.out.println("sprawdzam drugi raz x, y" + x +"  " + y);
                        break;
                    }
                    else{
                         continue;
                    }
                }
            }
            //Ladujemy kontenery na statek
             for(int i=0;i<kontenery.length;i++){
                if(kontenery[i][7]==null){
                   kontenery[i][7]=statki[x][0];
                   suma_ladunku = (suma_ladunku - Double.parseDouble(kontenery[i][5]));
                   //System.out.println("Teraz suma ladunku wynosi: " + suma_ladunku);
                   statki[x][5] = Double.toString(Double.parseDouble(statki[x][5]) + Double.parseDouble(kontenery[i][5]));

                   if(i+1<kontenery.length) {
                       if (Double.parseDouble(kontenery[i + 1][5]) > (Double.parseDouble(statki[x][4]) - Double.parseDouble(statki[x][5]))) {
                           statki[x][6] = "1";
                           break;
                       } else {
                           continue;
                       }
                   }

                }
                else {

                    continue;
                }
            }
            if(kontenery[kontenery.length-1][7]!=null){break;}
            System.out.println("kontenery: \n" + Arrays.deepToString(kontenery));



        }

        System.out.println("Statki: \n" + Arrays.deepToString(statki));
        return kontenery;
    }
    public static String[][] algorytm_sprytny(String [][] kontenery, String [][] statki) throws IOException {

        int status_ladowania=0;
        //Przygotowanie tabeli kontenerow
        //Dodawanie kolumn do tabeli z kontenerami + kalkulowanie pojemnosci
        for(int i=0; i<kontenery.length; i++) {

            kontenery[i] = Arrays.copyOf(kontenery[i], kontenery[i].length + 3);
            kontenery[i][5] = Double.toString((Double.parseDouble(kontenery[i][1]) * Double.parseDouble(kontenery[i][2]) * Double.parseDouble(kontenery[i][3])));
            kontenery[i][6] = Double.toString((Double.parseDouble(kontenery[i][4])/Double.parseDouble(kontenery[i][5]))*1.00);
            //kontenery[i][7] = "0";
            //Kolumna [i][7] bedzie zawierać ID statku na ktory zostala załadowana, jesli jest null to kontener nadal nie zostal zaladowany

        }
        System.out.println("Rozszerzona tabela: \n" + Arrays.deepToString(kontenery));

        //Sortowanie kontenerów po stosunku wartosc/objetosc
        Arrays.sort(kontenery, new Comparator<String[]>() {
            @Override
            public int compare(String[] a, String[] b) {
                return Double.compare(Double.parseDouble(a[6]), Double.parseDouble(b[6]));
            }
        });
        System.out.println("Po sortowaniu w sprytnym: \n" + Arrays.deepToString(kontenery));

        //Obliczanie lacznego ladunku kontenerow
        double suma_ladunku = 0;
        for(int i=0; i<kontenery.length; i++){
            suma_ladunku += Double.parseDouble(kontenery[i][5]);
        }
        System.out.println("suma ladunku: " + suma_ladunku);


        //Przygotowanie tabeli statkow
        //Dodawanie kolumn do tabeli ze statkami + kalkulowanie pojemnosci
        for(int i=0; i<statki.length; i++) {

            statki[i] = Arrays.copyOf(statki[i], statki[i].length + 3);
            statki[i][4] = Float.toString((Float.parseFloat(statki[i][1]) * Float.parseFloat(statki[i][2]) * Float.parseFloat(statki[i][3])));
            statki[i][5]=Double.toString(0);
            statki[i][6]="0";
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
        System.out.println("Po sortowaniu statkow: \n" + Arrays.deepToString(statki));

        //Ladowanie
        while(suma_ladunku>Double.parseDouble("0")){
            int x = 0;
            int y = 0;
            //Wybieranie statku do zaladowania
            for (int i = 0; i < statki.length; i++) {
                if(statki[i][6]=="0") {
                    if ((Double.parseDouble(statki[i][4]) - (Double.parseDouble(statki[i][5]))) >= suma_ladunku) {
                        x = i;
                        y = 4;
                        //System.out.println("sprawdzam x, y: " + x + ",  " + y);
                        break;
                    } else continue;
                }
            }
            if (y == 0) {
                for(int i=0;i<statki.length;i++){
                    if((statki[i][6])!="1"){
                        x=i;
                        y=4;
                        //System.out.println("sprawdzam drugi raz x, y" + x +"  " + y);
                        break;
                    }
                    else{
                        continue;
                    }
                }
            }
            //Ladujemy kontenery na statek
            for(int i=0;i<kontenery.length;i++){
                if(kontenery[i][7]==null){
                    kontenery[i][7]=statki[x][0];
                    suma_ladunku = (suma_ladunku - Double.parseDouble(kontenery[i][5]));
                    //System.out.println("Teraz suma ladunku wynosi: " + suma_ladunku);
                    statki[x][5] = Double.toString(Double.parseDouble(statki[x][5]) + Double.parseDouble(kontenery[i][5]));

                    if(i+1<kontenery.length) {
                        if (Double.parseDouble(kontenery[i + 1][5]) > (Double.parseDouble(statki[x][4]) - Double.parseDouble(statki[x][5]))) {
                            statki[x][6] = "1";
                            break;
                        } else {
                            continue;
                        }
                    }

                }
                else {

                    continue;
                }
            }
            if(kontenery[kontenery.length-1][7]!=null){break;}
            System.out.println("kontenery: \n" + Arrays.deepToString(kontenery));



        }

        System.out.println("Statki: \n" + Arrays.deepToString(statki));
        return kontenery;
    }

}