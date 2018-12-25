package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    public Label lbl1;
    public TextField drzavaField;
    private SimpleStringProperty drzavaproperty;


    public Controller() {
        drzavaField=new TextField();
        drzavaproperty = new SimpleStringProperty();
    }

    public void initialize() {

        drzavaField.textProperty().bindBidirectional(drzavaproperty);
    }

    public void glavnigrad() {

      String s="Glavni grad drzave "+drzavaproperty.get()+ "je "+GeografijaDAO.getInstance().glavniGrad(drzavaproperty.get()).getNaziv();
        lbl1.setText(s);
    }

    public void izbrisi() {
        int velicina = GeografijaDAO.getInstance().getListadrzava().size();

        GeografijaDAO.getInstance().obrisiDrzavu(drzavaproperty.get());

        if(velicina!=GeografijaDAO.getInstance().getListadrzava().size())
            lbl1.setText("Drzava "+drzavaproperty.get()+" obrisana");
        else lbl1.setText("Unesena drzava nije u bazi");
    }



}
