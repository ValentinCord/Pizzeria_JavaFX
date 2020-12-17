package be.ac.umons.abstractFactory;

import be.ac.umons.Ingredient;
import be.ac.umons.Pizza;
import java.util.Map;

public class ChoixPizzaHut extends ChoixPizza { ;

    public Pizza type(Pizza pizza, Map<String, Ingredient> c){

        pizza.addIngredient(c.get("Olive"));

        pizza.setPrice(pizza.getPrice().add(c.get("Olive").getPrice()));

        return pizza;
    }
}
