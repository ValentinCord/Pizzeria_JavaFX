package be.ac.umons.decorationPizza;

import be.ac.umons.Pizza;
import java.math.BigDecimal;

public class Cheesy extends Decoration {

    public Cheesy(Pizza pizza) {
        pizza.setName("Cheesy " + pizza.getName());
        pizza.setPrice(pizza.getPrice().add(BigDecimal.valueOf(2)));
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
