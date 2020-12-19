package be.ac.umons.state;

import be.ac.umons.Ingredient;
import be.ac.umons.Pizza;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Map;

public class Context {
    private State state;

    public Context() {
        this.state = new Attente();
    }
    public void setState(State newState){
        this.state = newState;
    }

    public void currentState(){
        state.currentState(this);
    }


    public void reapprovisioner(ArrayList<String> emptyIngredient, Map<String, Ingredient> ingredients) {
        state.reapprovisionner(emptyIngredient, ingredients);
    }

    public void fabriquerCommande(ObservableList<Pizza> commande) {

        state.fabriquerCommande(commande);
    }
}
