package view.Custom;

import model.Module;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class TitledListView<T> extends VBox {
	
	public ListView<T> listView = new ListView<T>();
	public Label titleTextField = new Label();
	
	private String title;
	
	public TitledListView(String title) {
		this.title = title;
		layoutViews();
	}
	
	private void layoutViews() {
	    setSpacing(5);
	    titleTextField.setText(title);
	    getChildren().addAll(titleTextField, listView);
	}
}
