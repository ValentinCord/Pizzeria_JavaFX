package be.ac.umons.Observer;

public class Reparateur implements TheObserver{

    private Observable engins;

    @Override
    public void update() {
        System.out.println("Le réparateur est informé");
    }

    @Override
    public void setObservable(Observable observable) {
        if(observable == null){
            throw new NullPointerException("The subject is null");
        }
        this.engins = observable;
    }
}
