

public class Mängija extends Kaardid{
    public Mängija() {
        super();
    }

    /**
     * Tagastab mängija kaardid ja nende summa ilusal String kujul
     * @return Tagastab ilusa String
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Mängija kaardid: ");
        for (Integer i : super.getKaardid()) {
            output.append(i);
            output.append(" ");
        }
        output.append("= ");
        output.append(super.getSumma());
        return output.toString();

    }

    /**
     * Teeb Mängijale algseadistuse ja lisab uue mängu jaoks veel ühe kaardi
     */
    public void restart(){
        super.restart();
        lisaKaart();
    }

    @Override
    public boolean ai() {
        return false;
    }

    @Override
    public int getPeidetudKaart() {
        return 0;
    }
}
