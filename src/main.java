/**
 * Created by Marcin Celczynski
 * Inzynieria Oprogramowania 2018/2019
 */
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class main {



    public static void main (String[] args) throws IOException, InterruptedException {
        int akcja = 0;
        while (akcja != 5) {
            //Wstep
            System.out.println("Witaj w aplikacji do automatycznego rozdzelania kontenerów na statki");
            System.out.println("Powiedz prosze, co chcesz zrobić?\nMasz do wyboru następujące opcje:");
            System.out.println("[1] Dodaj nowy statek\n[2] Rozlokuj kontenery\n[3] Wyswietl raporty\n[4] Usun statek\n[5]Zakoncz");
            akcja = Funkcje.menu_opcje();
            System.out.println("Przechodze do akcji: " + akcja);

            //Menu glowne  - akcje
            switch( akcja )
            {
                case 1:
                    String statek = Statki.Dodaj_statek();
                    System.out.println(statek);
                    break;

                case 2:
                    Rozlokuj.Rozlokuj_nowy();
                    break;

                //...
                case 3:
                    System.out.println("Podaj plik z kontenerami:");
                    String kontenery = Funkcje.czytaj_string();
                    Funkcje.czytaj_z_csv(kontenery);
                    break;
                case 4:
                    //jakiś kod
                    break;
                case 6:
                    Funkcje.generuj_statki();
                    break;
                case 7:
                    Funkcje.generuj_kontenery();
                    break;
            }
        }
        System.out.println("Konczymy na dzisiaj. Dziekuje! ");
        TimeUnit.SECONDS.sleep(5);
    }


}