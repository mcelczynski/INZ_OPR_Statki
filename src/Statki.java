import java.io.IOException;

public class Statki {
    public static String ID_statku = "0";
    int w =0;
    int h =0;
    int d =0;

    public static String Dodaj_statek() throws IOException {
        wczytaj_statek();

        return("Dodalem statek");
    }
    public static void wczytaj_statek() throws IOException {
        int status_czytaj = 0;
        while (status_czytaj == 0) {
            System.out.println("Podaj Unikatowy numer Statku [prefix ID + 6 znakow - np: ID123456]: ");
            ID_statku = Funkcje.czytaj_string();
            int status_ID = Funkcje.ID_sprawdzanie(ID_statku);
            if (status_ID == 1) {
                System.out.println("Podany  numer statku juz idtnieje w bazie\nPodaj poprawny numer ID");
                Funkcje.loguj("Dodawanie Statku. Podany ID istnieje w bazie statkow: " + ID_statku);
                status_czytaj=0;
                continue;
            }
            else{
                Funkcje.loguj("Podano ID nowego statku: " + ID_statku);
            }
            System.out.println("Podaj Unikatowy numer Statku [prefix ID + 6 znakow - np: ID123456]: ");
            ID_statku = Funkcje.czytaj_string();

            }
        }
       /* String string = "004-034556";
        String[] parts = string.split("-");
        String part1 = parts[0]; // 004
        String part2 = parts[1]; // 034556
    */
    }

