package be.ac.umons;

import be.ac.umons.state.Fabrication;
import javafx.collections.ObservableList;

public class MonThread implements Runnable{

    int timer;
    Thread t;
    Pizza fabPizza;

    public MonThread(Pizza fabPizza,int timer) {
        this.timer = timer;
        this.fabPizza = fabPizza;
    }

    @Override
    public void run() {
        try {
            System.out.println("je vais travailler");
            t.sleep(timer);
            System.out.println(fabPizza + "donne");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
