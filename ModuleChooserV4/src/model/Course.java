package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class Course implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	private String courseName;
	private Map<String, Module> modulesOnCourse;
	
	public Course(String courseName) {
		this.courseName = courseName;
		modulesOnCourse = new HashMap<String, Module>();
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public void addModuleToCourse(Module m) {
		modulesOnCourse.put(m.getModuleCode(), m);
	}
	
	public Module getModuleByCode(String code) {
		return modulesOnCourse.get(code);
	}
	
	public Collection<Module> getAllModulesOnCourse() {
		return modulesOnCourse.values();
	}
	
	@Override
	public String toString() {
		//a non-standard toString that simply returns the course name,
		//so as to assist in displaying courses correctly in a ComboBox<Course>
		return courseName;
	}
	
	public String actualToString() {
		return "Course:[courseName=" + courseName + ", modulesOnCourse=" + modulesOnCourse + "]";
	}
	
}
