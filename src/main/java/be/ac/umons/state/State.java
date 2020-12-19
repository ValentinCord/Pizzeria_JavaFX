package be.ac.umons.state;

import be.ac.umons.Ingredient;
import be.ac.umons.Pizza;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Map;

public interface State {
    void currentState(Context context);

    void reapprovisionner(ArrayList<String> emptyIngredient, Map<String, Ingredient> ingredients);

    void fabriquerCommande(ObservableList<Pizza> commande);
}
