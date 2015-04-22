package florence.client;

import java.util.ArrayList;
/**
 * Class that checks minimum and maximum conditions.
 */
public class MinMaxConditions {
	/**
	 * ArrayList of module types.
	 */
	private ArrayList<Type> listOfMod = new ArrayList<Type>();
	/**
	 * Count for plain modules.
	 */
	private int plainCount = 0;
	/**
	 * Count for  dormitory modules.
	 */
	private int dormitoryCount = 0;
	/**
	 * Count for sanitation modules.
	 */
	private int sanitationCount = 0;
	/**
	 * Count for food and water storage modules.
	 */
	private int fwsCount = 0;
	/**
	 * Count for gym and recreation modules.
	 */
	private int grCount = 0;
	/**
	 * Count for canteen modules.
	 */
	private int canteenCount = 0;
	/**
	 * Count for power modules.
	 */
	private int powerCount = 0;
	/**
	 * Count for control modules.
	 */
	private int controlCount = 0;
	/**
	 * Count for airlock modules.
	 */
	private int airlockCount = 0;
	/**
	 * Count for medical modules.
	 */
	private int medicalCount = 0;
	/**
	 * Method that increments a counter based on given type.
	 * @param module the module type that needs count incremented
	 */
	public void addModuleItem(Type module) {
		listOfMod.add(module);
		if (module == Type.AIRLOCK) {
			airlockCount++;
		} else if (module == Type.CONTROL) {
			controlCount++;
		} else if (module == Type.POWER) {
			powerCount++;
		} else if (module == Type.FWS) {
			fwsCount++;
		} else if (module == Type.DORMITORY) {
			dormitoryCount++;
		} else if (module == Type.CANTEEN) {
			canteenCount++;
		} else if (module == Type.SANITATION) {
			sanitationCount++;
		} else if (module == Type.PLAIN) {
			plainCount++;
		} else if (module == Type.GR) {
			grCount++;
		} else { //(module == Type.MEDICAL)
			medicalCount++;
		}
	}
	/**
	 * Method that checks if minimum conditions are met.
	 * @return true the condition is met, false the condition was not met
	 */
	public boolean checkMinCond() {
		if (airlockCount > 0 && controlCount > 0 
				&& powerCount > 0 && fwsCount > 0 && dormitoryCount > 0 
				&& canteenCount > 0 && sanitationCount > 0 
				&& plainCount > 2) {
			return true;
		} else {
			return false;
		}
	}
	
    // TODO update the maximum configuration requirements
	/**
	 * Method that checks if maximum conditions are met.
	 * @return true the condition is met, false the condition was not met
	 */
	public boolean checkMaxCond() {
		if (airlockCount > 0 && controlCount > 0 && powerCount > 0 
				&& fwsCount > 0 && dormitoryCount > 0 && canteenCount > 0 
				&& sanitationCount > 0 && medicalCount > 0 
				&& grCount > 0 && plainCount > 2) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
