package be.ac.umons;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class choixFactoryController {

    private static String factory;

    @FXML
    protected void handleChoixPizzaHut (ActionEvent event) throws IOException {
        factory = "PizzaHut";
        App.setRoot("choixCommande");
    }

    @FXML
    protected void handleChoixDominos (ActionEvent event) throws IOException {
        factory = "Domino's";
        App.setRoot("choixCommande");
    }

    public static String factoryReturn(){
        return factory;
    }

}
