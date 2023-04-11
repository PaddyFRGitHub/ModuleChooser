package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import helpers.ModuleSelectionManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Course;
import model.Schedule;
import model.Module;
import model.StudentProfile;
import view.ModuleChooserRootPane;
import view.OverviewPane;
import view.ReserveModulePane;
import view.SelectModulePane;
import view.CreateStudentProfilePane;
import view.ModuleChooserMenuBar;

public class ModuleChooserController {

	// Student profile model
	private StudentProfile model;
	
	// Tab views. Accessed via the root pane
	private ModuleChooserRootPane view;
	private CreateStudentProfilePane createProfilePane;
	private SelectModulePane selectModulePane;
	private ReserveModulePane reserveModulePane;
	private OverviewPane overviewPane;
	private ModuleChooserMenuBar menuBar;
	
	// Hardcoded course data
	private Course[] courses = generateAndGetCourses();
	
	private ModuleSelectionManager moduleSelectionManager;

	public ModuleChooserController(ModuleChooserRootPane view, StudentProfile model) {
		//initialise view and model fields
		this.view = view;
		this.model = model;
		
		moduleSelectionManager = new ModuleSelectionManager(60, 60, 30, 30);
		
		//initialise view subcontainer fields
		createProfilePane = view.createProfilePane;
		selectModulePane = view.selectModulePane;
		reserveModulePane = view.reserveModulePane;
		overviewPane = view.overviewPane;
		menuBar = view.menuBar;

		attachEventHandlers();	
		setInitialState();
	}

	//helper method - used to attach event handlers
	private void attachEventHandlers() {
		//attach an event handler to the create student profile pane
		createProfilePane.addCreateStudentProfileHandler(new CreateStudentProfileHandler());
		
		// Select module handler
		selectModulePane.setAddAllYearModuleHandler(new AddYearLongModuleEventHandler());
		selectModulePane.setRemoveAllYearModuleHandler(new RemoveYearLongModuleEventHandler());
		selectModulePane.setAddTermOneModuleHandler(new AddTermOneModuleEventHandler());
		selectModulePane.setRemoveTermOneModuleHandler(new RemoveTermOneModuleEventHandler());
		selectModulePane.setAddTermTwoModuleHandler(new AddTermTwoModuleEventHandler());
		selectModulePane.setRemoveTermTwoModuleHandler(new RemoveTermTwoModuleEventHandler());
		selectModulePane.setSubmitEventHandler(new ModuleSelectionSubmitHandler());
		selectModulePane.setResetEventHandler(new ResetHandler());
		 
		// Reserve module handlers 
		reserveModulePane.setReserveTermOneModuleHandler(new ReserveTermOneModuleEventHandler());
		reserveModulePane.setReserveTermTwoModuleHandler(new ReserveTermTwoModuleEventHandler());
		reserveModulePane.setRemoveReservedTermOneModuleHandler(new RemoveReserveTermOneModuleEventHandler());
		reserveModulePane.setRemoveReservedTermTwoModuleHandler(new RemoveReserveTermTwoModuleEventHandler());
		reserveModulePane.setSubmitEventHandler(new ReserveModuleSelectionSubmitHandler());
		reserveModulePane.setResetEventHandler(new ResetHandler());
		
		// Selection Overview
		
		
		//attach an event handler to the menu bar that closes the application
		menuBar.addExitHandler(e -> System.exit(0));
		menuBar.addSaveHandler(new SaveHandler());
		menuBar.addLoadHandler(new LoadHandler());
	}
	
	private void setInitialState() {
		//add courses to combobox in create student profile pane using the generateAndGetCourses helper method below
		createProfilePane.addCoursesToComboBox(courses);
				
		updateModuleSelections();
	}
	
