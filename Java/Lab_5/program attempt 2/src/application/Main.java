package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("МАШИНЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫ");
		stage.getIcons().add(new Image("duke.png"));
		stage.setResizable(false);
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Layout.fxml"))));
		stage.show();
	}
	public static void main(String[] args) { launch(args); }

}
