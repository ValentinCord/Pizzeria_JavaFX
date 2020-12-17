package be.ac.umons.decorationPizza;

import be.ac.umons.Pizza;
import java.math.BigDecimal;

public class Pan extends Decoration{

    public Pan(Pizza pizza) {
        pizza.setName("Pan " + pizza.getName());
        pizza.setPrice(pizza.getPrice().add(BigDecimal.valueOf(1.5)));
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public BigDecimal getPrice() {
        return null;
    }

    @Override
    public void setName(String name) {
    }

    @Override
    public void setPrice(BigDecimal price) {
    }

    @Override
    public String toString() {
        return null;
    }

}
