package ba.unsa.etf.rpr;

public class Drzava {

    private String naziv;
     private String nazivgrada;

    public Drzava(String naziv, String nazivgrada) {
        this.naziv = naziv;
        this.nazivgrada = nazivgrada;
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
