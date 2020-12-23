package be.ac.umons.state;

import be.ac.umons.Pizza;
import be.ac.umons.state.Fabrication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class MonThread implements Runnable{

    int timer;
    Thread t;
    Pizza fabPizza;

    public MonThread(Pizza fabPizza,int timer) {
        this.timer = timer;
        this.fabPizza = fabPizza;
    }

    public static void finirPeparation() throws InterruptedException {
        Thread.sleep(61000);
    }


    @Override
    public void run() {
        try {
            t.sleep(timer);
            System.out.println(fabPizza + "finie");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
