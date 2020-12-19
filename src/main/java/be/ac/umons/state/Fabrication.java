package be.ac.umons.state;

import be.ac.umons.Ingredient;
import be.ac.umons.MonThread;
import be.ac.umons.Pizza;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Map;

public class Fabrication implements State{

    public Fabrication() {
        System.out.println("Fabrication");
    }

    @Override
    public void currentState(Context context) {
    }

    @Override
    public void reapprovisionner(ArrayList<String> emptyIngredient, Map<String, Ingredient> ingredients) {
    }

    @Override
    public void fabriquerCommande(ObservableList<Pizza> commande) {

        ThreadGroup monThreadGroup = new ThreadGroup("Main");

        do {
            while (monThreadGroup.activeCount() < 2 && !commande.isEmpty()) {
                Pizza fabPizza = commande.get(0);
                System.out.println("je cree un thread");
                MonThread e = new MonThread(fabPizza, 3000);
                Thread t = new Thread(monThreadGroup,e);
                t.start();
                commande.remove(0);
                System.out.println(commande);
            }
        } while (!commande.isEmpty());
    }

}
