package be.ac.umons;

import javafx.collections.ObservableList;

public class MonThread implements Runnable{

    ObservableList<Pizza> commande;
    int timer;
    Thread t;
    Pizza fabPizza;


    public MonThread(ObservableList<Pizza> commande, int timer) {
        this.commande = commande;
        this.timer = timer;
    }

    @Override
    public void run() {
        /*
        System.out.println("Preparation"+ commande.get(i) + ":");
        try {
            t1.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Prepartion terminee");
        */
        try {
            fabPizza = commande.get(0);
            commande.remove(0);
            t.sleep(timer);
            System.out.println(fabPizza+ "donne");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
