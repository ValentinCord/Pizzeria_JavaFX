package be.ac.umons;

import be.ac.umons.database.DBSingleton;
import be.ac.umons.decorationPizza.Cheesy;
import be.ac.umons.decorationPizza.Decoration;
import be.ac.umons.decorationPizza.Pan;

import be.ac.umons.abstractFactory.ChoixPizza;
import be.ac.umons.abstractFactory.Dominos;
import be.ac.umons.abstractFactory.FabriqueAbstraite;
import be.ac.umons.abstractFactory.PizzaHut;

import be.ac.umons.pizzas.Carbonara;
import be.ac.umons.pizzas.FruttiDiMare;
import be.ac.umons.pizzas.Margherita;
import be.ac.umons.pizzas.Proscuitto;
import be.ac.umons.state.*;
import be.ac.umons.util.ColorPrint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;


public class choixCommandeController {

    //ChoiceBox
    ObservableList<String> listPizza = FXCollections.observableArrayList("Margherita", "Proscuitto", "Carbonara","FruttiDiMare");
    ObservableList<String> listDeco = FXCollections.observableArrayList("Cheesy", "Pan", "Aucune");
    //ListView
    ObservableList<Pizza> commande = FXCollections.observableArrayList();
    //Recuperation des ingredients et de la factory
    Map<String, Ingredient> ingredients = App.ingredientsReturn();
    String factory = choixFactoryController.factoryReturn();

    FabriqueAbstraite fabrique;

    //Price
    BigDecimal totalPrice = BigDecimal.ZERO;
    BigDecimal price = BigDecimal.ZERO;

    //State et Context
    Context context = new Context();
    State panneState = new Panne();
    State manqueState = new Manque();
    State fabricationState = new Fabrication();
    Boolean panne = false;
    Boolean manque = false;
    Boolean fabrication = false;

    //Variables JavaFX
    @FXML private ChoiceBox choixPizza;
    @FXML private ChoiceBox choixDeco;
    @FXML private ListView commandeView;
    @FXML private Label titre;
    @FXML private Label prix;

    //Initialisation des objets JavaFX
    @FXML private void initialize(){
        choixPizza.setValue("Margherita");
        choixPizza.setItems(listPizza);
        choixDeco.setValue("Aucune");
        choixDeco.setItems(listDeco);
        titre.setText("Bienvenue chez "+factory);

        if(factory == "PizzaHut"){
            fabrique = new PizzaHut();
        }else if(factory == "Domino's") {
            fabrique = new Dominos();
        }

        prix.setText("Prix totale : 0 euros");
    }

    @FXML protected void handleAjouter (ActionEvent event) throws IOException {
        //Get de la pizza et de la decoration choisie
        String p = (String) choixPizza.getSelectionModel().getSelectedItem();
        String d = (String) choixDeco.getSelectionModel().getSelectedItem();

        //Creation de la pizza + deco
        //Ajout de la pizza a la commande
        //Calcul de son prix
        //Ajout de la pizza a la listView
        if (p == "Margherita"){
            ChoixPizza pizzaLocation = fabrique.createPizza();
            Margherita pizza = new Margherita(ingredients);
            pizzaLocation.type(pizza, ingredients);
            if(d == "Cheesy"){
                Decoration deco = new Cheesy(pizza);
            }
            else if (d == "Pan"){
                Decoration deco = new Pan(pizza);
            }
            commande.add(pizza);
            price = pizza.getPrice();
            commandeView.getItems().add(pizza.toString());
            //commandeView.setItems(commande);
        }
        else if (p == "Proscuitto") {
            ChoixPizza pizzaLocation = fabrique.createPizza();
            Proscuitto pizza = new Proscuitto(ingredients);
            pizzaLocation.type(pizza, ingredients);
            if(d == "Cheesy"){
                Decoration deco = new Cheesy(pizza);
            }
            else if (d == "Pan"){
                Decoration deco = new Pan(pizza);
            }
            commande.add(pizza);
            price = pizza.getPrice();
            commandeView.setItems(commande);
        }
        else if (p == "Carbonara") {
            ChoixPizza pizzaLocation = fabrique.createPizza();
            Carbonara pizza = new Carbonara(ingredients);
            pizzaLocation.type(pizza, ingredients);
            if(d == "Cheesy"){
                Decoration deco = new Cheesy(pizza);
            }
            else if (d == "Pan"){
                Decoration deco = new Pan(pizza);
            }
            commande.add(pizza);
            price = pizza.getPrice();
            commandeView.setItems(commande);
        }
        else if (p == "FruttiDiMare") {
            ChoixPizza pizzaLocation = fabrique.createPizza();
            FruttiDiMare pizza = new FruttiDiMare(ingredients);
            pizzaLocation.type(pizza, ingredients);
            if(d == "Cheesy"){
                Decoration deco = new Cheesy(pizza);
            }
            else if (d == "Pan"){
                Decoration deco = new Pan(pizza);
            }
            commande.add(pizza);
            price = pizza.getPrice();
            commandeView.setItems(commande);
        }

        //Calcul du prix total + affichage
        totalPrice = totalPrice.add(price);
        prix.setText("Prix totale : "+ totalPrice +" euros");
    }