	private void updateModuleSelections() {
		System.out.println("Updating Module Selections");
		
		createProfilePane.setStudentPNumber(model.getStudentPnumber());
		createProfilePane.setStudentEmail(model.getStudentEmail());
		createProfilePane.setStudentName(model.getStudentName());
		createProfilePane.setStudentDate(model.getSubmissionDate());
		
		selectModulePane.setAvailableFullYearModules(moduleSelectionManager.getAvailableFullYearModules());
		selectModulePane.setChosenFullYearModules(moduleSelectionManager.getSelectedFullYearModules());
		selectModulePane.setAvailableTermOneModules(moduleSelectionManager.getAvailableTermOneModules());
		selectModulePane.setChosenTermOneModules(moduleSelectionManager.getSelectedTermOneModules());
		selectModulePane.setAvailableTermTwoModules(moduleSelectionManager.getAvailableTermTwoModules());
		selectModulePane.setChosenTermTwoModules(moduleSelectionManager.getSelectedTermTwoModules());
		selectModulePane.setAvailableTermOneCredits(moduleSelectionManager.getTermOneCredits());
		selectModulePane.setAvailableTermTwoCredits(moduleSelectionManager.getTermTwoCredits());
		
		reserveModulePane.setReservedTermOneModules(moduleSelectionManager.getReservedTermOneModules());
		reserveModulePane.setReservedTermTwoModules(moduleSelectionManager.getReservedTermTwoModules());
		reserveModulePane.setAvailableTermOneModules(moduleSelectionManager.getAvailableTermOneModules());
		reserveModulePane.setAvailableTermTwoModules(moduleSelectionManager.getAvailableTermTwoModules());
		
		
		overviewPane.setSelectedModulesList(new ArrayList<Module>(model.getAllSelectedModules()));
		overviewPane.setReservedModulesList(new ArrayList<Module>(model.getAllReservedModules()));
		
		ArrayList<String> profileList = new ArrayList<String>();
				
		if (model.getStudentPnumber() != null) {
			profileList.add(model.getStudentPnumber());
		}
		
		if (model.getStudentEmail() != null) {
			profileList.add(model.getStudentEmail());
		}
		
		Course course = model.getStudentCourse();
		if (course != null) {
			System.out.println(course.getCourseName());
			profileList.add(course.toString());
		}
		
		if (model.getSubmissionDate() != null) {
			profileList.add(model.getSubmissionDate().toString());
		}
		
		overviewPane.setProfileList(profileList);
		
		
	}
	
	// Event handlers
	
	// Student profile handlers
	private class CreateStudentProfileHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			System.out.print("Creating Profile");
			Course selected = createProfilePane.getSelectedCourse();
			model.setStudentCourse(selected);
			moduleSelectionManager.setCourse(selected);
			
			if (createProfilePane.getStudentName() != null) {
				model.setStudentName(createProfilePane.getStudentName());
			}
			
			if (createProfilePane.getStudentEmail() != null) {
				model.setStudentEmail(createProfilePane.getStudentEmail());
			}
			
			if (createProfilePane.getStudentPnumber() != null) {
				model.setStudentPnumber(createProfilePane.getStudentPnumber());
			}
			
			if (createProfilePane.getStudentDate() != null) {
				model.setSubmissionDate(createProfilePane.getStudentDate());
			}
			
