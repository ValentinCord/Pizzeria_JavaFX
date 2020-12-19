package be.ac.umons.state;

import be.ac.umons.Ingredient;
import be.ac.umons.Pizza;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Map;

public class Attente implements State {

    public Attente(){
        System.out.println("Attente");
    }


    @Override
    public Boolean reparer(Boolean panne) {
        return panne;
    }

    @Override
    public Map<String, Ingredient> reapprovisionner(ObservableList<String> emptyIngredient, Map<String, Ingredient> ingredients) {
        return ingredients;
    }

    @Override
    public void fabriquerCommande(ObservableList<Pizza> commande) {

    }


}
