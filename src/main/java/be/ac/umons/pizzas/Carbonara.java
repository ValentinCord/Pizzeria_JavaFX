package be.ac.umons.pizzas;

import be.ac.umons.Ingredient;
import be.ac.umons.Pizza;
import java.math.BigDecimal;
import java.util.Map;

public class Carbonara extends Pizza {

    public Carbonara(Map<String, Ingredient> c) {

        setName("Carbonara");

        addIngredient(c.get("Dough"));
        addIngredient(c.get("White Cream"));
        addIngredient(c.get("Cheese"));
        addIngredient(c.get("Ham"));

        BigDecimal price = getListIngredient()
                .stream()
                .map(Ingredient::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        setPrice(price);
    }
}
