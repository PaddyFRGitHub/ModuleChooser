package model;

import java.io.Serializable;
import java.util.Objects;


public class Module implements Comparable<Module>, Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	private String moduleCode;
	private String moduleName;
	private int moduleCredits;
	private boolean mandatory;
	private Schedule delivery;
	
	public Module(String moduleCode, String moduleName, int moduleCredits, boolean mandatory, Schedule delivery) {
		this.moduleCode = moduleCode;
		this.moduleName = moduleName;
		this.moduleCredits = moduleCredits;
		this.mandatory = mandatory;
		this.delivery = delivery;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public int getModuleCredits() {
		return moduleCredits;
	}
	
	public void setModuleCredits(int moduleCredits) {
		this.moduleCredits = moduleCredits;
	}

	public boolean isMandatory() {
		return mandatory;
	}
	
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
	
	public Schedule getDelivery() {
		return delivery;
	}

	public void setDelivery(Schedule delivery) {
		this.delivery = delivery;
	}
	
	
	@Override
	public String toString() {
		//a non-standard toString that simply returns the module code and name,
		//so as to assist in displaying modules correctly in a ListView<Module> in the view
		//-Note- you may customise this if you wish to do so.
		return moduleCode + " : " + moduleName;
	}
	
	public String actualToString() {
		return "Module:[moduleCode=" + moduleCode + ", moduleName=" + moduleName + 
				", moduleCredits=" + moduleCredits + ", mandatory=" + mandatory + ", delivery=" + delivery + "]";
	}
	
	@Override
	public int compareTo(Module other) {
		int result = this.moduleCode.compareTo(other.moduleCode);
		
		if (result == 0) {
			result = Integer.compare(this.moduleCredits, other.moduleCredits);
			
			if (result == 0) {
				result = Boolean.compare(other.mandatory, this.mandatory);
				
				if (result == 0) {
					result = this.moduleName.compareTo(other.moduleName);
					
					if (result == 0) {
						result = this.delivery.compareTo(other.delivery);
					}
				}
				
			}
		}
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		
		Module other = (Module) obj;
		
		return this.mandatory == other.mandatory && this.moduleCredits == other.moduleCredits &&
				this.moduleCode.equals(other.moduleCode) && this.moduleName.equals(other.moduleName) &&
				this.delivery.equals(other.delivery);
		
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(mandatory, moduleCredits, moduleCode, moduleName, delivery);
	}
	
	
}
