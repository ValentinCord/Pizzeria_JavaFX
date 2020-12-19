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
    public void currentState(Context context) {
        context.setState(new Panne());
        context.setState(new Manque());

        context.setState(new Fabrication());

    }

    @Override
    public void reapprovisionner(ArrayList<String> emptyIngredient, Map<String, Ingredient> ingredients) {

    }

    @Override
    public void fabriquerCommande(ObservableList<Pizza> commande) {

    }


}
