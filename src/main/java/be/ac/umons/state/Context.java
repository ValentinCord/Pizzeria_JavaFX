package be.ac.umons.state;

import be.ac.umons.Ingredient;
import be.ac.umons.Pizza;
import javafx.collections.ObservableList;

import java.util.Map;

public class Context {
    private State state;

    public Context() {
        this.state = new Attente();
    }

    public void setState(State newState){
        this.state = newState;
    }

    public Boolean reparer(Boolean panne){
        state.reparer(panne);
        return panne;
    }

    public Map<String, Ingredient> reapprovisioner(ObservableList<String> emptyIngredient, Map<String, Ingredient> ingredients) {
        ingredients = state.reapprovisionner(emptyIngredient, ingredients);
        return ingredients;
    }

    public void fabriquerCommande(ObservableList<Pizza> commande) throws InterruptedException {
        state.fabriquerCommande(commande);
    }
}
