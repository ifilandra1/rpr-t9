package ba.unsa.etf.rpr;

public class Grad {

    private String naziv;
    private int broj_stanovnika;
    private Drzava drzava;
    private int id;

    public Grad(String naziv, int broj_stanovnika, Drzava drzava) {
        this.naziv = naziv;
        this.broj_stanovnika = broj_stanovnika;
        this.drzava = drzava;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBroj_stanovnika() {
        return broj_stanovnika;
    }

    public void setBroj_stanovnika(int broj_stanovnika) {
        this.broj_stanovnika = broj_stanovnika;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }


    public int compareTo(Grad g) {

    if(this.broj_stanovnika<g.getBroj_stanovnika()) return -1;
    if(this.broj_stanovnika>g.getBroj_stanovnika()) return 1;
     else return 0;
    }


}
