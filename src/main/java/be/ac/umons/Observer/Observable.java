package be.ac.umons.Observer;

public interface Observable {

    void addTheObserver(TheObserver obs);
    void removeTheObserver(TheObserver obs);
    void notifyTheObserver();
    void getUpdate();

}
