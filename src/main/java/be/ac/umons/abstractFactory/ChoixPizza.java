package be.ac.umons.abstractFactory;

import be.ac.umons.Ingredient;
import be.ac.umons.Pizza;

import java.util.Map;

public abstract class ChoixPizza {
    public abstract Pizza type(Pizza pizza, Map<String, Ingredient> c);
}
