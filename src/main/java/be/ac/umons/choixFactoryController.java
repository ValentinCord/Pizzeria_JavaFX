package be.ac.umons;

import be.ac.umons.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class choixFactoryController {

    @FXML
    protected void handleChoixPizzaHut (ActionEvent event) throws IOException {
        // Construire la fabrique PizzaHut et lancer la deuxieme interface
        // attention de bien conserver le nom de la fabrique dans l'autre classe
        App.setRoot("choixCommande");
    }

    @FXML
    protected void handleChoixDominos (ActionEvent event) throws IOException {
        // Construire la fabrique Dominos et lancer la deuxieme interface
        App.setRoot("choixCommande");
    }

}
