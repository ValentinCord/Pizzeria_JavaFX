package be.ac.umons.pizzas;

import be.ac.umons.Ingredient;
import be.ac.umons.Pizza;
import java.math.BigDecimal;
import java.util.Map;

public class Proscuitto extends Pizza {

    public Proscuitto(Map<String, Ingredient> c) {

        setName("Proscuitto");

        addIngredient(c.get("Dough"));
        addIngredient(c.get("Tomato Sauce"));
        addIngredient(c.get("Cheese"));
        addIngredient(c.get("Ham"));

        BigDecimal price = getListIngredient()
                .stream()
                .map(Ingredient::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        setPrice(price);
    }
}
