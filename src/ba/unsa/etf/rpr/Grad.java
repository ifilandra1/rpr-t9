package ba.unsa.etf.rpr;

public class Grad  {

    private String naziv;
    private int brojStanovnika;
    private Drzava drzava;
    private int id;

    public Grad(String naziv, int broj_stanovnika, Drzava drzava) {
        this.naziv = naziv;
        this.brojStanovnika = broj_stanovnika;
        this.drzava = drzava;
    }

    public Grad() {
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

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }


    public int compareTo(Grad g) {

    if(this.brojStanovnika <g.getBrojStanovnika()) return -1;
    if(this.brojStanovnika >g.getBrojStanovnika()) return 1;
     else return 0;
    }


}
