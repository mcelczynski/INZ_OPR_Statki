import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.LocalDate;

public class Raporty {
    public static void generuj_raport (String[][] kontenery)throws IOException{
        String raport = null;
        LocalDate localDate1 = LocalDate.now();
        LocalTime localTime1 = LocalTime.now();
        Double d=0.00;


        FileWriter file1 = new FileWriter(localDate1 + "  raport.txt", true);
        BufferedWriter out1 = new BufferedWriter(file1);
        out1.write("\n\nRaport z dnia:\t" +localDate1 + "\tgodziny:\t" + localTime1 + "\n");
        out1.close();
        System.out.println(raport);

        for(int i=0; i<kontenery.length;i++) {
            String a = kontenery[i][7]; //ID Statku
            String b = kontenery[i][0]; // ID Kontenera
            String c = kontenery[i][4]; // Wartosc kontenera
            d += Double.parseDouble(kontenery[i][4]);
            raport = "ID Statku: " + a + "\tKontener: " + b + "\tWartosc Kontenera:" + c;

            FileWriter file = new FileWriter(localDate1 + "  raport.txt", true);
            BufferedWriter out = new BufferedWriter(file);
            out.write(raport + "\n");
            out.close();
            System.out.println(raport);
        }
        FileWriter file = new FileWriter(localDate1 + "  raport.txt", true);
        BufferedWriter out = new BufferedWriter(file);
        out.write("_____________________\nLaczna wartosc kontenerow to:\t"+ d + "\t[USD]\n");
        out.close();
       // System.out.println("Raport, posortowana po statkach: \n" + Arrays.deepToString(kontenery));
    }


}
