
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Kaardid mängija = new Mängija(); // Mängija objekti loomine
        Kaardid diiler = new Diiler(); // Diileri objekti loomine
        int mängijaPunktid = 0;
        int diileriPunktid = 0;


        boolean mäng = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tere!");
        System.out.println("See on Blackjack(veidi muudetud) kaardimäng. Mängu eesmärk on saada diilerist suurem kaartide summa,");
        System.out.println("aga mitte üle 21 punkti.");
        System.out.println();
        while (mäng){ // Mängu pea tsükkel
            mängija.restart(); // Mängija kaartide algseadistamine
            diiler.restart(); // Diileri kaartide algseadistamine
            boolean kontroll = true;
            do { // Tsükkel milles kasutaja saab valida kas võtta uus kaart või kontrollida, kes võitis
                System.out.println(mängija.toString());
                System.out.println(diiler.toString());
                kontroll = midaTeha(mängija, diiler, scanner); // Järgmise sammu meetod

            } while (!mängija.ule21() && !diiler.ule21() && kontroll); // Tsükklis ollakse kuni üks mängijatest otsustab mängu lõpetada või kui keegi läheb üle 21

            if (kontroll == true){
                kontroll = kontrolli(mängija.getSumma(), diiler.getSumma(), diiler.getPeidetudKaart());
            }

            //Siit algab kontrollimine, kes võitis
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
            // Väljastatakse seansi skoorid
            System.out.println("Seis: Mängija=" + mängijaPunktid + ", Diiler=" + diileriPunktid);
            System.out.println();
            System.out.println("Kas soovite uuesti mängida? (Y/N) ");
            String uuesti = scanner.nextLine();
            mäng = StringToBoolean(uuesti);
        }
        scanner.close();

    }

    /**
     * Meetod, mis laseb kasutajal otsustada, kas võtta uus kaart või kontrollida kes võitis
     * @param mängija Mängija objekt
     * @param diiler Diileri objekt
     * @param scanner Scanner objekt
     * @return Tagastab True/False. True, kui kasutaja soovib uut kaarti ja mäng läheb edasi. False, kui mängija või diiler(juhuslikult) otsustas mängu lõpetada.
     */
    private static boolean midaTeha(Kaardid mängija, Kaardid diiler, Scanner scanner) {
        boolean kontroll = true;
        System.out.println("Kas võtta uus kaart(U) või kontrollida, kes võitis(K)?");
        String midaTeha = scanner.nextLine();
        if (midaTeha.toLowerCase().equals("u")){ // Kui mängija soovib (U)ut kaarti.
            mängija.lisaKaart(); // Lisab mängijale kaardi
            if (diiler.ai()){ // Diiler teeb oma käigu ja see meetod tagastab kas True või False, vastavalt sellele, kas Diiler soovib kaarte kontrollida.
                kontroll = kontrolli(mängija.getSumma(), diiler.getSumma(), diiler.getPeidetudKaart()); // Väljastatakse osapoolte summad ja tagastatakse False, et mäng lõpetada
                return kontroll;
            }
            return kontroll;

        }
        if (midaTeha.toLowerCase().equals("k")){ // Kui Mängija soovib Kontrollida kaartide seisu ja mäng lõpetada
            kontroll = kontrolli(mängija.getSumma(), diiler.getSumma(), diiler.getPeidetudKaart());
            return kontroll;
        }
        else { // Kui sisestatakse vale täht
            System.out.println("Viga! Sisestage täht uuesti.");
        }
        return kontroll;

    }

    /**
     * Meetod, mis võljastab ekraanile osapoolte kaartide summad
     * @param mängija Mängija objekt
     * @param diiler Diileri objekt
     * @param peidetudKaart Diileri peidetud kaart
     * @return tagastab False, et mäng lõpetada, sest toimus kontrollimine
     */
    private static boolean kontrolli(int mängija, int diiler, int peidetudKaart) {
        System.out.println("Mängija summa: " + mängija);
        System.out.println("Diileri summa: " + diiler + "+" + peidetudKaart + "=" + (diiler+peidetudKaart));
        return false;
    }

    /**
     * Teisendab "Y" boolean True'ks
     * @param rida Kasutaja sisestatud String
     * @return tagastab True, kui kasutaja sisestas Y, vastasel juhul False
     */
    public static boolean StringToBoolean(String rida){
        if (rida.toLowerCase().equals("y")){
            return true;
        }
        return false;
    }


}
