import java.util.Random;

public class Diiler extends Kaardid{
    private int peidetudKaart;
    public Diiler() {
        super();
    }

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

    public void uusPeidetudKaart(){
        Random random = new Random();
        this.peidetudKaart = random.nextInt(2, 12);
    }

    public void restart(){
        super.restart();
        uusPeidetudKaart();
    }

    public boolean ai(){ // Tegelt ei ole Ai hahaha, suvaliselt kas lisab kaardi või mitte
        Random random = new Random();
        if (super.getSumma() < 10){
            super.lisaKaart();
            return false;
        }
        int arv = random.nextInt(1, 11);
        if (super.getSumma() < 18){
            if (arv < 5){
                super.lisaKaart();
            }
            return false;
        }
        else {
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
