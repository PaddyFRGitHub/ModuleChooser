package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;


public class ModuleChooserRootPane extends BorderPane {

	// Tabs
	public final CreateStudentProfilePane createProfilePane = new CreateStudentProfilePane();
	public final SelectModulePane selectModulePane = new SelectModulePane();
	public final ReserveModulePane reserveModulePane = new ReserveModulePane();
	public final OverviewPane overviewPane = new OverviewPane();
	
	// Menu Bar
	public final ModuleChooserMenuBar menuBar;
	
	// Tab Pane
	public final TabPane tabPane;
	
	// Constructor 
	public ModuleChooserRootPane() {
		//create tab pane and disable tabs from being closed
		tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		addTabs();
		
		//create menu bar
		menuBar = new ModuleChooserMenuBar();
		
		//add menu bar and tab pane to this root pane
		this.setTop(menuBar);
		this.setCenter(tabPane);
		
	}
	
	// Private helpers
	private void addTabs() {
		tabPane.getTabs().addAll(
			new Tab("Create Profile", createProfilePane),
			new Tab("Select Modules", selectModulePane),
			new Tab("Reserve Modules", reserveModulePane),
			new Tab("Overview", overviewPane)
		);
				
	}
	
	//method to allow the controller to change tabs
	public void changeTab(int index) {
		tabPane.getSelectionModel().select(index);
	}
}
