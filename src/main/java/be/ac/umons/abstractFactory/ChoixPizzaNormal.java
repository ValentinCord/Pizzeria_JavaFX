package be.ac.umons.abstractFactory;

import be.ac.umons.Ingredient;
import be.ac.umons.Pizza;

import java.math.BigDecimal;
import java.util.Map;

public class ChoixPizzaNormal extends ChoixPizza {

    public Pizza type(Pizza pizza, Map<String, Ingredient> c){
        return pizza;
    }
}
