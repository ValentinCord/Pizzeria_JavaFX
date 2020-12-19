package be.ac.umons.state;

import be.ac.umons.Ingredient;
import be.ac.umons.Pizza;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Map;

public class Manque implements State {

    public Manque() {
        System.out.println("Etat de manque");
    }

    @Override
    public Boolean reparer(Boolean panne) {
        return panne;
    }

    @Override
    public Map<String, Ingredient> reapprovisionner(ObservableList<String> emptyIngredient, Map<String, Ingredient> ingredients) {
        for(String item:emptyIngredient){
            ingredients.get(item).setStock(10);
        }
        return ingredients;
    }

    @Override
    public void fabriquerCommande(ObservableList<Pizza> commande) {

    }

}