    @FXML protected void handleCommander (ActionEvent event) throws IOException {
        //Genere un integer de 0 a 100
        int random = (int)(Math.random()*100);
        if (random <10 || panne== true){
            panne = true;
            //ca marche po :(
            context.setState(panneState);
            context.currentState();

            // aller modif la fonction current state qui dira que la machine est en panne
        }


        //1 si on est en panne (soit de facon random soit tout les X pizza) -> PANNE

        //Map<Ingredient, Integer> stock = new HashMap<>();
        //ingredients.forEach((k,v) -> stock.put(v,0));
        //commande.forEach(p -> p.getListIngredient().forEach(i -> stock.put(i, stock.get(i)+1)));

        ArrayList<String> EmptyIngredient = new ArrayList<>();

        for(String key: ingredients.keySet()){
            if (ingredients.get(key).getStock()< 0){
                EmptyIngredient.add(ingredients.get(key).getName());
                System.out.println(ingredients.get(key)+" "+ingredients.get(key).getStock());
            }
        }
        System.out.println(EmptyIngredient);

        if(!EmptyIngredient.isEmpty()){
            context.setState(manqueState);
            context.reapprovisioner(EmptyIngredient, ingredients);

        }else {
            context.setState(fabricationState);
            context.fabriquerCommande(commande);

        }




        /*
        2 si il manque des ingrédients -> MANQUE :
            somme(commande -> chaque Pizza(sauce,tomate,fromage) -> chaque ingredient) <<<<<<<< Map = ingredients
            solution = Map(String,Quantite)->Quantite++ a la cle String
        3 Sinon -> FABRICATION => 2 Thread qui se partage les pizza (60s/pizza)
        -> context.setState(Fabrication)
        context.currentState()
         */

    }

    @FXML protected void handleRetour (ActionEvent event) throws IOException{
        App.setRoot("choixFactory");
    }

    /**
     * Insert, update, delete example.
     */
    private static void updateQueryDemo() {
        try {
            //DBSingleton db = DBSingleton.getSingleton(url, username, password);
            DBSingleton db = DBSingleton.getSingleton();

            Object[] arguments = new Object[2];
            arguments[0] = formatDotDecimal(new BigDecimal(10.2));
            arguments[1] = "N/A";

            int result = db.queryUpdate("INSERT INTO ingredients(name, price) VALUES ('N/A', '0.0')");
            ColorPrint.printDebug("inserting N/A into ingredients, return value : " + result);

            result = db.queryUpdate("UPDATE ingredients SET price = %s WHERE name = '%s'", arguments);
            ColorPrint.printDebug("updating ingredients return value : " + result);

            ResultSet rs = db.querySelect("SELECT * FROM ingredients");

            while (rs.next()) {
                System.out.println(rs.getString("name") + " : " + rs.getBigDecimal("price"));
            }
            rs.close();

            result = db.queryUpdate("DELETE FROM ingredients WHERE name = '%s'", arguments[1]);
            ColorPrint.printDebug("delete dough from ingredients : " + result);

        } catch (SQLException e) {
            ColorPrint.printError("SQL Error : " + e.getMessage());
        } catch (NullPointerException e) {
            ColorPrint.printError(e.getMessage());
        }
    }

    /**
     * Get a money value as a string with a dot separator for decimals
     * @param money the value to format
     * @return a string representation of a money value
     */
    public static String formatDotDecimal(BigDecimal money) {
        DecimalFormatSymbols decimalSymbol = DecimalFormatSymbols.getInstance();
        decimalSymbol.setDecimalSeparator('.');
        return new DecimalFormat("0.00", decimalSymbol).format(money);
    }
}
