import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Funkcje {

    public static int menu_opcje() throws IOException {
        int status = 0;
        int akcja = 0;
        while (status == 0) {
            //Scanner odczyt = new Scanner(System.in); //obiekt do odebrania danych od użytkownika
            //akcja = odczyt.nextInt();
            akcja = czytaj_int();
            if (akcja >= 1 && akcja <= 5) {
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
        out.write("\n" + localTime1 + "   " + text);
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
}
