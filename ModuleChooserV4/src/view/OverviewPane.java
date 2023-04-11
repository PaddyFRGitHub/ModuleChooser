package view;

import java.util.Collection;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import model.Module;
import view.Custom.TitledListView;

public class OverviewPane extends GridPane {
	
	private TitledListView<String> profileList = new TitledListView<String>("Profile");
	private TitledListView<Module> selectedModulesList = new TitledListView<Module>("Selected Modules");
	private TitledListView<Module> reservedModulesList = new TitledListView<Module>("Reserved Modules");
	private Button saveOverviewButton = new Button("Save Overview");
	
	
	public OverviewPane() {
		layoutViews();
	}
	
	private void layoutViews() {
		setVgap(5);
		setHgap(5);
		setAlignment(Pos.CENTER);
		setPadding(new Insets(10, 10, 10, 10));
		
		ColumnConstraints col1 = new ColumnConstraints();
	    col1.setHgrow(Priority.ALWAYS);
	    

	    getColumnConstraints().addAll(col1);

	  
	    add(profileList, 0, 0);
		add(selectedModulesList, 0, 1);
		add(reservedModulesList, 0, 2);
		add(saveOverviewButton, 0, 3);
	
		saveOverviewButton.setPrefSize(100, 20);
	}
	
	public void setProfileList(Collection<String> profileItems) {
		profileList.listView.getItems().clear();
		profileList.listView.getItems().addAll(profileItems);
	}
	
	public void setSelectedModulesList(Collection<Module> modules) {
		System.out.println("Set");
		System.out.println(modules);
		selectedModulesList.listView.getItems().clear();
		selectedModulesList.listView.getItems().addAll(modules);
	}
	
	public void setReservedModulesList(Collection<Module> modules) {
		reservedModulesList.listView.getItems().clear();
		reservedModulesList.listView.getItems().addAll(modules);
	}
	
	public void setSubmitEventHandler(EventHandler<ActionEvent> handler) {
		saveOverviewButton.setOnAction(handler);
	}
}

