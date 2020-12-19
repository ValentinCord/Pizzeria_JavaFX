package be.ac.umons;

import be.ac.umons.database.DBSingleton;
import be.ac.umons.util.AnsiColor;
import be.ac.umons.util.ColorPrint;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/** JavaFX App **/

public class App extends Application {

    private Stage stage;
    private static Scene scene;
    private static Map<String, Ingredient> ingredients = new HashMap<>();

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        this.stage.setTitle("Pizzeria");

        try{
            scene = new Scene(loadFXML("choixFactory"), 805, 500);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {

    try {
        DBSingleton db = DBSingleton.getSingleton("jdbc:mysql://localhost:8889/tp6_db_java", "root", "root");

        ResultSet rs = db.querySelect("SELECT * FROM ingredients");
        while (rs.next()) {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(rs.getString("name"));
            ingredient.setPrice(rs.getBigDecimal("price"));
            ingredient.setStock(rs.getInt("stock"));
            ingredients.put(ingredient.getName(), ingredient);
            //ingredient.register(obs);
            //obs.setSubject(ingredient);
        }
        rs.close();
        //updateQueryDemo();
    } catch (SQLException e) {
        ColorPrint.printError("SQL ERROR : " + e.getMessage());
    } catch (NullPointerException e) {
        System.out.print(AnsiColor.RED);
        e.printStackTrace();
        System.out.print(AnsiColor.RESET);
    }
        ingredients.forEach((k, v) -> System.out.println(k + " : " + v.getPrice() + " €, " + v.getStock() + " disponible(s) "));
        launch();
    }

    public static Map<String, Ingredient> ingredientsReturn(){
        return ingredients;
    }

    public static Map<String, Ingredient> returnIngredients (){
        return ingredients;
    }

}