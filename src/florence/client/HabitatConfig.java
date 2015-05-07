package florence.client;

import java.util.ArrayList;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.storage.client.Storage;

public class HabitatConfig {
	
	private ModuleLog moduleLog;
	private Module[][] habitatConfig;

	private ArrayList<Module> plainModules;
	private ArrayList<Module> dormitoryModules;
	private ArrayList<Module> sanitationModules;
	private ArrayList<Module> controlModules;
	private ArrayList<Module> foodAndWaterModules;
	private ArrayList<Module> gymAndRelaxationModules;
	private ArrayList<Module> canteenModules;
	private ArrayList<Module> powerModules;
	private ArrayList<Module> airlockModules;
	private ArrayList<Module> medicalModules;
	
	private int centerX;
	private int centerY;
	
	private String habitatKey;
	private double qualityPercent;
	
	public double getQualityPercent() {
		return qualityPercent;
	}
	public void setQualityPercent(double qualityPercent) {
		this.qualityPercent = qualityPercent;
	}
	public HabitatConfig(){
		habitatConfig = new Module[100][100];	
	}
	public HabitatConfig(ModuleLog log){
		moduleLog = log;
		habitatConfig = new Module[100][100];
		//setTypeArrays();
		
	}
	
	public void createConfig(String name){
		setHabitatKey(name);
		setTypeArrays();
		if(isMinimumConfig()){
			if(name.equals("min1")){
				setMinimumConfig1();
			}
			else if(name.equals("min2")){
				setMinimumConfig2();
			}
			else{
				calculateConfig(name);
			}
		}
	}
	
