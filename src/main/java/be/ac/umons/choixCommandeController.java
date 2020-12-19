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
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
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
    //State state;
    State attenteState = new Attente();
    State panneState = new Panne();
    State manqueState = new Manque();
    State fabricationState = new Fabrication();
    ObservableList<String> EmptyIngredient = FXCollections.observableArrayList();


    //Variables JavaFX avec le meme ID que dans scene builder
    @FXML private ChoiceBox choixPizza = new ChoiceBox();
    @FXML private ChoiceBox choixDeco = new ChoiceBox();
    @FXML private ListView commandeView = new ListView();
    @FXML private Label titre = new Label();
    @FXML private Label prix = new Label();
    @FXML private Button approvisionner = new Button();
    @FXML private Button reparation = new Button();

    //Initialisation des objets JavaFX
    @FXML private void initialize(){
        choixPizza.setValue("Margherita");
        choixPizza.setItems(listPizza);
        choixDeco.setValue("Aucune");
        choixDeco.setItems(listDeco);
        titre.setText("Bienvenue chez "+factory);
        //initialisation de la fabrique
        if(factory == "PizzaHut"){
            fabrique = new PizzaHut();
        }else if(factory == "Domino's") {
            fabrique = new Dominos();
        }
        //Au départ le prix vaut 0 euros
        prix.setText("Prix totale : 0 euros");
        //Bouton invisible
        approvisionner.setVisible(false);
        reparation.setVisible(false);
    }

    @FXML protected void handleAjouter (ActionEvent event) throws IOException {
        //Get de la pizza et de la decoration choisie
        String p = (String) choixPizza.getSelectionModel().getSelectedItem();
        String d = (String) choixDeco.getSelectionModel().getSelectedItem();
        commandeView.setItems(commande);

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
        }

        //Calcul du prix total + affichage
        totalPrice = totalPrice.add(price);
        prix.setText("Prix totale : "+ totalPrice +" euros");
    }

    @FXML protected void handleCommander (ActionEvent event) throws IOException, InterruptedException {
        //Check si on est en panne
        int random = (int)(Math.random()*100);
        //Check si il manque des ingredient
        for(String key: ingredients.keySet()){
            if (ingredients.get(key).getStock()< 0){
                EmptyIngredient.add(ingredients.get(key).getName());
                System.out.println(ingredients.get(key)+" "+ingredients.get(key).getStock());
            }
        }

        //1ere condition pour aller dans l'etat "panne"
        if (random < 100){
            context.setState(panneState);
        }

        //2eme conditions pour aller dans l'etat "manque"
        else if(!EmptyIngredient.isEmpty()){
            context.setState(manqueState);
            approvisionner.setVisible(true);

        }

        //3eme conditions pour aller dans l'etat "fabrication"
        else {
            context.setState(fabricationState);
            context.fabriquerCommande(commande);
            context.setState(attenteState);
            commande.clear();
        }
    }

    @FXML protected void handleReparation (ActionEvent event) throws IOException{
        context.setState(attenteState);
    }

    @FXML protected void handleAppro (ActionEvent event) throws IOException{
        ingredients = context.reapprovisioner(EmptyIngredient, ingredients);
        for(String key: ingredients.keySet()){
            System.out.println(ingredients.get(key)+" "+ingredients.get(key).getStock());
        }
        context.setState(attenteState);
        EmptyIngredient.clear();
        approvisionner.setVisible(false);
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
