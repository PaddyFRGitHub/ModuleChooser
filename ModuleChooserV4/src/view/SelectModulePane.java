package view;

import model.Module;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.util.Collection;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.Custom.AddRemoveModuleView;
import view.Custom.AvailableCreditsView;
import view.Custom.TitledListView;


public class SelectModulePane extends GridPane {
	
	private TitledListView<Module> availableYearLong = new TitledListView<Module>("Available - All Year");
	private TitledListView<Module> selectedYearLong = new TitledListView<Module>("Selected - All Year");
	private TitledListView<Module> availableTermOne = new TitledListView<Module>("Available - Term One");
	private TitledListView<Module> selectedTermOne = new TitledListView<Module>("Selected - Term One");
	private TitledListView<Module> availableTermTwo = new TitledListView<Module>("Available - Term Two");
	private TitledListView<Module> selectedTermTwo = new TitledListView<Module>("Selected - Term Two");
	
	private AddRemoveModuleView allYearModuleButtonView = new AddRemoveModuleView("All Year");
	private AddRemoveModuleView termOneModuleButtonView = new AddRemoveModuleView("Term 1");
	private AddRemoveModuleView termTwoModuleButtonView = new AddRemoveModuleView("Term 2");
	
	private AvailableCreditsView availableCreditsView = new AvailableCreditsView();
	
	private Button resetButton = new Button("Reset");
	private Button submitButton = new Button("Submit");
	
	public SelectModulePane() {
		layoutViews();
	}
	
	private void layoutViews() {
		setVgap(20);
		setHgap(20);
		setAlignment(Pos.CENTER);
		setPadding(new Insets(10, 10, 10, 10));
		
		ColumnConstraints col1 = new ColumnConstraints();
	    col1.setHgrow(Priority.ALWAYS);
	    
	    ColumnConstraints col2 = new ColumnConstraints();
	    col2.setHgrow(Priority.ALWAYS);

	    getColumnConstraints().addAll(col1, col2);
		
		add(availableYearLong, 0, 0);
		add(allYearModuleButtonView, 0, 1);
		add(availableTermOne, 0, 2);
		add(termOneModuleButtonView, 0, 3);
		add(availableTermTwo, 0, 4);
		add(termTwoModuleButtonView, 0, 5);
		
		add(selectedYearLong, 1, 0);
		add(selectedTermOne, 1, 2);
		add(selectedTermTwo, 1, 4);
		
		add(availableCreditsView, 0, 6);
		
		HBox buttons = new HBox();
		buttons.setSpacing(5);
		buttons.setAlignment(Pos.BASELINE_RIGHT);
	    buttons.getChildren().addAll(resetButton, submitButton);
		add(buttons, 1, 7);
	}
	
	// Get current selections from lists
	public Module getSelectedAvailableFullYearModule() {
		return availableYearLong.listView.getSelectionModel().getSelectedItem();
	}
	
	public Module getSelectedChosenFullYearModule() {
		return selectedYearLong.listView.getSelectionModel().getSelectedItem();
	}
	
	public Module getSelectedAvailableTermOneModule() {
		return availableTermOne.listView.getSelectionModel().getSelectedItem();
	}
	
	public Module getSelectedChosenTermOneModule() {
		return selectedTermOne.listView.getSelectionModel().getSelectedItem();
	}
	
	public Module getSelectedAvailableTermTwoModule() {
		return availableTermTwo.listView.getSelectionModel().getSelectedItem();
	}
	
	public Module getSelectedChosenTermTwoModule() {
		return selectedTermTwo.listView.getSelectionModel().getSelectedItem();
	}
	
	// Update Lists
	public void setAvailableFullYearModules(Collection<Module> modules) {
		availableYearLong.listView.getItems().clear();
		availableYearLong.listView.getItems().addAll(modules);
		availableYearLong.listView.getSelectionModel().select(0); //select first course by default
	}
	
	public void setChosenFullYearModules(Collection<Module> modules) {
		selectedYearLong.listView.getItems().clear();
		selectedYearLong.listView.getItems().addAll(modules);
		selectedYearLong.listView.getSelectionModel().select(0); //select first course by default
	}
	
	public void setAvailableTermOneModules(Collection<Module> modules) {
		availableTermOne.listView.getItems().clear();
		availableTermOne.listView.getItems().addAll(modules);
		availableTermOne.listView.getSelectionModel().select(0); //select first course by default
	}
	
	public void setChosenTermOneModules(Collection<Module> modules) {
		selectedTermOne.listView.getItems().clear();
		selectedTermOne.listView.getItems().addAll(modules);
		selectedTermOne.listView.getSelectionModel().select(0); //select first course by default
	}
	
	public void setAvailableTermTwoModules(Collection<Module> modules) {
		availableTermTwo.listView.getItems().clear();
		availableTermTwo.listView.getItems().addAll(modules);
		availableTermTwo.listView.getSelectionModel().select(0); //select first course by default
	}
	
	public void setChosenTermTwoModules(Collection<Module> modules) {
		selectedTermTwo.listView.getItems().clear();
		selectedTermTwo.listView.getItems().addAll(modules);
		selectedTermTwo.listView.getSelectionModel().select(0); //select first course by default
	}
	
	// Update available credits
	public void setAvailableTermOneCredits(int credits) {
		availableCreditsView.termOneAvailableCreditsLabel.setText(Integer.toString(credits));
	}
	
	public void setAvailableTermTwoCredits(int credits) {
		availableCreditsView.termTwoAvailableCreditsLabel.setText(Integer.toString(credits));
	}
	
	
	// Add Handlers
	public void setAddAllYearModuleHandler(EventHandler<ActionEvent> handler) {
		allYearModuleButtonView.addModuleButton.setOnAction(handler);
	}
	
	public void setRemoveAllYearModuleHandler(EventHandler<ActionEvent> handler) {
		System.out.println("Add Remove All Year Event Handler");
		allYearModuleButtonView.removeModuleButton.setOnAction(handler);
	}
	
	public void setAddTermOneModuleHandler(EventHandler<ActionEvent> handler) {
		termOneModuleButtonView.addModuleButton.setOnAction(handler);
	}
	
	public void setRemoveTermOneModuleHandler(EventHandler<ActionEvent> handler) {
		termOneModuleButtonView.removeModuleButton.setOnAction(handler);
	}
	
	public void setAddTermTwoModuleHandler(EventHandler<ActionEvent> handler) {
		termTwoModuleButtonView.addModuleButton.setOnAction(handler);
	}
	
	public void setRemoveTermTwoModuleHandler(EventHandler<ActionEvent> handler) {
		termTwoModuleButtonView.removeModuleButton.setOnAction(handler);
	}
	
	public void setSubmitEventHandler(EventHandler<ActionEvent> handler) {
		submitButton.setOnAction(handler);
	}
	
	public void setResetEventHandler(EventHandler<ActionEvent> handler) {
		resetButton.setOnAction(handler);
	}
	
	
	
	
	
	
	
}
