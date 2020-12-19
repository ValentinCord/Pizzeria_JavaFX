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

        Pizza firstPizza;
        Pizza nextPizza;

        MonThread e1 = new MonThread(commande, 15000);
        Thread t1 = new Thread(e1);
        MonThread e2 = new MonThread(commande, 10000);
        Thread t2 = new Thread(e2);

        while(commande.size()>1){
            if(!t1.isAlive()){
                t1.start();
            }
            if(!t2.isAlive()){
                t2.start();
            }

        }


/*
        for(int i=0; i<commande.size(); i++){

            MonThread producteur1 = new MonThread(commande);
            producteur1.run();

            //new Thread(new MonThread()).start();
        }


 */
        System.out.println("on est dan la fabrication");

    }

}
