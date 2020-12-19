package be.ac.umons.state;

import be.ac.umons.Ingredient;

import java.util.ArrayList;
import java.util.Map;

public interface State {
    void currentState(Context context);


    void reapprovisionner(ArrayList<String> emptyIngredient, Map<String, Ingredient> ingredients);
}
