package view.Custom;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class AvailableCreditsView extends GridPane {
	
	private Label title = new Label();
	private Label termOneTitle = new Label();
	private Label termTwoTitle = new Label();
	
	public Label termOneAvailableCreditsLabel = new Label();
	public Label termTwoAvailableCreditsLabel = new Label();
	
	public AvailableCreditsView() {
		title.setText("Available Credits");
		termOneTitle.setText("Term 1:");
		termTwoTitle.setText("Term 2:");
		
		termOneAvailableCreditsLabel.setText("120");
		termTwoAvailableCreditsLabel.setText("120");
		
		layoutViews();
	}
	
	private void layoutViews() {
		setVgap(5);
		setAlignment(Pos.CENTER);
		
		add(title, 0, 0);
		title.setPadding(new Insets(5, 5, 5, 5));
		
		add(termOneTitle, 0, 1);
		add(termTwoTitle, 0, 2);
		
		add(termOneAvailableCreditsLabel, 1, 1);
		add(termTwoAvailableCreditsLabel, 1, 2);
	}
}
