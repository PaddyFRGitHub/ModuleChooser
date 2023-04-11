package helpers;

import java.util.ArrayList;
import java.util.Collection;

import model.Course;
import model.Module;

public class ModuleSelectionManager {
	
	private Course course;
	
	private Collection<Module> availableFullYearModules = new ArrayList<Module>();
	private Collection<Module> selectedFullYearModules = new ArrayList<Module>();
	private Collection<Module> availableTermOneModules = new ArrayList<Module>();
	private Collection<Module> selectedTermOneModules = new ArrayList<Module>();
	private Collection<Module> availableTermTwoModules = new ArrayList<Module>();
	private Collection<Module> selectedTermTwoModules = new ArrayList<Module>();
	private Collection<Module> reservedTermOneModules = new ArrayList<Module>();
	private Collection<Module> reservedTermTwoModules = new ArrayList<Module>();
	
	// Reserved Module selection
	private int termOneCredits;
	private int termTwoCredits;
	private int termOneReservedCredits;
	private int termTwoReservedCredits;
	
	private int currentTermOneCredits;
	private int currentTermTwoCredits;
	private int currentTermOneReservedCredits;
	private int currentTermTwoReservedCredits;
	
	public ModuleSelectionManager(int termOneCredits, int termTwoCredits, int termOneReserveCredits, int termTwoReserveCredits) {
		this.currentTermOneCredits = termOneCredits;
		this.currentTermTwoCredits = termTwoCredits;
		this.currentTermOneReservedCredits = termOneReserveCredits;
		this.currentTermTwoReservedCredits = termTwoReserveCredits;
		this.termOneCredits = termOneCredits;
		this.termTwoCredits = termTwoCredits;
		this.termOneReservedCredits = termOneReserveCredits;
		this.termTwoReservedCredits = termTwoReserveCredits;
	}
	
	// public functionality
	public boolean addFullYearModule(Module module) {
		// Split the credits between each term for full year modules
		int creditsPerTerm = module.getModuleCredits() / 2;
		
		if (currentTermOneCredits >= creditsPerTerm && currentTermTwoCredits >= creditsPerTerm) {
			boolean removed = availableFullYearModules.remove(module);
			System.out.println(removed);
			selectedFullYearModules.add(module);
			
			currentTermOneCredits -= creditsPerTerm;
			currentTermTwoCredits -= creditsPerTerm;
			
			return true;
		} else {
			return false;
		}
	}
			
	
	public boolean removeFullYearModule(Module module) {
		int creditsPerTerm = module.getModuleCredits() / 2;
		
		availableFullYearModules.add(module);
		selectedFullYearModules.remove(module);
		
		currentTermOneCredits += creditsPerTerm;
		currentTermTwoCredits += creditsPerTerm;
		
		return true;
	}

	public boolean addTermOneModule(Module module) {
		int credits = module.getModuleCredits();
		
		if (currentTermOneCredits >= credits) {
			selectedTermOneModules.add(module);
			availableTermOneModules.remove(module);
			
			currentTermOneCredits -= credits;
			
			return true;
		} else {
			return false;
		}
	}
	
	public boolean removeTermOneModule(Module module) {
		int credits = module.getModuleCredits();
		
		availableTermOneModules.add(module);
		selectedTermOneModules.remove(module);
		
		currentTermOneCredits += credits;
		
		return true;
	}
	
	public boolean addTermTwoModule(Module module) {
		int credits = module.getModuleCredits();
		
		if (currentTermTwoCredits >= credits) {
			selectedTermTwoModules.add(module);
			availableTermTwoModules.remove(module);
			
			currentTermTwoCredits -= credits;
			
			return true;
		} else {
			return false;
		}
	}

	
	public boolean removeTermTwoModule(Module module) {
		int credits = module.getModuleCredits();
		
		availableTermTwoModules.add(module);
		selectedTermTwoModules.remove(module);
		
		currentTermTwoCredits += credits;
		
		return true;
	}
	
	// Reserved Modules
	
	public boolean reserveTermTwoModule(Module module) {
		int credits = module.getModuleCredits();
		
		if (currentTermTwoReservedCredits >= credits) {
			availableTermTwoModules.remove(module);
			reservedTermTwoModules.add(module);
			
			currentTermTwoReservedCredits -= credits;
			
			return true;
		} else {
			return false;
		}
	}
	
	public boolean removeTermTwoReservedModule(Module module) {
		int credits = module.getModuleCredits();
		
		availableTermTwoModules.add(module);
		reservedTermTwoModules.remove(module);
		
		currentTermTwoReservedCredits += credits;
		
		return true;
	}
	

	public boolean reserveTermOneModule(Module module) {
		int credits = module.getModuleCredits();
		
		if (currentTermOneReservedCredits >= credits) {
			availableTermOneModules.remove(module);
			reservedTermOneModules.add(module);
			
			currentTermOneReservedCredits -= credits;
			
			return true;
		} else {
			return false;
		}
	}
	
	public boolean removeTermOneReservedModule(Module module) {
		int credits = module.getModuleCredits();
		
		availableTermOneModules.add(module);
		reservedTermOneModules.remove(module);
		
		currentTermOneReservedCredits += credits;
		
		return true;
	}
	
	
	// Interface to current state of module selections
	public Collection<Module> getAvailableFullYearModules() {
		return availableFullYearModules;
	}
	
	public Collection<Module> getSelectedFullYearModules() {
		return selectedFullYearModules;
	}
	
	public Collection<Module> getAvailableTermOneModules() {
		return availableTermOneModules;
	}
	
	public Collection<Module> getSelectedTermOneModules() {
		return selectedTermOneModules;
	}
	
	public Collection<Module> getAvailableTermTwoModules() {
		return availableTermTwoModules;
	}
	
	public Collection<Module> getSelectedTermTwoModules() {
		return selectedTermTwoModules;
	}
	
	public Collection<Module> getReservedTermOneModules() {
		return reservedTermOneModules;
	}
	
	public Collection<Module> getReservedTermTwoModules() {
		return reservedTermTwoModules;
	}
	
	public int getTermOneCredits() {
		return currentTermOneCredits;
	}
	
	public int getTermTwoCredits() {
		return currentTermTwoCredits;
	}
	
	public void setCourse(Course course) {
		this.course = course;
				
		Collection<Module> modules = course.getAllModulesOnCourse();
		
		// Reset all collections
		availableFullYearModules = new ArrayList<Module>();
		selectedFullYearModules = new ArrayList<Module>();
		availableTermOneModules = new ArrayList<Module>();
		selectedTermOneModules = new ArrayList<Module>();
		availableTermTwoModules = new ArrayList<Module>();
		selectedTermTwoModules = new ArrayList<Module>();
		reservedTermOneModules = new ArrayList<Module>();
		reservedTermTwoModules = new ArrayList<Module>();
		
		currentTermOneCredits = termOneCredits;
		currentTermTwoCredits = termTwoCredits;
		currentTermOneReservedCredits = termOneReservedCredits;
		currentTermTwoReservedCredits = termTwoReservedCredits;
		
		// Build initial available modules for each term
		for (Module module : modules) {
			System.out.println("Adding Module");
			System.out.println(module.getModuleName());
			switch (module.getDelivery()) {
			case TERM_1:
				availableTermOneModules.add(module);
				break;
			case TERM_2:
				availableTermTwoModules.add(module);
				break;
			case YEAR_LONG:
				availableFullYearModules.add(module);
				break;
			default:
				break;
			
			}
		}
	}
	
	public void reset() {
		setCourse(this.course);
	}
}
