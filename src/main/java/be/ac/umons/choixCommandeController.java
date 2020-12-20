package be.ac.umons;

import be.ac.umons.observer.*;
import be.ac.umons.observer.Observable;
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
import be.ac.umons.util.AnsiColor;
import be.ac.umons.util.ColorPrint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;


public class choixCommandeController {

    //Observer -> Pour les réservoirs
    TheObserver fournisseur = new Fournisseur();
    Observable reservoir = new ObservationResevoir();
    //Observer -> Pour la panne
    TheObserver reparateur = new Reparateur();
    Observable engins = new ObservationPanne();
    //ChoiceBox
    ObservableList<String> listPizza = FXCollections.observableArrayList("Margherita", "Proscuitto", "Carbonara","FruttiDiMare");
    ObservableList<String> listDeco = FXCollections.observableArrayList("Cheesy", "Pan", "Aucune");
    //ListView
    ObservableList<Pizza> commande = FXCollections.observableArrayList();
    //Recuperation des ingredients et de la factory
    Map<String, Ingredient> ingredients = new HashMap<>();
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


    //Variables JavaFX avec le meme Id que dans scene builder
    @FXML private ChoiceBox choixPizza = new ChoiceBox();
    @FXML private ChoiceBox choixDeco = new ChoiceBox();
    @FXML private ListView commandeView = new ListView();
    @FXML private Label titre = new Label();
    @FXML private Label prix = new Label();
    @FXML private Button approvisionner = new Button();
    @FXML private Button reparation = new Button();
    @FXML private TextArea notice = new TextArea();

    //Initialisation des objets JavaFX
    @FXML private void initialize(){
        //Fournisseur
        reservoir.addTheObserver(fournisseur);
        fournisseur.setObservable(reservoir);
        //Réparateur
        engins.addTheObserver(reparateur);
        reparateur.setObservable(engins);
        //Pizza
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
        //TextArea de notice
         notice.setText("choisir une commande");

        try {
            DBSingleton db = DBSingleton.getSingleton("jdbc:mysql://localhost:3306/tp6_db_java", "root", "");

            ResultSet rs = db.querySelect("SELECT * FROM ingredients");
            while (rs.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setName(rs.getString("name"));
                ingredient.setPrice(rs.getBigDecimal("price"));
                ingredient.setStock(rs.getInt("stock"));
                ingredients.put(ingredient.getName(), ingredient);
            }
            rs.close();
        } catch (SQLException e) {
            ColorPrint.printError("SQL ERROR : " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.print(AnsiColor.RED);
            e.printStackTrace();
            System.out.print(AnsiColor.RESET);
        }
        ingredients.forEach((k, v) -> System.out.println(k + " : " + v.getPrice() + " €, " + v.getStock() + " disponible(s) "));
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
        if (random < 10){
            context.setState(panneState);
            reparation.setVisible(true);
            engins.notifyTheObserver();
            notice.setText("réparer la machine");
        }

        //2eme conditions pour aller dans l'état "manque"
        else if(!EmptyIngredient.isEmpty()){
            context.setState(manqueState);
            approvisionner.setVisible(true);
            reservoir.notifyTheObserver();
            notice.setText("il manque d'ingredients");

        }

        //3eme conditions pour aller dans l'état "fabrication"
        else {
            notice.setText("confection en cours");
            context.setState(fabricationState);
            context.fabriquerCommande(commande);
            context.setState(attenteState);
            commande.clear();
            notice.setText("choisir une nouvelle commande");
        }
    }

    @FXML protected void handleReparation (ActionEvent event) throws IOException{
        context.setState(attenteState);
        reparation.setVisible(false);
        engins.getUpdate();
        notice.setText("choisir une commande");
    }

    @FXML protected void handleAppro (ActionEvent event) throws IOException{
        ingredients = context.reapprovisioner(EmptyIngredient, ingredients);
        for(String key: ingredients.keySet()){
            System.out.println(ingredients.get(key)+" "+ingredients.get(key).getStock());
        }
        context.setState(attenteState);
        EmptyIngredient.clear();
        approvisionner.setVisible(false);
        reservoir.getUpdate();
        notice.setText("choisir une commande");
    }

    @FXML protected void handleRetour (ActionEvent event) throws IOException{
        App.setRoot("choixFactory");
    }

}
