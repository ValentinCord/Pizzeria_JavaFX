package be.ac.umons.state;

import be.ac.umons.Ingredient;

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


}
