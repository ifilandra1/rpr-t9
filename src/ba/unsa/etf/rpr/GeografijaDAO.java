package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class GeografijaDAO {

private ArrayList<Drzava> listadrzava;
private ArrayList<Grad> listagradova;
Connection conn;

private static GeografijaDAO instance = null;

    private GeografijaDAO () {
             listadrzava=new ArrayList<>();
             listagradova=new ArrayList<>();

        String url="jdbc:sqlite:resources/baza.db";
        String upit1 = "SELECT * FROM grad";
        String upit2= "SELECT * FROM drzava";
        try {
             conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(upit1);


        } catch (SQLException e) {

            kreirajbazu();

            Drzava d1= new Drzava("Francuska", "Pariz");
            Drzava d2= new Drzava("Engleska", "London");
            Drzava d3 = new Drzava("Austrija", "Beč");

            Grad g1 = new Grad("Pariz",2206488, d1);
            Grad g2 = new Grad("London",8825000, d2);
            Grad g3 = new Grad("Beč",1899055, d3);
            Grad g4 = new Grad("Manchester",545500, d2);
            Grad g5 = new Grad("Graz", 280200, d3);

           d1.setGlavniGrad(g1);
           d2.setGlavniGrad(g2);
           d3.setGlavniGrad(g3);

           listagradova.add(g1); listagradova.add(g2); listagradova.add(g3);listagradova.add(g4);
           listagradova.add(g5);
           listadrzava.add(d1);listadrzava.add(d2); listadrzava.add(d3);
           // umjesto ovoga probati raditi sa dodajDrzavu i dodajGrad

        }
    }

    public static GeografijaDAO getInstance() {
        if (instance == null) initialize();
        return instance;
    }
    public static void removeInstance() { instance = null; }

    private static void initialize() {
        instance = new GeografijaDAO();
    }

    Grad glavniGrad (String drzava) {

        for(Drzava d: listadrzava) {
            if(d.getNaziv().equals(drzava)) {
                for(Grad g: listagradova) {
                    if(g.getNaziv()==d.getNazivgrada()) return g;
                }

            }
        }
        return null;
    }

    void obrisiDrzavu(String drzava) {

        ArrayList<String> listaimenagradova= new ArrayList<>();
         Iterator it = listadrzava.iterator();
         while(it.hasNext()) {

             Drzava pomocna =  (Drzava) it.next();
             if(pomocna.getNaziv()==drzava) {

                 Iterator it2= listagradova.iterator();
                 while(it2.hasNext()) {
                     Grad pomocni = (Grad) it2.next();
                     if(pomocni.getDrzava().getNaziv().equals(pomocna.getNaziv())) {
                         listaimenagradova.add(new String(pomocni.getNaziv()));
                         it2.remove();
                     }
                 }
                      it.remove();
             }

         }

         try {
             PreparedStatement p = conn.prepareStatement("DELETE FROM drzava WHERE naziv = ?");
             p.setString(1, drzava);
             p.execute();

             p=conn.prepareStatement("DELETE FROM grad WHERE naziv = ?");
             for(int i=0;i<listaimenagradova.size();i++) {
                 p.setString(1,listaimenagradova.get(i));
                 p.execute();
             }


         }
         catch(SQLException e) {
             e.printStackTrace();
         }

    }

   public ArrayList<Grad> gradovi() {

        TreeSet<Grad> set=new TreeSet<>(listagradova);
        ArrayList<Grad> lista=new ArrayList<>(set);
        return lista;
    }

    public void dodajGrad(Grad grad) {

     listagradova.add(grad);
        String s = "INSERT INTO grad (naziv, broj_stanovnika, drzava) VALUES(?,?,?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(s);
            stmt.setString(1, grad.getNaziv());
            stmt.setInt(2, grad.getBrojStanovnika());
            stmt.setInt(3, grad.getDrzava().getId());
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Drzava> getListadrzava() {
        return listadrzava;
    }

    public void dodajDrzavu(Drzava drzava) {

        listadrzava.add(drzava);
        int id=0;
        for(Grad g:listagradova) {
            if(g.getNaziv()==drzava.getNazivgrada())
            id=g.getId();
        }

        String s = "INSERT INTO drzava (naziv, broj_stanovnika, drzava) VALUES(?,?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(s);
            stmt.setString(1, drzava.getNaziv());
            stmt.setInt(2, id);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

    }

    void izmijeniGrad(Grad grad) {
        Iterator it = listagradova.iterator();
        while(it.hasNext()) {

            Grad pomocni= (Grad)it.next();

            if(pomocni.getId()==grad.getId()) {

                pomocni.setNaziv(grad.getNaziv());
                pomocni.setBrojStanovnika(grad.getBrojStanovnika());
                pomocni.setDrzava(grad.getDrzava());
            }
        }

        String s = "UPDATE grad SET naziv = ? broj_stanovnika=? drzava=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(s);
            stmt.setString(1, grad.getNaziv());
            stmt.setInt(2, grad.getBrojStanovnika());
            stmt.setInt(3, grad.getDrzava().getId());
            stmt.execute();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }


    }


    Drzava nadjiDrzavu(String drzava) {
        for(Drzava d : listadrzava) {

            if(d.getNazivgrada().equals(drzava)) return d;
        }

return null;
    }





    public static void kreirajbazu() {

        String grad= "CREATE TABLE IF NOT EXISTS grad \n"+
                "(\n id int PRIMARY KEY," +
                "naziv text," +
                "broj_stanovnika int," +
                "drzava int, \n" +
                "constraint grad_drzava_id_fk FOREIGN KEY (drzava) REFERENCES drzava(id)\n" +
                ");";
        String drzava = "CREATE TABLE IF NOT EXISTS drzava \n"+
                "(\n id int PRIMARY KEY," +
                "naziv text," +
                "glavni_grad int, \n" +
                "constraint drzava_grad_id_fk FOREIGN KEY (glavni_grad) REFERENCES grad(id)\n" +
                ");";

        String url= "jdbc:sqlite:baza.db";
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt1 = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            stmt1.execute(grad);
            stmt2.execute(drzava);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

    }



}
