import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Kaardid mängija = new Mängija();
        Kaardid diiler = new Diiler();
        int mängijaPunktid = 0;
        int diileriPunktid = 0;


        boolean mäng = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tere!");
        System.out.println("See on Blackjack kaardimäng. Mängu eesmärk on saada diilerist suurem kaartide summa,");
        System.out.println("aga mitte üle 21 punkti.");
        System.out.println();
        while (mäng){
            mängija.restart();
            diiler.restart();
            boolean kontroll = true;
            do {
                System.out.println(mängija.toString());
                System.out.println(diiler.toString());
                kontroll = midaTeha(mängija, diiler, scanner);

            } while (!mängija.ule21() && !diiler.ule21() && kontroll);

            if (kontroll == true){
                kontroll = kontrolli(mängija.getSumma(), diiler.getSumma(), diiler.getPeidetudKaart());
            }

            if (mängija.ule21() && diiler.ule21()){
                System.out.println("Keegi ei võitnud!");
            }
            else if (mängija.ule21()){
                diileriPunktid += 1;
                System.out.println("Diiler võitis!");
            }
            else if (diiler.ule21()){
                System.out.println("Mängija võitis!");
                mängijaPunktid += 1;
            }
            else {
                if (mängija.getSumma() > (diiler.getSumma() + diiler.getPeidetudKaart())) {
                    System.out.println("Mängija võitis!");
                    mängijaPunktid += 1;
                }
                if (mängija.getSumma() < (diiler.getSumma() + diiler.getPeidetudKaart())) {
                    diileriPunktid += 1;
                    System.out.println("Diiler võitis!");
                }
                if (mängija.getSumma() == (diiler.getSumma() + diiler.getPeidetudKaart())) {
                    diileriPunktid += 1;
                    mängijaPunktid += 1;
                    System.out.println("Viik!");
                }
            }
            System.out.println("Seis: Mängija=" + mängijaPunktid + ", Diiler=" + diileriPunktid);
            System.out.println();
            System.out.println("Kas soovite uuesti mängida? (Y/N) ");
            String uuesti = scanner.nextLine();
            mäng = StringToBoolean(uuesti);
        }
        scanner.close();

    }

    private static boolean midaTeha(Kaardid mängija, Kaardid diiler, Scanner scanner) {
        boolean kontroll = true;
        System.out.println("Kas võtta uus kaart(U) või kontrollida, kes võitis(K)?");
        String midaTeha = scanner.nextLine();
        if (midaTeha.toLowerCase().equals("u")){
            mängija.lisaKaart();
            if (diiler.ai()){
                kontroll = kontrolli(mängija.getSumma(), diiler.getSumma(), diiler.getPeidetudKaart());
                return kontroll;
            }
            return kontroll;

        }
        if (midaTeha.toLowerCase().equals("k")){
            kontroll = kontrolli(mängija.getSumma(), diiler.getSumma(), diiler.getPeidetudKaart());
            return kontroll;
        }
        else {
            System.out.println("Viga!");
        }
        return kontroll;

    }

    private static boolean kontrolli(int mängija, int diiler, int peidetudKaart) {
        System.out.println("Mängija summa: " + mängija);
        System.out.println("Diileri summa: " + diiler + "+" + peidetudKaart + "=" + (diiler+peidetudKaart));
        return false;
    }

    public static boolean StringToBoolean(String rida){
        if (rida.toLowerCase().equals("y")){
            return true;
        }
        return false;
    }


}