			updateModuleSelections();
			
		}
	}
	
	// Module selection handlers
	private class AddYearLongModuleEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			System.out.println("Add Year Long Module");
			
			Module selected = selectModulePane.getSelectedAvailableFullYearModule();
			moduleSelectionManager.addFullYearModule(selected);
			updateModuleSelections();
		}
	}
	
	private class RemoveYearLongModuleEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			System.out.println("Remove Year Long Module");
			
			Module selected = selectModulePane.getSelectedChosenFullYearModule();
			moduleSelectionManager.removeFullYearModule(selected);
			updateModuleSelections();
		}
	}
	
	private class AddTermOneModuleEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			System.out.println("AddTermOneModuleEventHandler");
			
			Module selected = selectModulePane.getSelectedAvailableTermOneModule();
			System.out.println("");
			System.out.println(selected.getModuleName());
			moduleSelectionManager.addTermOneModule(selected);
			updateModuleSelections();
		}
	}
	
	private class RemoveTermOneModuleEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			System.out.println("RemoveTermOneModuleEventHandler");
			
			Module selected = selectModulePane.getSelectedChosenTermOneModule();
			moduleSelectionManager.removeTermOneModule(selected);
			updateModuleSelections();
		}
	}
	
	private class AddTermTwoModuleEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			System.out.println("AddTermTwoModuleEventHandler");
			
			Module selected = selectModulePane.getSelectedAvailableTermTwoModule();
			moduleSelectionManager.addTermTwoModule(selected);
			updateModuleSelections();
		}
	}
	
	private class RemoveTermTwoModuleEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			System.out.println("RemoveTermTwoModuleEventHandler");
			
			Module selected = selectModulePane.getSelectedChosenTermTwoModule();
			moduleSelectionManager.removeTermTwoModule(selected);
			updateModuleSelections();
		}
	}
	
	
	// Reserve
	
	private class ReserveTermOneModuleEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			
			Module selected = reserveModulePane.getSelectedAvailableTermOneModule();
			moduleSelectionManager.reserveTermOneModule(selected);
			updateModuleSelections();
		}
	}
	
	private class ReserveTermTwoModuleEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			
			Module selected = reserveModulePane.getSelectedAvailableTermTwoModule();
			moduleSelectionManager.reserveTermTwoModule(selected);
			updateModuleSelections();
		}
	}
	
	
	private class RemoveReserveTermOneModuleEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Module selected = reserveModulePane.getSelectedReservedTermOneModule();
			moduleSelectionManager.removeTermOneReservedModule(selected);
			updateModuleSelections();
		}
	}
	
	private class RemoveReserveTermTwoModuleEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Module selected = reserveModulePane.getSelectedReservedTermTwoModule();
			moduleSelectionManager.removeTermTwoReservedModule(selected);
			updateModuleSelections();
		}
	}
	
	private class ModuleSelectionSubmitHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			
			
			System.out.println("Submitted");
			for (Module module : moduleSelectionManager.getSelectedFullYearModules()) {
				model.addSelectedModule(module);
			}
			
			for (Module module : moduleSelectionManager.getSelectedTermOneModules()) {
				model.addSelectedModule(module);
			}
			
			for (Module module : moduleSelectionManager.getSelectedTermTwoModules()) {
				model.addSelectedModule(module);
			}
			
			updateModuleSelections();
			
			System.out.println("Selected Modules");
			System.out.println(model.getAllSelectedModules());
		}
	}
	
	private class ResetHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			moduleSelectionManager.reset();
			updateModuleSelections();
		}
	}
	
	private class ReserveModuleSelectionSubmitHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			
			
			System.out.println("Submitted Reservations");
			for (Module module : moduleSelectionManager.getReservedTermOneModules()) {
				model.addReservedModule(module);
			}
			
			for (Module module : moduleSelectionManager.getReservedTermTwoModules()) {
				model.addReservedModule(module);
			}
			
			
			updateModuleSelections();
			System.out.println("Reserved Modules");
			System.out.println(model.getAllReservedModules());
		}
	}
	
	private class SaveHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			
			FileChooser fileChooser = new FileChooser();

			Stage stage = (Stage)view.getScene().getWindow();
			fileChooser.setInitialFileName("studentProfile");
			File file = fileChooser.showSaveDialog(stage);
			
			try {
				writeToFile(model, file);
			}
			catch(Exception ex) {
				System.out.println("Failed To Save Profile");
				System.out.println(ex.getMessage());
			}
					
			
		}
	}
	
	private class LoadHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			
			FileChooser fileChooser = new FileChooser();

			Stage stage = (Stage)view.getScene().getWindow();
			File file = fileChooser.showOpenDialog(stage);
			
			try {
				StudentProfile loadedProfile = readFromFile(file);
				
				model = loadedProfile;
				System.out.println(model);
				updateModuleSelections();
				
			}
			catch(Exception ex) {
				System.out.println("Failed To Save Profile");
				System.out.println(ex.getMessage());
			}
					
			
		}
	}
	
	public void writeToFile(StudentProfile profile, File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(profile);
            oos.flush();
        }
    }
	
	public StudentProfile readFromFile(File file) throws IOException, ClassNotFoundException {
        StudentProfile result = null;
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = (StudentProfile) ois.readObject();
        }
        
        return result;
    }


	//helper method - generates course and module data and returns courses within an array
	private Course[] generateAndGetCourses() {
		Module imat3423 = new Module("IMAT3423", "Systems Building: Methods", 15, true, Schedule.TERM_1);
		Module ctec3451 = new Module("CTEC3451", "Development Project", 30, true, Schedule.YEAR_LONG);
		Module ctec3902_SoftEng = new Module("CTEC3902", "Rigorous Systems", 15, true, Schedule.TERM_2);	
		Module ctec3902_CompSci = new Module("CTEC3902", "Rigorous Systems", 15, false, Schedule.TERM_2);
		Module ctec3110 = new Module("CTEC3110", "Secure Web Application Development", 15, false, Schedule.TERM_1);
		Module ctec3605 = new Module("CTEC3605", "Multi-service Networks 1", 15, false, Schedule.TERM_1);	
		Module ctec3606 = new Module("CTEC3606", "Multi-service Networks 2", 15, false, Schedule.TERM_2);	
		Module ctec3410 = new Module("CTEC3410", "Web Application Penetration Testing", 15, false, Schedule.TERM_2);
		Module ctec3904 = new Module("CTEC3904", "Functional Software Development", 15, false, Schedule.TERM_2);
		Module ctec3905 = new Module("CTEC3905", "Front-End Web Development", 15, false, Schedule.TERM_2);
		Module ctec3906 = new Module("CTEC3906", "Interaction Design", 15, false, Schedule.TERM_1);
		Module ctec3911 = new Module("CTEC3911", "Mobile Application Development", 15, false, Schedule.TERM_1);
		Module imat3410 = new Module("IMAT3104", "Database Management and Programming", 15, false, Schedule.TERM_2);
		Module imat3406 = new Module("IMAT3406", "Fuzzy Logic and Knowledge Based Systems", 15, false, Schedule.TERM_1);
		Module imat3611 = new Module("IMAT3611", "Computer Ethics and Privacy", 15, false, Schedule.TERM_1);
		Module imat3613 = new Module("IMAT3613", "Data Mining", 15, false, Schedule.TERM_1);
		Module imat3614 = new Module("IMAT3614", "Big Data and Business Models", 15, false, Schedule.TERM_2);
		Module imat3428_CompSci = new Module("IMAT3428", "Information Technology Services Practice", 15, false, Schedule.TERM_2);


		Course compSci = new Course("Computer Science");
		compSci.addModuleToCourse(imat3423);
		compSci.addModuleToCourse(ctec3451);
		compSci.addModuleToCourse(ctec3902_CompSci);
		compSci.addModuleToCourse(ctec3110);
		compSci.addModuleToCourse(ctec3605);
		compSci.addModuleToCourse(ctec3606);
		compSci.addModuleToCourse(ctec3410);
		compSci.addModuleToCourse(ctec3904);
		compSci.addModuleToCourse(ctec3905);
		compSci.addModuleToCourse(ctec3906);
		compSci.addModuleToCourse(ctec3911);
		compSci.addModuleToCourse(imat3410);
		compSci.addModuleToCourse(imat3406);
		compSci.addModuleToCourse(imat3611);
		compSci.addModuleToCourse(imat3613);
		compSci.addModuleToCourse(imat3614);
		compSci.addModuleToCourse(imat3428_CompSci);

		Course softEng = new Course("Software Engineering");
		softEng.addModuleToCourse(imat3423);
		softEng.addModuleToCourse(ctec3451);
		softEng.addModuleToCourse(ctec3902_SoftEng);
		softEng.addModuleToCourse(ctec3110);
		softEng.addModuleToCourse(ctec3605);
		softEng.addModuleToCourse(ctec3606);
		softEng.addModuleToCourse(ctec3410);
		softEng.addModuleToCourse(ctec3904);
		softEng.addModuleToCourse(ctec3905);
		softEng.addModuleToCourse(ctec3906);
		softEng.addModuleToCourse(ctec3911);
		softEng.addModuleToCourse(imat3410);
		softEng.addModuleToCourse(imat3406);
		softEng.addModuleToCourse(imat3611);
		softEng.addModuleToCourse(imat3613);
		softEng.addModuleToCourse(imat3614);

		Course[] courses = new Course[2];
		courses[0] = compSci;
		courses[1] = softEng;

		return courses;
	}

}
