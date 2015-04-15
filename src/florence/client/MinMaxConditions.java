package florence.client;

import java.util.ArrayList;

public class MinMaxConditions {
	private ArrayList<Type> listOfMod = new ArrayList<Type>();
	private int plainCount = 0;
	private int dormitoryCount = 0;
	private int sanitationCount = 0;
	private int fwsCount = 0;
	private int grCount = 0;
	private int canteenCount = 0;
	private int powerCount = 0;
	private int controlCount = 0;
	private int airlockCount = 0;
	private int medicalCount = 0;
	
	public void addModuleItem(Type module){
		listOfMod.add(module);
		if(module == Type.AIRLOCK) {
			airlockCount++;
		} else if(module == Type.CONTROL) {
			controlCount++;
		} else if(module == Type.POWER) {
			powerCount++;
		} else if(module == Type.FWS) {
			fwsCount++;
		} else if(module == Type.DORMITORY) {
			dormitoryCount++;
		} else if(module == Type.CANTEEN) {
			canteenCount++;
		} else if(module == Type.SANITATION) {
			sanitationCount++;
		} else if(module == Type.PLAIN) {
			plainCount++;
		} else if(module == Type.GR) {
			grCount++;
		} else { //(module == Type.MEDICAL)
			medicalCount++;
		}
	}
	
	public boolean checkMinCond(){
		if(airlockCount > 0 && controlCount > 0 && powerCount > 0 && fwsCount > 0 && dormitoryCount > 0 && canteenCount > 0 && sanitationCount > 0 && plainCount > 2)
			return true;
		else
			return false;
	}
	
    // TODO update the maximum configuration requirements
	public boolean checkMaxCond(){
		if(airlockCount > 0 && controlCount > 0 && powerCount > 0 && fwsCount > 0 && dormitoryCount > 0 && canteenCount > 0 && sanitationCount > 0 && medicalCount > 0 && grCount > 0 && plainCount > 2)
			return true;
		else
			return false;
	}
	
	
}
