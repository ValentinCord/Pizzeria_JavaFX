package be.ac.umons;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class choixFactoryController {

    private static String factory;

    @FXML
    protected void handleChoixPizzaHut (ActionEvent event) throws IOException {
        // Construire la fabrique PizzaHut et lancer la deuxieme interface
        // attention de bien conserver le nom de la fabrique dans l'autre classe
        factory = "PizzaHut";
        App.setRoot("choixCommande");
    }

    @FXML
    protected void handleChoixDominos (ActionEvent event) throws IOException {
        // Construire la fabrique Dominos et lancer la deuxieme interface
        factory = "Domino's";
        App.setRoot("choixCommande");
    }

    public static String factoryReturn(){
        return factory;
    }

}
