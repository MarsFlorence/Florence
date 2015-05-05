package florence.client;

import java.util.ArrayList;

public class HabitatConfig {
	
	private ModuleLog moduleLog;
	private Module[][] habitatConfig;

	public ArrayList<Module> plainModules;
	public ArrayList<Module> dormitoryModules;
	public ArrayList<Module> sanitationModules;
	public ArrayList<Module> controlModules;
	public ArrayList<Module> foodAndWaterModules;
	public ArrayList<Module> gymAndRelaxationModules;
	public ArrayList<Module> canteenModules;
	public ArrayList<Module> powerModules;
	public ArrayList<Module> airlockModules;
	public ArrayList<Module> medicalModules;
	
	
	
	public HabitatConfig(ModuleLog log){
		moduleLog = log;
		habitatConfig = new Module[100][100];
		setTypeArrays();
		
	}
	
	private void setTypeArrays(){
		for(Module mod: moduleLog.getModuleLog()){
			if(mod.getModType() == Type.PLAIN){
				plainModules.add(mod);
			}
			else if(mod.getModType() == Type.AIRLOCK){
				airlockModules.add(mod);
			}
			else if(mod.getModType() == Type.CANTEEN){
				canteenModules.add(mod);
			}
			else if(mod.getModType() == Type.CONTROL){
				controlModules.add(mod);
			}
			else if(mod.getModType() == Type.DORMITORY){
				dormitoryModules.add(mod);
			}
			else if(mod.getModType() == Type.FWS){
				foodAndWaterModules.add(mod);
			}
			else if(mod.getModType() == Type.GR){
				gymAndRelaxationModules.add(mod);
			}
			else if(mod.getModType() == Type.MEDICAL){
				medicalModules.add(mod);
			}
			else if(mod.getModType() == Type.POWER){
				powerModules.add(mod);
			}
			else if(mod.getModType() == Type.SANITATION){
				sanitationModules.add(mod);
			}
		}
	}
	
	private void addModule(Module mod, int x, int y){
		habitatConfig[x][y] = mod;
	}
	
	private boolean isMinimumConfig(){
		if(plainModules.size() < 3){
			return false;
		}
		else if(airlockModules.size() < 1){
			return false;
		}
		else if(controlModules.size() < 1){
			return false;
		}
		else if(powerModules.size() < 1){
			return false;
		}
		else if(foodAndWaterModules.size() < 1){
			return false;
		}
		else if(dormitoryModules.size() < 1){
			return false;
		}
		else if(canteenModules.size() < 1){
			return false;
		}
		else if(sanitationModules.size() < 1){
			return false;
		}
		return true;
	}
	
	private void setMinimimumConfig(){
		addModule(dormitoryModules.get(0), 50, 50);
		dormitoryModules.remove(0);
		addModule(plainModules.get(0), 51, 50);
		plainModules.remove(0);
		addModule(plainModules.get(0), 52, 50);
		plainModules.remove(0);
		addModule(plainModules.get(0), 53, 50);
		plainModules.remove(0);
		addModule(airlockModules.get(0), 54, 50);
		airlockModules.remove(0);
		addModule(sanitationModules.get(0), 51, 49);
		sanitationModules.remove(0);
		addModule(powerModules.get(0), 52, 49);
		powerModules.remove(0);
		addModule(controlModules.get(0), 53, 49);
		controlModules.remove(0);
		addModule(canteenModules.get(0), 52, 51);
		canteenModules.remove(0);
		addModule(foodAndWaterModules.get(0), 53, 51);
		foodAndWaterModules.remove(0);
	}
	
	public Module getModuleAtCoordinates(int x, int y){
		return habitatConfig[x][y];
	}
	
	private int getXCoordinate(Module mod){
		for(int y=0; y < 100; y++){
			for(int x=0; x < 100; x++){
				if(habitatConfig[x][y] == mod){
					return x;
				}
			}
		}
		return -1;
	}
	
	private int getYCoordinate(Module mod){
		for(int y=0; y < 100; y++){
			for(int x=0; x < 100; x++){
				if(habitatConfig[x][y] == mod){
					return y;
				}
			}
		}
		return -1;
	}
	
	public Module[][] getHabitatConfig() {
		return habitatConfig;
	}

	public void setHabitatConfig(Module[][] habitatConfig) {
		this.habitatConfig = habitatConfig;
	}

	public ModuleLog getModuleLog() {
		return moduleLog;
	}

	public void setModuleLog(ModuleLog moduleLog) {
		this.moduleLog = moduleLog;
	}
	
}
