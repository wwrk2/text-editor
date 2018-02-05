package versionA;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.*;
import javafx.stage.*;
import javafx.stage.FileChooser.*;
import java.io.*;
import javafx.scene.control.*;

public class TextEditorController implements EventHandler<ActionEvent> {
	
	/* View */
	private final TextEditorView view = new TextEditorView(this);
	
	/* Save and open files. */
	FileChooser fileChooser = new FileChooser();
	
	public TextEditorController() {
		// Add extension filter to file chooser.
		ExtensionFilter extensionFilter = 
				new ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);
        
        
	}
	
	@Override
	public void handle(ActionEvent actionEvent) {
		String buttonType
			= actionEvent.getSource().getClass().getSimpleName();
		
		if (buttonType.equals("SaveButton")) {
			File newFile = fileChooser.showSaveDialog(new Stage());
			String text = view.getTextAreaText();
			
			try {
				TextEditorModel.writeToFile(newFile, text);
			} catch (IOException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Save failed.");
				alert.setContentText("Could not save file.");
			}
			
		} else { // buttonType.equals("OpenButton")
			File openedFile = fileChooser.showOpenDialog(new Stage());
			try {
				String text = TextEditorModel.getTextFromFile(openedFile);
				view.setTextAreaText(text);
			} catch (IOException exc) {
				view.setTextAreaText("error");
			}
		}
	}
	
	public TextEditorView getTextEditorView() {
		return view;
	}
}
