package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static String ispisiGradove() {

        ArrayList<Grad> gradovi = GeografijaDAO.getInstance().gradovi();

        String s="";
        for(Grad g: gradovi ){

            s=s+ g.getNaziv() +"("+g.getDrzava().getNaziv()+") - "+g.getBrojStanovnika()+'\n';
        }
        return s;
    }




    public static void main(String[] args) {


    }

}
