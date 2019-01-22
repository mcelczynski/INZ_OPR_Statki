import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Funkcje {

    public static int menu_opcje() throws IOException {
        int status = 0;
        int akcja = 0;
        while (status == 0) {
            Scanner odczyt = new Scanner(System.in); //obiekt do odebrania danych od użytkownika
            akcja = odczyt.nextInt();
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
}
