package view;

import java.util.Collection;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import model.Module;
import view.Custom.AddRemoveModuleView;
import view.Custom.AvailableCreditsView;
import view.Custom.TitledListView;

public class ReserveModulePane extends GridPane {
	
	
	private TitledListView<Module> availableTermOne = new TitledListView<Module>("Unselected - Term One");
	private TitledListView<Module> selectedTermOne = new TitledListView<Module>("Reserved - Term One");
	private TitledListView<Module> availableTermTwo = new TitledListView<Module>("Unselected - Term Two");
	private TitledListView<Module> selectedTermTwo = new TitledListView<Module>("Reserved - Term Two");
	
	private AddRemoveModuleView termOneModuleButtonView = new AddRemoveModuleView("Term 1");
	private AddRemoveModuleView termTwoModuleButtonView = new AddRemoveModuleView("Term 2");
	
	private Button resetButton = new Button("Reset");
	private Button submitButton = new Button("Submit");
	
	private Label reserveTitle = new Label("Select 30 credits of reserve modules per term");
	
	public ReserveModulePane() {
		layoutViews();
	}
	
	private void layoutViews() {
		setVgap(5);
		setHgap(5);
		setAlignment(Pos.CENTER);
		setPadding(new Insets(10, 10, 10, 10));
		
		ColumnConstraints col1 = new ColumnConstraints();
	    col1.setHgrow(Priority.ALWAYS);
	    
	    ColumnConstraints col2 = new ColumnConstraints();
	    col2.setHgrow(Priority.ALWAYS);

	    getColumnConstraints().addAll(col1, col2);
	    
	    reserveTitle.setPadding(new Insets(10, 0, 10, 10));
	    
	    add(reserveTitle, 0, 1);
		add(availableTermOne, 0, 2);
		add(termOneModuleButtonView, 0, 3);
		add(availableTermTwo, 0, 4);
		add(termTwoModuleButtonView, 0, 5);
		add(selectedTermOne, 1, 2);
		add(selectedTermTwo, 1, 4);
		
		HBox buttons = new HBox();
		buttons.setSpacing(5);
		buttons.setAlignment(Pos.BASELINE_RIGHT);
	    buttons.getChildren().addAll(resetButton, submitButton);
		add(buttons, 1, 7);
	}
	
	// Get current selections from lists		
	public Module getSelectedAvailableTermOneModule() {
		return availableTermOne.listView.getSelectionModel().getSelectedItem();
	}
	
	public Module getSelectedReservedTermOneModule() {
		return selectedTermOne.listView.getSelectionModel().getSelectedItem();
	}
	
	public Module getSelectedAvailableTermTwoModule() {
		return availableTermTwo.listView.getSelectionModel().getSelectedItem();
	}
	
	public Module getSelectedReservedTermTwoModule() {
		return selectedTermTwo.listView.getSelectionModel().getSelectedItem();
	}
	
	// Update Lists
	public void setAvailableTermOneModules(Collection<Module> modules) {
		availableTermOne.listView.getItems().clear();
		availableTermOne.listView.getItems().addAll(modules);
		availableTermOne.listView.getSelectionModel().select(0); //select first course by default
	}
	
	public void setReservedTermOneModules(Collection<Module> modules) {
		selectedTermOne.listView.getItems().clear();
		selectedTermOne.listView.getItems().addAll(modules);
		selectedTermOne.listView.getSelectionModel().select(0); //select first course by default
	}
	
	public void setAvailableTermTwoModules(Collection<Module> modules) {
		availableTermTwo.listView.getItems().clear();
		availableTermTwo.listView.getItems().addAll(modules);
		availableTermTwo.listView.getSelectionModel().select(0); //select first course by default
	}
	
	public void setReservedTermTwoModules(Collection<Module> modules) {
		selectedTermTwo.listView.getItems().clear();
		selectedTermTwo.listView.getItems().addAll(modules);
		selectedTermTwo.listView.getSelectionModel().select(0); //select first course by default
	}
			
	
	// Add Handlers
	public void setReserveTermOneModuleHandler(EventHandler<ActionEvent> handler) {
		termOneModuleButtonView.addModuleButton.setOnAction(handler);
	}
	
	public void setRemoveReservedTermOneModuleHandler(EventHandler<ActionEvent> handler) {
		termOneModuleButtonView.removeModuleButton.setOnAction(handler);
	}
	
	public void setReserveTermTwoModuleHandler(EventHandler<ActionEvent> handler) {
		termTwoModuleButtonView.addModuleButton.setOnAction(handler);
	}
	
	public void setRemoveReservedTermTwoModuleHandler(EventHandler<ActionEvent> handler) {
		termTwoModuleButtonView.removeModuleButton.setOnAction(handler);
	}
	
	public void setSubmitEventHandler(EventHandler<ActionEvent> handler) {
		submitButton.setOnAction(handler);
	}
	
	public void setResetEventHandler(EventHandler<ActionEvent> handler) {
		resetButton.setOnAction(handler);
	}

}
