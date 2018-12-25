package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GeografijaDAO {

private ArrayList<Drzava> listadrzava;
private ArrayList<Grad> listagradova;

    private GeografijaDAO () {


        String url="jdbc:sqlite:resources/baza.db";
        String upit = "SELECT ";
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(upit);
        } catch (SQLException e) {

            listadrzava.add(new Drzava("Francuska", "Pariz"));
            listadrzava.add(new Drzava("Engleska","London"));
            listadrzava.add(new Drzava("Austrija", "Beč"));

            listagradova.add(new Grad("Pariz",2206488, new Drzava("Francuska", "Pariz")));
            listagradova.add(new Grad("London",8825000, new Drzava("Engleska", "London") ));
            listagradova.add(new Grad("Beč", 1899055,new Drzava ("Austrija", "Beč")));
            listagradova.add(new Grad("Manchester", 545500, new Drzava ("Engleska", "London")));
            listagradova.add(new Grad("Graz",280200, new Drzava("Austrija", "Beč")));

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




}
