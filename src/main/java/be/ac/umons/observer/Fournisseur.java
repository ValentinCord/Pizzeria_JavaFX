package be.ac.umons.observer;

public class Fournisseur implements TheObserver {

    private Observable reservoir;


    @Override
    public void update() {
        System.out.println("Le fournisseur est informé");
    }

    @Override
    public void setObservable(Observable observable) {
        if(observable == null){
            throw new NullPointerException("The subject is null");
        }
        this.reservoir = observable;
    }
}
