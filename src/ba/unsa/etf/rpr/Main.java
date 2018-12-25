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


    public void glavniGrad() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Unesite naziv drzave");
        String s = scan.next();
        System.out.println("Glavni grad drzave "+s+" je "+GeografijaDAO.getInstance().glavniGrad(s).getNaziv());

    }




    public static void main(String[] args) {


    }

}
