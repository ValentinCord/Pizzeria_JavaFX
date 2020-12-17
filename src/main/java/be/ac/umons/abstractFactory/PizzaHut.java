package be.ac.umons.abstractFactory;

public class PizzaHut extends FabriqueAbstraite {
    private static PizzaHut instance;

    @Override
    public ChoixPizza createPizza() {
        return new ChoixPizzaHut();
    }

    public static PizzaHut getInstance(){
        if (instance == null){
            instance = new PizzaHut();
        }
        return instance;
    }
}
