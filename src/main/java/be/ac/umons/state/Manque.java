package be.ac.umons.state;

import be.ac.umons.Ingredient;
import be.ac.umons.Pizza;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Map;

public class Manque implements State {

    @Override
    public void currentState(Context context) {
        System.out.println("Manque");

        context.setState(new Attente());
    }

    @Override
    public void reapprovisionner(ArrayList<String> emptyIngredient, Map<String, Ingredient> ingredients) {

        for(String item:emptyIngredient){
            System.out.println(item);
            System.out.println(ingredients);
            System.out.println(ingredients.get(item));
            ingredients.get(item).setStock(10);

            System.out.println("1  "+ingredients.get(item).getStock());
        }

    }

    @Override
    public void fabriquerCommande(ObservableList<Pizza> commande) {

    }

}
