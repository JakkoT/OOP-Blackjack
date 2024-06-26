import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Kaardid{
    private List<Integer> kaardid;
    private int summa;
    private int viimaneKaart;

    public Kaardid() {
        this.kaardid = new ArrayList<>();
        this.summa = 0;
    }

    /**
     * Lisab Random abiga ühe uue kaardi kaartide listi
     */
    public void lisaKaart(){
        Random random = new Random();
        int uusKaart = random.nextInt(2, 12);
        this.kaardid.add(uusKaart);
        this.viimaneKaart = uusKaart;
    }

    /**
     * Kontrollib, kas kaartide summa on üle 21
     * @return tagastab True, kui summa on üle 21. Kui on alla siis False
     */
    public boolean ule21(){
        if (getSumma() > 21){
            return true;
        }
        return false;

    }

    public int getSumma() {
        int temp = 0;
        for (Integer i : this.kaardid) {
            temp += i;
        }
        summa = temp;
        return summa;
    }

    public int getViimaneKaart() {
        return viimaneKaart;
    }

    public List<Integer> getKaardid() {
        return kaardid;
    }

    public void restart(){
        this.kaardid.clear();
        lisaKaart();
    }

    public abstract boolean ai();
    public abstract int getPeidetudKaart();
}
