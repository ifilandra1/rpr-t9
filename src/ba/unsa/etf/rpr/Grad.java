package ba.unsa.etf.rpr;

public class Grad {

    private String naziv;
    private int id;
    private int broj_stanovnika;
    private Drzava drzava;

    public Grad(String naziv, int id, int broj_stanovnika) {
        this.naziv = naziv;
        this.id = id;
        this.broj_stanovnika = broj_stanovnika;
    }

}
