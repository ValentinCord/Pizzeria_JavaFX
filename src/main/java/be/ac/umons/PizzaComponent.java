package be.ac.umons;

import java.math.BigDecimal;

public interface PizzaComponent {
    String getName();
    BigDecimal getPrice();
    void setName(String name);
    void setPrice (BigDecimal price);
    String toString();
}
