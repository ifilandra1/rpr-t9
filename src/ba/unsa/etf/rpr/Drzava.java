package ba.unsa.etf.rpr;

public class Drzava {

    private String naziv;
    private int id;

    public Drzava(String naziv, int id) {
        this.naziv = naziv;
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getNazivgrada() {
        return nazivgrada;
    }

    public void setNazivgrada(String nazivgrada) {
        this.nazivgrada = nazivgrada;
    }
}
