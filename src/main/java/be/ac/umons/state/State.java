package be.ac.umons.state;

import be.ac.umons.Ingredient;
import be.ac.umons.Pizza;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Map;

public interface State {

    Boolean reparer(Boolean panne);

    Map<String, Ingredient> reapprovisionner(ObservableList<String> emptyIngredient, Map<String, Ingredient> ingredients);

    void fabriquerCommande(ObservableList<Pizza> commande) throws InterruptedException;
}
