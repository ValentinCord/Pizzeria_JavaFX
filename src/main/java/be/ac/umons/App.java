package be.ac.umons;

import be.ac.umons.database.DBSingleton;
import be.ac.umons.util.AnsiColor;
import be.ac.umons.util.ColorPrint;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            scene = new Scene(loadFXML("choixFactory"), 640, 480);
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
        // mettre dans main dans une fonction /!\ faire une methode static pour return la map dans la classe controller (voir factoryReturn)
    try {
        DBSingleton db = DBSingleton.getSingleton("jdbc:mysql://localhost:3306/tp6_db_java", "root", "");
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

}