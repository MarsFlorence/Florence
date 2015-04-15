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
		if(module == Type.AIRLOCK)
			airlockCount++;
		if(module == Type.CONTROL)
			controlCount++;
		if(module == Type.POWER)
			powerCount++;
		if(module == Type.FWS)
			fwsCount++;
		if(module == Type.DORMITORY)
			dormitoryCount++;
		if(module == Type.CANTEEN)
			canteenCount++;
		if(module == Type.SANITATION)
			sanitationCount++;
		if(module == Type.PLAIN)
			plainCount++;
		if(module == Type.GR)
			grCount++;
		if(module == Type.MEDICAL)
			medicalCount++;
		
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
