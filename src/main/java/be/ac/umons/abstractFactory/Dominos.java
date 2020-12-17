package be.ac.umons.abstractFactory;

public class Dominos extends FabriqueAbstraite {
    private static Dominos instance;

    public Dominos() {
    }

    @Override
    public ChoixPizza createPizza() {
        return new ChoixPizzaNormal();
    }

    public static Dominos getInstance(){
        if (instance == null){
            instance = new Dominos();
        }
        return instance;
    }

}
