import java.util.Random;

public class Diiler extends Kaardid{
    private int peidetudKaart;
    public Diiler() {
        super();
    }

    /**
     * Tagastab diileri kaardid, lisades ette ühe ? peidetud kaardi jaoks ja nende summa ilusal String kujul
     * @return tagastab ilusa String
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Diileri kaardid: ");
        output.append("? ");
        for (Integer i : super.getKaardid()) {
            output.append(i);
            output.append(" ");
        }
        output.append("= ");
        output.append(super.getSumma());
        output.append("+?");
        return output.toString();
    }

    /**
     * Loob Diilerile uue peidetud kaardi suvaliselt.
     */
    public void uusPeidetudKaart(){
        Random random = new Random();
        this.peidetudKaart = random.nextInt(2, 12);
    }

    /**
     * Teeb Diilerile algseadistuse ja loob uue peidetud kaardi
     */
    public void restart(){
        super.restart();
        uusPeidetudKaart();
    }

    /**
     * Diileri "AI", mis otsustab mida mängus edasi teha
     * @return Tagastab True kui Diiler soovib kaarte kontrollida, kui ei soovi siis tagastab False
     */
    public boolean ai(){ // Tegelt ei ole Ai hahaha, suvaliselt kas lisab kaardi või mitte
        Random random = new Random();
        if (super.getSumma() + getPeidetudKaart() < 10){ // Kui on alla 10 punkti siis võtab diiler alati uue kaardi
            super.lisaKaart();
            System.out.println("Diiler võtiis kaardi ja sai " + getViimaneKaart());
            return false;
        }
        int arv = random.nextInt(1, 11);
        if (super.getSumma() + getPeidetudKaart() < 18){ // Kui on alla 18 punkti, siis diiler suvaliselt valib kas võtta uus kaard või lõpetada mäng
            if (arv < 5){
                super.lisaKaart();
                System.out.println("Diiler võtiis kaardi ja sai " + getViimaneKaart());
            }
            return false;
        }
        else { // Kui on rohkem kui 18 punkti, siis diiler alati kontrollib kaarte
            System.out.println("Diiler kontrollib kaarte!");
            return true;
        }
    }

    public int getPeidetudKaart() {
        return peidetudKaart;
    }

    public boolean ule21(){
        if (getSumma()+getPeidetudKaart() > 21){
            return true;
        }
        return false;

    }
}
