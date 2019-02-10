import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;

import static java.nio.file.Paths.get;

public class Funkcje {

    public static int menu_opcje() throws IOException {
        int status = 0;
        int akcja = 0;
        while (status == 0) {
            //Scanner odczyt = new Scanner(System.in); //obiekt do odebrania danych od użytkownika
            //akcja = odczyt.nextInt();
            akcja = czytaj_int();
            if (akcja >= 1 && akcja <= 7) {
                System.out.println("Wybrales opcje: " + akcja);
                status = 1;
            }
            else {
                System.out.println("Wybrales opcje spoza zakresu: " + akcja + "\nPodaj poprawna wartosc");
                status = 0;
                loguj("Zla akcja wybrana");
            }
        }
        loguj("wybrano akcje: " + akcja);
        return akcja;
    }

    public static void loguj(String text) throws IOException {
        LocalTime localTime1 = LocalTime.now();
        LocalDate localDate1 = LocalDate.now();

        FileWriter file = new FileWriter(localDate1 + "  logs.txt", true);
        BufferedWriter out = new BufferedWriter(file);
        out.write(  localTime1 + "   " + text + "\n");
        out.close();
    }

    public static String czytaj_string(){
        String string;
        Scanner odczyt = new Scanner(System.in); //obiekt do odebrania danych od użytkownika
        string = odczyt.nextLine();
        return string;
    }
    public static int czytaj_int(){
        int string;
        Scanner odczyt = new Scanner(System.in); //obiekt do odebrania danych od użytkownika
        string = odczyt.nextInt();
        return string;
    }
    public static int ID_sprawdzanie(String ID) throws IOException {
            // Ladowanie pliku
            File file = null;
            int status = 0;
            file = new File("statki.csv");
            Scanner in = new Scanner(file);
           // Szukanie w pliku
        try {
            in = new Scanner(file).useDelimiter(";");

            while (in.hasNext()) {
                final String lineFromFile = in.nextLine();
                if (lineFromFile.contains(ID)) {
                    // Znalazlem
                    status = 1;
                    break;
                }
            }
        } catch (IOException e){}
        //wynik szukania
        return status;
    }
    public static int wymiary_sprawdzanie (int a, int b, int c)  throws IOException {
        int status = 0;
        if (a <= c && c <= b){
            status = 1;
        } else{ status = 0; }
    return status;
    }

    public static void generuj_statki ()  throws IOException {
        System.out.println("Podaj ilosc statkow do wygenerowania:");
        int ilosc = czytaj_int();
        String namefile;
        String statek;
        System.out.println("Podaj ilosc statkow do wygenerowania:");
        namefile = czytaj_string();

        for (int i = 0;i < ilosc;i++) {
            String ID_prefix = "ID0000";
            String ID = ID_prefix + Integer.toString(i+10);
            int w = losowy_int(50, 100);
            int h = losowy_int(50, 100);
            int d = losowy_int(50, 100);
            int status = 0;
            statek = ID + ";" + Integer.toString(w) + ";" + Integer.toString(h) + ";" + Integer.toString(d) + ";" + Integer.toString(status);
            FileWriter file = new FileWriter(namefile, true);
            BufferedWriter out = new BufferedWriter(file);
            out.write("\n" + statek );
            out.close();
            System.out.println(statek);
        }
    }
    public static int losowy_int (int x, int y){
        Random rand = new Random();
        int a = rand.nextInt(y - x + 1) + x;
        return a;
    }

    public static double losowy_double (float x, float y) {
        Random rand = new Random();
        double a = rand.nextDouble()*(y - x + 1) + x;
        double round_a =  Math.round(a * 100.0) / 100.0;  // Zaokraglanie do 2 miejsc po przecinku
        return round_a;
    }
    public static void generuj_kontenery ()  throws IOException {
        System.out.println("Podaj ilosc kontenerow do wygenerowania:");
        int ilosc = czytaj_int();
        String namefile;
        String kontener;
        System.out.println("Podaj nazwe pliku w ktorym mam zapisać kontenery (rozszerzenie zostanie dodane automatycznie):");
        namefile = String.format(czytaj_string() + ".csv");

        for (int i = 0;i < ilosc;i++) {
            String ID_prefix = "KON0000";
            String ID = ID_prefix + Integer.toString(i+10);
            int w = losowy_int(50, 100);
            int h = losowy_int(50, 100);
            int d = losowy_int(50, 100);
            double wartosc = losowy_double(100, 1000);
            kontener = ID + ";" + Integer.toString(w) + ";" + Integer.toString(h) + ";" + Integer.toString(d) + ";" + Double.toString(wartosc);
            FileWriter file = new FileWriter(namefile, true);
            BufferedWriter out = new BufferedWriter(file);
            out.write("\n" + kontener );
            out.close();
            System.out.println(kontener);
            System.out.println("Dane zapisane do pliku " + namefile);
            Funkcje.loguj("Dodano kontener: " + kontener + " do pliku: " + namefile);
        }
    }

    //Zliczanie linii pliku
    public static int ilosc_lini (String a)  throws IOException {
        int lineCount = (int) Files.lines(get(a)).count();
        return lineCount;
    }

    //Wczytywanie pliku z kontenerami
    public static String czytaj_kontenery(){
        int status =0;
        String plik_kontenery = null;
        while(status == 0) {
            System.out.println("Podaj nazwe pliku z kontenerami: ");
            plik_kontenery = Funkcje.czytaj_string();

            boolean i = new File(plik_kontenery).isFile();
            if (i == false){
                System.out.println("Plik o podanej nazwie nie istnieje - pamiętaj o rozszerzeniu pliku!");
                continue;}
            else
            {
                System.out.println("Znaleziono plik");
                break;
            }
        }
        return plik_kontenery;
    }

    //Wczytywanie pliku ze statkami
    public static String czytaj_statki(){
        int status =0;
        String baza_statki = null;
        while(status == 0) {
            System.out.println("Podaj nazwe bazy statkow: ");
            baza_statki = Funkcje.czytaj_string();
            String plik_statki = (baza_statki + ".csv");
            boolean i = new File(plik_statki).isFile();
            if (i == false){
                System.out.println("Baza o podanej nazwie nie istnieje - podaj poprawna nazwe bazy!");
                continue;}
            else
            {
                System.out.println("Znaleziono plik " + plik_statki);
                break;
            }
        }

        return baza_statki;
    }
}
