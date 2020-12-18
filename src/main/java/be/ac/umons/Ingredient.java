package be.ac.umons;

import java.math.BigDecimal;
import java.util.List;
import java.util.Observer;

public class Ingredient implements PizzaComponent {
    private String name;
    private BigDecimal price;
    int stock;
    public List<Observer> observers;




    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public BigDecimal getPrice() {
        return price;
    }
    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    @Override
    public String toString() {
        /*return "Ingredient{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';*/
        return name;
    }
    public void register(Observer obs){
        if (obs == null){
            throw new NullPointerException(("This osb is null"));
        }

        if (!observers.contains(obs)){
            observers.add(obs);
        }
    }
    public void unregister(Observer obs){
        if (obs == null){
            throw new NullPointerException(("This observer is null"));
        }

        if (!observers.contains(obs)){
            observers.remove(obs);
        }
    }

    public void notify(List<Observer> observers){

    }


}
