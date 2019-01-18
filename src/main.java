/**
 * Created by Marcin Celczynski
 * Inzynieria Oprogramowania 2018/2019
 */
import java.util.Scanner;

public class main {



    public static void main (String[] args)
    {
        System.out.println("Witaj w aplikacji do automatycznego rozdzelania kontenerów na statki");
        System.out.println("Powiedz prosze, co chcesz zrobić?\nMasz do wyboru następujące opcje:");
        System.out.println("[1] Dodaj nowy statek\n[2] Rozlokuj kontenery\n[3] Wyswietl raporty\n[4] Usun statek\n[5]Zakoncz");

        Scanner odczyt = new Scanner(System.in); //obiekt do odebrania danych od użytkownika

        int akcja = odczyt.nextInt();  //zmienna do wyboru opcji

        System.out.println("Wybrales opcje: " + akcja);
    }
}
