package be.ac.umons.pizzas;

import be.ac.umons.Ingredient;
import be.ac.umons.Pizza;
import java.math.BigDecimal;
import java.util.Map;

public class FruttiDiMare extends Pizza {

    public FruttiDiMare(Map<String, Ingredient> c) {
        setName("FruttiDiMare");
        addIngredient(c.get("Dough"));
        c.get("Dough").setStock(c.get("Dough").getStock()-1);
        addIngredient(c.get("Tomato Sauce"));
        c.get("Tomato Sauce").setStock(c.get("Tomato Sauce").getStock()-1);
        addIngredient(c.get("Cheese"));
        c.get("Cheese").setStock(c.get("Cheese").getStock()-1);
        addIngredient(c.get("Sea food"));
        c.get("Sea food").setStock(c.get("Sea food").getStock()-1);

        BigDecimal price = getListIngredient()
                .stream()
                .map(Ingredient::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        setPrice(price);

    }
}
