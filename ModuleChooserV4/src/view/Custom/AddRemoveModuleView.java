package view.Custom;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AddRemoveModuleView extends HBox {

	private Label textFieldLabel = new Label();
	public Button addModuleButton = new Button("Add");
	public Button removeModuleButton = new Button("Remove");
	private String title;
	
	public AddRemoveModuleView(String title) {
		this.title = title;
		setupLayout();
	}
	
	private void setupLayout() {
	    setSpacing(10);
	    textFieldLabel.setText(title);
	    setAlignment(Pos.CENTER);
	  
	    addModuleButton.setPrefSize(100, 20);
	    removeModuleButton.setPrefSize(100, 20);
	    getChildren().addAll(textFieldLabel, removeModuleButton, addModuleButton);
	}
}
