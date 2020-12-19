package be.ac.umons.pizzas;

import be.ac.umons.Ingredient;
import be.ac.umons.Pizza;
import java.math.BigDecimal;
import java.util.Map;

public class Carbonara extends Pizza {

    public Carbonara(Map<String, Ingredient> c) {
        setName("Carbonara");
        addIngredient(c.get("Dough"));
        c.get("Dough").setStock(c.get("Dough").getStock()-1);
        addIngredient(c.get("White Cream"));
        c.get("White Cream").setStock(c.get("White Cream").getStock()-1);
        addIngredient(c.get("Cheese"));
        c.get("Cheese").setStock(c.get("Cheese").getStock()-1);
        addIngredient(c.get("Ham"));
        c.get("Ham").setStock(c.get("Ham").getStock()-1);

        BigDecimal price = getListIngredient()
                .stream()
                .map(Ingredient::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        setPrice(price);
    }
}
