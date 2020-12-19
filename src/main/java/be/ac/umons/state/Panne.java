package be.ac.umons.state;

import be.ac.umons.Ingredient;
import be.ac.umons.Pizza;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Map;

public class Panne implements State {

    @Override
    public void currentState(Context context) {
        System.out.println("Panne");

        context.setState(new Attente());

    }

    @Override
    public void reapprovisionner(ArrayList<String> emptyIngredient, Map<String, Ingredient> ingredients) {

    }

    @Override
    public void fabriquerCommande(ObservableList<Pizza> commande) {

    }


}
