package application;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import database.DatabaseReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import login.LoginController;
import login.LoginWrapper;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	public static void main(String[] args) {
		
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	//Raj
    	//LoginController loginController = new LoginController(new LoginWrapper(new DatabaseReader().readUserDatabase(getClass().getResource("/Users/rajatmittal/Desktop/Eclipse /DSFinalProject/DSFinalProjectFXML/src/database/userDB.txt"),new DatabaseReader().readRestaurantDatabase(getClass().getResource("/Users/rajatmittal/Desktop/Eclipse /DSFinalProject/DSFinalProjectFXML/src/database/restaurantDB.txt"), new Stage()))));
    	//Windows
    	LoginController loginController = new LoginController(new LoginWrapper(new DatabaseReader().readUserDatabase(getClass().getResource("/database/userDB.txt").getPath().replaceAll("/", "\\\\").substring(1).replaceAll("%20", " ").replaceAll("bin", "src")),new DatabaseReader().readRestaurantDatabase(getClass().getResource("/database/restaurantDB.txt").getPath().replaceAll("/", "\\\\").substring(1).replaceAll("%20", " ").replaceAll("bin", "src")), new Stage()));
    	loginController.showStage();
    }
}
