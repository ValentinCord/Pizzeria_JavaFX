package be.ac.umons.state;

import be.ac.umons.Ingredient;
import be.ac.umons.Pizza;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.CheckBoxListCell;

import java.util.ArrayList;
import java.util.Map;

public class Fabrication implements State{


    public Fabrication() {
        System.out.println("Etat de fabrication");
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
    public void fabriquerCommande(ObservableList<Pizza> commande) throws InterruptedException {
        ThreadGroup monThreadGroup = new ThreadGroup("Main");
        do {
            while (monThreadGroup.activeCount() < 2 && !commande.isEmpty()) {
                Pizza fabPizza = commande.get(0);
                MonThread e = new MonThread(fabPizza, 10000);
                Thread t = new Thread(monThreadGroup, e);
                t.start();
                commande.remove(0);
            }

        } while (!commande.isEmpty());

        MonThread.finirPeparation();
        System.out.println("Preparation terminée");
    }
}
