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
                    Statki.Dodaj_statek();
                    break;

                case 2:
                    Rozlokuj.Rozlokuj_nowy();
                    break;

                //...
                case 3:
                    //jakiś kod
                    break;
                case 4:
                    //jakiś kod
                    break;

                //...
                case 5:
                    //jakiś kod
                    break;
                default:

                    break;
            }
        }
        System.out.println("Konczymy na dzisiaj. Dziekuje! ");
        TimeUnit.SECONDS.sleep(5);
    }


}
