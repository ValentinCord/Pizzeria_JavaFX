package be.ac.umons.observer;

import java.util.ArrayList;
import java.util.List;

public class ObservationPanne implements Observable{

    List<TheObserver> listObservers = new ArrayList<>();
    @Override
    public void addTheObserver(TheObserver obs) {
        if(obs == null){
            throw new NullPointerException("the observer passed is null");
        }
        if(!listObservers.contains(obs)){
            this.listObservers.add(obs);
        }
    }

    @Override
    public void removeTheObserver(TheObserver obs) {
        if(obs == null){
            throw new NullPointerException("the observer passed is null");
        }
        if(listObservers.contains(obs)){
            this.listObservers.remove(obs);
        }
    }

    @Override
    public void notifyTheObserver() {
        System.out.println("On notifie les obsevers de la panne");
        for (TheObserver item: listObservers){
            item.update();
        }
    }

    @Override
    public void getUpdate() {
        System.out.println("Le réparateur a remis la machine en état de marche");
    }
}
