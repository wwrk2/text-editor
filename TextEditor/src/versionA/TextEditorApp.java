package versionA;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;

public class TextEditorApp extends Application {
	
	private static TextEditorController controller = new TextEditorController();
	private static Scene scene = new Scene(controller.getTextEditorView(), 400, 500);
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setScene(scene);
		primaryStage.setTitle("Lisa v1.01");
		primaryStage.show();	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}



