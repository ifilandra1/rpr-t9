package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public class GeografijaDAO {

private ArrayList<Drzava> listadrzava;
private ArrayList<Grad> listagradova;
Connection conn;

    private GeografijaDAO () {


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

            listagradova.add(new Grad("Pariz",2206488, d1));
            listagradova.add(new Grad("London",8825000,d2));
            listagradova.add(new Grad("Beč", 1899055,d3));
            listagradova.add(new Grad("Manchester", 545500, d2));
            listagradova.add(new Grad("Graz",280200, d3));

        }
    }

    Grad glavnigrad (String drzava) {

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

    ArrayList<Grad> gradovi() {

        TreeSet<Grad> set=new TreeSet<>(listagradova);
        ArrayList<Grad> lista=new ArrayList<>(set);
        return lista;
    }


    public static void kreirajbazu() {

        String grad= "CREATE TABLE grad \n"+
                "(\n id int PRIMARY KEY," +
                "naziv text," +
                "broj_stanovnika int," +
                "drzava int, \n" +
                "constraint grad_drzava_id_fk FOREIGN KEY (drzava) REFERENCES drzava(id)\n" +
                ");";
        String drzava = "CREATE TABLE drzava \n"+
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
