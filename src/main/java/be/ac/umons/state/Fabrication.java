package be.ac.umons.state;

import be.ac.umons.Ingredient;

import java.util.ArrayList;
import java.util.Map;

public class Fabrication extends Thread implements State{
    private Boolean etatFabrication = false;

    public Fabrication() {
        System.out.println("Fabrication");
    }

    @Override
    public void currentState(Context context) {
        try {
            sleep(60000);
            etatFabrication = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        context.setState(new Attente());

    }

    @Override
    public void reapprovisionner(ArrayList<String> emptyIngredient, Map<String, Ingredient> ingredients) {

    }

}