	public void setTypeArrays(){
		plainModules = new ArrayList<Module>();
		dormitoryModules = new ArrayList<Module>();
		sanitationModules = new ArrayList<Module>();
		controlModules = new ArrayList<Module>();
		foodAndWaterModules = new ArrayList<Module>();
		gymAndRelaxationModules = new ArrayList<Module>();
		canteenModules = new ArrayList<Module>();
		powerModules = new ArrayList<Module>();
		airlockModules = new ArrayList<Module>();
		medicalModules = new ArrayList<Module>();
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
	
	private void setMinimumConfig1(){
		int x = 20;
		int y = 20;
		if(centerX != 0){
			x = centerX;
		}
		if(centerX != 0){
			y = centerY;
		}
		addModule(dormitoryModules.get(0), x - 2, y);
		dormitoryModules.remove(0);
		addModule(plainModules.get(0), x - 1, y);
		plainModules.remove(0);
		addModule(plainModules.get(0), x, y);
		plainModules.remove(0);
		addModule(plainModules.get(0), x + 1, y);
		plainModules.remove(0);
		addModule(airlockModules.get(0), x + 2, y);
		airlockModules.remove(0);
		addModule(sanitationModules.get(0), x - 1, y - 1);
		sanitationModules.remove(0);
		addModule(powerModules.get(0), x, y - 1);
		powerModules.remove(0);
		addModule(controlModules.get(0), x + 1, y - 1);
		controlModules.remove(0);
		addModule(canteenModules.get(0), x, y + 1);
		canteenModules.remove(0);
		addModule(foodAndWaterModules.get(0), x + 1, y + 1);
		foodAndWaterModules.remove(0);
		
		getQuality();
		saveConfiguration("min1");
	}
	
	private void setMinimumConfig2(){
		int x = 20;
		int y = 20;
		if(centerX != 0){
			x = centerX;
		}
		if(centerX != 0){
			y = centerY;
		}
		addModule(dormitoryModules.get(0), x, y + 2);
		dormitoryModules.remove(0);
		addModule(plainModules.get(0), x, y + 1);
		plainModules.remove(0);
		addModule(plainModules.get(0), x, y);
		plainModules.remove(0);
		addModule(plainModules.get(0), x + 1, y);
		plainModules.remove(0);
		addModule(airlockModules.get(0), x + 2, y);
		airlockModules.remove(0);
		addModule(sanitationModules.get(0), x - 1, y + 1);
		sanitationModules.remove(0);
		addModule(powerModules.get(0), x - 1, y);
		powerModules.remove(0);
		addModule(controlModules.get(0), x + 1, y + 1);
		controlModules.remove(0);
		addModule(canteenModules.get(0), x, y - 1);
		canteenModules.remove(0);
		addModule(foodAndWaterModules.get(0), x + 1, y - 1);
		foodAndWaterModules.remove(0);
		
		getQuality();
		saveConfiguration("min2");
	}
	
	public Module getModuleAtCoordinates(int x, int y){
		return habitatConfig[x][y];
	}
	
	public int getXCoordinate(Module mod){
		for(int y=0; y < 100; y++){
			for(int x=0; x < 100; x++){
				if(habitatConfig[x][y] == mod){
					return x;
				}
			}
		}
		return -1;
	}
	
	public int getYCoordinate(Module mod){
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
	
	private void calculateConfig(String key){
		int plainNumber = plainModules.size();
		int dormNumber = dormitoryModules.size();
		int sanitationNumber = sanitationModules.size();
		int controlNumber = controlModules.size();
		int foodNumber = foodAndWaterModules.size();
		int gymNumber = gymAndRelaxationModules.size();
		int canteenNumber = canteenModules.size();
		int powerNumber = powerModules.size();
		int airlockNumber = airlockModules.size();
		int medicalNumber = medicalModules.size();
		
		int dormGroup = 0; // 2 dormitory, 1 sanitation, 2 plain
		int dormGymGroup = 0; // 2 dormitory, 1 sanitation, 2 plain, 1 gym
		int powerControlGroup = 0; // 1 power, 1 control, 1 plain
		int foodBasicGroup = 0; // 1 canteen, 1 food, 1 plain
		int foodLargeGroup = 0; // 1 canteen, 3 food, 2 plain
		int airlockMedGroup = 0; // 1 airlock, 1 medical, 1 plain
		int cornerGroup = 0; // 3 plain
		
		if(plainNumber >= 3){
			plainNumber = plainNumber - 3;
			cornerGroup++;
		}
		
		while(dormNumber >= 2 && sanitationNumber >= 1 && plainNumber >= 2){
			dormNumber = dormNumber - 2;
			sanitationNumber--;
			plainNumber = plainNumber - 2;
			if(gymNumber >= 1){
				gymNumber--;
				dormGymGroup++;
			}
			else{
				dormGroup++;
			}
		}
		
		while(powerNumber >= 1 && controlNumber >= 1 && plainNumber >= 1){
			powerNumber--;
			controlNumber--;
			plainNumber--;
			powerControlGroup++;
		}
		
		while(canteenNumber >= 1 && foodNumber >= 1 && plainNumber >= 1){
			canteenNumber--;
			if(foodNumber >= 3 && plainNumber >= 2){
				foodNumber = foodNumber - 3;
				plainNumber = plainNumber - 2;
				foodLargeGroup++;
			}
			else{
				foodNumber--;
				plainNumber--;
				foodBasicGroup++;
			}
		}
		
		while(airlockNumber >= 1 && medicalNumber >= 1 && plainNumber >= 1){
			airlockNumber--;
			medicalNumber--;
			plainNumber--;
			airlockMedGroup++;
		}
		
		int centerX = getCenterX();
		int centerY = getCenterY();
		
		int x = centerX;
		int y = centerY;
		
		if(cornerGroup >= 1){
			addModule(plainModules.get(0), x , y);
			plainModules.remove(0);
			addModule(plainModules.get(0), x + 1, y);
			plainModules.remove(0);
			addModule(plainModules.get(0), x , y + 1);
			plainModules.remove(0);
			cornerGroup--;
			x = x + 2;
		}
		while(dormGymGroup >= 1 || dormGroup >= 1){
			addModule(plainModules.get(0), x , y);
			plainModules.remove(0);
			addModule(plainModules.get(0), x + 1, y);
			plainModules.remove(0);
			addModule(dormitoryModules.get(0), x , y - 1);
			dormitoryModules.remove(0);
			addModule(dormitoryModules.get(0), x + 1 , y - 1);
			dormitoryModules.remove(0);
			addModule(sanitationModules.get(0), x + 1, y + 1);
			sanitationModules.remove(0);
			if(dormGymGroup >= 1){
				addModule(gymAndRelaxationModules.get(0), x , y + 1);
				gymAndRelaxationModules.remove(0);
				dormGymGroup--;
			}
			else{
				dormGroup--;
			}
			x = x + 2;
		}
		
		int halfPowerControl = powerControlGroup / 2;
		int remainderPowerControl = powerControlGroup - halfPowerControl;
		
		while(halfPowerControl >= 1){
			addModule(plainModules.get(0), x , y);
			plainModules.remove(0);
			addModule(controlModules.get(0), x , y - 1);
			controlModules.remove(0);
			addModule(powerModules.get(0), x , y + 1);
			powerModules.remove(0);
			powerControlGroup--;
			halfPowerControl--;
			x++;
		}
		if(airlockMedGroup >= 1){
			addModule(plainModules.get(0), x , y);
			plainModules.remove(0);
			addModule(medicalModules.get(0), x , y - 1);
			medicalModules.remove(0);
			addModule(airlockModules.get(0), x + 1 , y);
			airlockModules.remove(0);
			airlockMedGroup--;
			x = x + 2;
		}
		
		x = centerX;
		y = y + 2;
		
		while(foodBasicGroup >= 1 || foodLargeGroup >= 1){
			addModule(plainModules.get(0), x , y);
			plainModules.remove(0);
			addModule(canteenModules.get(0), x + 1 , y);
			canteenModules.remove(0);
			addModule(foodAndWaterModules.get(0), x - 1 , y);
			foodAndWaterModules.remove(0);
			if(foodLargeGroup >= 1){
				addModule(plainModules.get(0), x , y + 1);
				plainModules.remove(0);
				addModule(foodAndWaterModules.get(0), x + 1 , y + 1);
				foodAndWaterModules.remove(0);
				addModule(foodAndWaterModules.get(0), x - 1 , y + 1);
				foodAndWaterModules.remove(0);
				foodLargeGroup--;
				y = y + 2;
			}
			else{
				foodBasicGroup--;
				y++;
			}
		}
		
		while(remainderPowerControl >= 1){
			addModule(plainModules.get(0), x , y);
			plainModules.remove(0);
			addModule(controlModules.get(0), x + 1 , y);
			controlModules.remove(0);
			addModule(powerModules.get(0), x - 1 , y);
			powerModules.remove(0);
			powerControlGroup--;
			remainderPowerControl--;
			y++;
		}
		
		if(airlockMedGroup >= 1){
			addModule(plainModules.get(0), x , y);
			plainModules.remove(0);
			addModule(medicalModules.get(0), x + 1 , y);
			medicalModules.remove(0);
			addModule(airlockModules.get(0), x , y + 1);
			airlockModules.remove(0);
			airlockMedGroup--;
			x = x + 2;
		}
		
		getQuality();
		
		saveConfiguration(key);
		
	}
	/**
	 * Saves this habitat configuration onto HTML5 local storage.
	 * @param name The name of this configuration (ex: "min1").
	 */
	public void saveConfiguration(final String name) {		
		String key = name;
		String QUOTE = "\""; 
  		Storage moduleStore = Storage.getLocalStorageIfSupported();	
  		if (moduleStore != null) {
  			String moduleListInfo = "[";  		
  			for(int y=0; y < 100; y++){
  				for(int x=0; x < 100; x++){
  					if(habitatConfig[x][y] != null){
  						Module mod = habitatConfig[x][y];
  						//In the form: {id:"id",status:"status",ori:"ori",xcoord:"xcoord",ycoord:"ycoord",xpos:"xpos",ypos:"ypos"},
  						moduleListInfo = moduleListInfo + "{id:" + mod.getId() + ",status:" + QUOTE + mod.getStatus().toString() + QUOTE
  							+ ",ori:" + mod.getOrientation() + ",xoord:" + mod.getXCoord() + ",ycoord:" + mod.getYCoord() + ",xpos:" + x + ",ypos:" + y + "},";	
  					}
  				}
  			}
		    // Removes the final ',' placed by the loops.
		    moduleListInfo = moduleListInfo.substring(0, moduleListInfo.length() - 1);
		    moduleListInfo = moduleListInfo + "]";
			moduleStore.setItem(key, moduleListInfo);
  		}	
	}
	/**
	 * Loads a configuration from local storage into this habitat configuration.
	 * 
	 * @param name The name of the configuration to load.
	 * @return true if the configuration was loaded, else false
	 */
	public HabitatConfig loadConfiguration(final String name) {
		Storage store = null;
		store = Storage.getLocalStorageIfSupported();
		
		String key = name;
		String value = store.getItem(key);
		
		// If the configuration was not found in storage.
		if (value != null) {
			HabitatConfig habitatConfig = new HabitatConfig();
			JSONArray array = (JSONArray) JSONParser.parseLenient(value);
			JSONNumber number;
			JSONString string;
			double id;
			String status;
			double turns;
			double x;
			double y;
			int xPos;
			int yPos;
			//loop through jsonarray
			for(int i = 0; i < array.size(); i++){
				JSONObject object = (JSONObject) array.get(i);
				number = (JSONNumber) object.get("id");
				id = number.doubleValue();		
				
				string = (JSONString) object.get("status");
				status = string.stringValue();
				
				number = (JSONNumber) object.get("ori");
				turns = number.doubleValue();			
				
				number = (JSONNumber) object.get("xoord");
				x = number.doubleValue();	

				number = (JSONNumber) object.get("ycoord");
				y = number.doubleValue();	

				number = (JSONNumber) object.get("xpos");
				xPos = (int) number.doubleValue();	

				number = (JSONNumber) object.get("ypos");
				yPos = (int) number.doubleValue();
				
				Module newModule = new Module();
				newModule.setId((int) id);
				newModule.setStatus(status);
				newModule.setOrientation((int) turns);
				newModule.setXCoord(x);
				newModule.setYCoord(y);
				
				habitatConfig.getHabitatConfig()[xPos][yPos] = newModule;
				
			}
			habitatConfig.setHabitatKey(name);
			return habitatConfig;
		}
		return null;
	}
	private void getQuality(){
		int modulesLeft = 0; 
		modulesLeft += plainModules.size();
		modulesLeft += dormitoryModules.size();
		modulesLeft += sanitationModules.size();
		modulesLeft += controlModules.size();
		modulesLeft += foodAndWaterModules.size();
		modulesLeft += gymAndRelaxationModules.size();
		modulesLeft += canteenModules.size();
		modulesLeft += powerModules.size();
		modulesLeft += airlockModules.size();
		modulesLeft += medicalModules.size();
		int totalMods = moduleLog.getSize();
		int usedMods = totalMods - modulesLeft;
		double quality = (usedMods * 100) / totalMods;
		qualityPercent = quality;
	}
	public int getCenterX() {
		return centerX;
	}
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}
	public int getCenterY() {
		return centerY;
	}
	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}
	public String getHabitatKey() {
		return habitatKey;
	}
	public void setHabitatKey(String habitatKey) {
		this.habitatKey = habitatKey;
	}
}
