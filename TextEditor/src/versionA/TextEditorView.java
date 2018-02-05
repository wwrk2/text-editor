package versionA;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.beans.value.*;

public class TextEditorView extends BorderPane {
	
	/* Open & Save Buttons */
	private final SaveButton btnSave = new SaveButton("Save");
	private final OpenButton btnOpen = new OpenButton("Open");
	
	/* Flow Pane for buttons, and etc. */
	private final FlowPane flowPane = new FlowPane();
	
	/* Text Area */
	private final TextArea textArea = new TextArea();
	
	/* Label to display time and date. */
	private final Label label = new Label("display time here");
	
	/* Combo box for font size. */
	private final ComboBox<String> comboBoxFontSize = new ComboBox<>();
	
	public TextEditorView(TextEditorController controller) {	
		/* Set border pane colors. */
		this.setStyle("-fx-background-color: #63A5DF;");
		
		/* Add font sizes to combo box, make it editable,
		 * set its size and style, and add listener. */
//		comboBoxFontSize.getContextMenu().setStyle("-fx-background: #EDED18;");
		comboBoxFontSize.setPrefWidth(55);
		comboBoxFontSize.setEditable(true);
		comboBoxFontSize.setPromptText("12"); // default font size is 12
		comboBoxFontSize.getItems().addAll("6", "8", "10", "12", "14", "16", "18", "20",
											"22", "24", "26", "28", "36", "48", "72");
		comboBoxFontSize.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
										String oldValue, String newValue) {
				textArea.setStyle("-fx-font-size: " + newValue + ";");
			}
			
		});
		
		/* Resize label for displaying time. */
		label.setPrefHeight(35);
		
		/* Set flow pane horizontal gap, and preferred height. */
		flowPane.setPrefHeight(40);
		flowPane.setHgap(5.0);
		
		/* Add buttons to flow pane. */
		flowPane.getChildren().addAll(btnSave, btnOpen, comboBoxFontSize);
		
		/* Register controller with buttons. */
		btnSave.setStyle("-fx-background-color: #EDED18;"
						+ "-fx-text-fill: #6C401A;");
		btnOpen.setStyle("-fx-background-color: #EDED18;"
						+ "-fx-text-fill: #6C401A;");
		btnSave.setOnAction(controller);
		btnOpen.setOnAction(controller);
		
		/* Set flow pane as top of border pane. */
		this.setTop(flowPane);
		
		/* Set text area as center of border pane. */
		this.setCenter(textArea);
		
		/* Set the label displaying time as bottom of border pane. */
		this.setBottom(label);
		
		/* Keep updating the current time. */
		KeyFrame update = new KeyFrame(Duration.seconds(1), event -> { 
		    updateTime();
		}); 
		
		Timeline tl = new Timeline(update); 
		tl.setCycleCount(Timeline.INDEFINITE);
		tl.play();
	}
	
	public String getTextAreaText() {
		return textArea.getText();
	}
	
	public void setTextAreaText(String text) {
		textArea.setText(text);
	}
	
	private void updateTime() {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		SimpleDateFormat dateFormat
			= new SimpleDateFormat("EEE, MMM d, yyyy, hh:mm:ss a z");
		
		label.setText(dateFormat.format(date));
	}
}