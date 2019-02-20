import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Statki {

    static int Status_Statku =0;
    public static String ID_statku = "0";
    static int w =0;
    static int h =0;
    static int d =0;
    static int v =0;

    public static String Dodaj_statek() throws IOException {
        int nowy = 1;
        while (nowy == 1) {
            String statek = wczytaj_statek();
            FileWriter file = new FileWriter("statki.csv", true);
            BufferedWriter out = new BufferedWriter(file);
            out.write("\n" + statek);
            out.close();
            System.out.println(statek);
            System.out.println("Czy chcesz dodac kolejny? [1 - TAK, 0 - NIE]");
            nowy=Funkcje.czytaj_int();
            }
        return ("Statek/Statki dodane poprawnie. Dziekujemy!\n");
    }
    public static String wczytaj_statek() throws IOException {
        int status_czytaj = 0;

        //Petle dodajaca Statek
        //ID statku
        while (status_czytaj == 0) {

            //ID statku
            System.out.println("Podaj Unikatowy numer Statku [prefix ID + 6 znakow - np: ID123456]: ");
            ID_statku = Funkcje.czytaj_string();
            int status_ID = Funkcje.ID_sprawdzanie(ID_statku);
            if (status_ID == 1) {
                System.out.println("Podany  numer statku juz idtnieje w bazie\nPodaj poprawny numer ID");
                Funkcje.loguj("Dodawanie Statku. Podany ID istnieje w bazie statkow: " + ID_statku);
                status_czytaj = 0;
                continue;
            } else {
                Funkcje.loguj("Podano ID nowego statku: " + ID_statku);
            }
            status_czytaj=1;
        }
        status_czytaj = 0;

        //Szerokosc statku
        while (status_czytaj == 0){

            //Szerokosc statku
            System.out.println("Podaj Szerokość statku [50 - 100]: ");
            w = Funkcje.czytaj_int();
            int status_ID = Funkcje.wymiary_sprawdzanie(50, 100, w);
            if (status_ID == 0) {
                System.out.println("Podany wymiar statku jest poza zakresem\nPodaj poprawny wymiar");
                Funkcje.loguj("Dodawanie Statku. Wymiar 'w' statku poza zakresem: " + w);
                status_czytaj=0;
                continue;
            }
            else{
                Funkcje.loguj("Podano 'w' nowego statku: " + w);
            }
            status_czytaj=1;
        }
        status_czytaj = 0;
        //Wysokosc statku
        while (status_czytaj == 0){

            //Wysokosc statku
            System.out.println("Podaj Wysokosc statku [50 - 100]: ");
            h = Funkcje.czytaj_int();
            int status_ID = Funkcje.wymiary_sprawdzanie(50, 100, h);
            if (status_ID == 0) {
                System.out.println("Podany wymiar statku jest poza zakresem\nPodaj poprawny wymiar");
                Funkcje.loguj("Dodawanie Statku. Wymiar 'w' statku poza zakresem: " + w);
                status_czytaj=0;
                continue;
            }
            else{
                Funkcje.loguj("Podano 'w' nowego statku: " + h);
            }
            status_czytaj=1;
        }
        status_czytaj = 0;

        //Dlugosc statku
        while (status_czytaj == 0){

            //Dlugosc statku
            System.out.println("Podaj Dlugosc statku [50 - 100]: ");
            d = Funkcje.czytaj_int();
            int status_ID = Funkcje.wymiary_sprawdzanie(50, 100, d);
            if (status_ID == 0) {
                System.out.println("Podany wymiar statku jest poza zakresem\nPodaj poprawny wymiar");
                Funkcje.loguj("Dodawanie Statku. Wymiar 'w' statku poza zakresem: " + d);
                status_czytaj=0;
                continue;
            }
            else{
                Funkcje.loguj("Podano 'w' nowego statku: " + d);
            }
            status_czytaj=1;
        }

        //Objetosc
        v=w*h*d;
        System.out.println("Podane wymiary statku daja laczna jego Objetosc: " + v + "m3");

        return (ID_statku + ";" + w + ";" + h + ";" + d + ";" + v + ";" + Status_Statku + ";");
        }


    }

