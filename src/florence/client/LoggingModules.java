package florence.client;

import com.allen_sauer.gwt.voices.client.Sound;
import com.allen_sauer.gwt.voices.client.SoundController;
import com.allen_sauer.gwt.voices.client.SoundType;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
/**
 * Class that sets up module logging UI and checks
 * the input of user.
 */
public class LoggingModules {
	
	private ConfigUI mapDisplay = null;
	
	private HabitatDisplay habitatDisplay = null;
	
	/**
	 * The moduleStore object to save to html5.
	 */
	private Storage moduleStore = null;
	/**
	 * The panel used for scrolling through module log.
	 */
	private ScrollPanel tableScroll = new ScrollPanel();
	/**
	 * The panel used for displaying module log.
	 */
	private VerticalPanel panel = new VerticalPanel();
	/**
	 * The module's ID input field.
	 */
	private TextBox modNum = new TextBox();
	/**
	 * The module's condition selection field.
	 */
	private ListBox modStatus = new ListBox();
	/**
	 * The module's orientation selection field.
	 */
	private ListBox modOrientation = new ListBox();
	/**
	 * The module's x-coordinate input field.
	 */
	private TextBox modXCoord = new TextBox();
	/**
	 * The module's y-coordinate input field.
	 */
	private TextBox modYCoord = new TextBox();
	/**
	 * The module object to be displayed to user.
	 */
	private ModuleLog moduleLog = new ModuleLog();
	/**
	 * The Table displaying logged modules.
	 */
	private FlexTable moduleTable = new FlexTable();
	/**
	 * Field for deleting modules.
	 */
	private TextBox deleteModId = new TextBox();
	/**
	 * The configuration conditions checker.
	 */
	private MinMaxConditions modConfigs = new MinMaxConditions();
	/**
	 * Handles sound.
	 */
	private SoundController soundControl = new SoundController();
	
	/**
	 * Hold the "Module Logged" confirmation message.
	 */
	private Sound confirmation;
	
	
	/**
	 * When triggered logs user's input after checking for errors.
	 */
	private Button logMod = new Button("Log Module", new ClickHandler() {
		public void onClick(ClickEvent event) {
			if(!modNum.getText().isEmpty() && !modXCoord.getText().isEmpty() && !modYCoord.getText().isEmpty()){
				Module newMod = new Module();
				int currentID = Integer.parseInt(modNum.getValue());
				boolean allOkay = true;
				if (!moduleLog.containsModule(currentID)) {
					Module checking = new Module();
					if (checking.validIDcheck(currentID)) {
						newMod.setId(currentID);
						newMod.setStatus(modStatus.getValue(
								modStatus.getSelectedIndex()));
						newMod.setOrientation(Integer.parseInt(
								modOrientation.getValue(
										modOrientation.getSelectedIndex())));
					} else {
						allOkay = false;
					}
					double x = Double.parseDouble(modXCoord.getValue());
					double y = Double.parseDouble(modYCoord.getValue());
					if ((x < 100 && y < 100 && x > 0 && y > 0) && allOkay) {
						newMod.setXCoord(x);
						newMod.setYCoord(y);
					} else {
						allOkay = false;
					}
					if (allOkay) {
						if (confirmation != null) {
	    	  				confirmation.play();
	    	  			} else {
	    	  				System.out.println("Sound is Null");
	    	  			}
						moduleLog.addModule(newMod);
						addTable();
						mapDisplay.updateMap(moduleLog, moduleLog.getSize());
						HabitatConfig habitat1 = new HabitatConfig(moduleLog);
						HabitatConfig habitat2 = new HabitatConfig(moduleLog);
						habitat1.createConfig("min1");
						habitat2.createConfig("min2");
						habitatDisplay.updateHabitat(habitat1);
						
						//TODO Stop modules from being placed on top of each other.
					} else {
						Window.alert("Module not added. Check ID and Coordinates.");
					}
				} else {
					Window.alert("This module has already been logged.");

				}

				//Clears all input data for next input.
				modNum.setValue("");
				modStatus.setSelectedIndex(0);
				modOrientation.setSelectedIndex(0);
				modXCoord.setValue("");
				modYCoord.setValue("");

				//Calls MinMaxConfigs functions here 
				//only if the module logged is not DAMAGED
				if (newMod.getStatus() == Status.UNDAMAGED 
						|| newMod.getStatus() == Status.UNCERTAIN) {

					modConfigs.addModuleItem(newMod.getModType());
					if (modConfigs.checkMinCond()) {
						/*This block will run when the minimum condition is met:
						 * ALERT
						 * Gives user the option to view two minimum habitat
						 * configurations (possibly use HistoryExample Lab) 
						 */
						Window.alert("ALERT :"
								+ " Minimum configuration has been reached.");
					}
					else if (modConfigs.checkMaxCond()) {
						/*This block will run when the maximum condition is met:
						 * ALERT
						 * 
						 */
						Window.alert("ALERT :"
								+ " Maximum configuration has been reached.");

					}
				}
			}
			else{
				Window.alert("Please complete all fields");
			}
		}
	      
	    });
	
		//TODO remove deleted module from map
		/**
		 * This button deletes a module from the table and map.
		 */
		private Button deleteMod = new Button("Delete", new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	  if(!deleteModId.getText().isEmpty() && moduleLog.containsModule(Integer.parseInt(deleteModId.getText()))){
		    		  moduleTable.removeRow(moduleLog.getIndex(Integer.parseInt(deleteModId.getText())));
		    		  mapDisplay.removeFromMap(moduleLog.getModuleFromId(Integer.parseInt(deleteModId.getText())));
		    		  moduleLog.deleteAndRemoveModule(Integer.parseInt(deleteModId.getText()), moduleStore);
		    		  addTable();
		    		  mapDisplay.updateMap(moduleLog, moduleLog.getSize());
		    		  deleteModId.setValue("");
		    	  }
		    	  else{
		    		  Window.alert("Please enter a valid module id");
		    		  deleteModId.setValue("");
		    	  }
		      }
		});
	/**
	 * Constructor that builds the Logging Module Page UI.
	 */
	public LoggingModules() {
		modStatus.addItem("UNDAMAGED");
		modStatus.addItem("DAMAGED");
		modStatus.addItem("UNCERTAIN");
		modOrientation.addItem("0");
		modOrientation.addItem("1");
		modOrientation.addItem("2");
		Label numLabel = new Label("Module ID:");
		Label statusLabel = new Label("Module Status:");
		Label numOrientation = new Label("Module Orientation:");
		Label xCoordLabel = new Label("Module X Coordinate:");
		Label yCoordLabel = new Label("Module Y Coordinate:");
		Label removeModLabel = new Label("Remove Module");
		panel.add(numLabel);
		panel.add(modNum);
		panel.add(statusLabel);
		panel.add(modStatus);
		panel.add(numOrientation);
		panel.add(modOrientation);
		panel.add(xCoordLabel);
		panel.add(modXCoord);
		panel.add(yCoordLabel);
		panel.add(modYCoord);
		panel.add(logMod);
		soundControl.setPreferredSoundTypes(SoundType.WEB_AUDIO, SoundType.FLASH, SoundType.HTML5);
		soundControl.setDefaultVolume(100);
		confirmation = soundControl.createSound(Sound.MIME_TYPE_AUDIO_OGG_SPEEX, "sound/LogSave.ogg");
		addTable();
		panel.add(moduleTable);
		panel.add(removeModLabel);
		panel.add(deleteModId);
		panel.add(deleteMod);
		tableScroll.add(panel);
		
		//Retrieve Data From local storage and add it to the table
		moduleStore = Storage.getLocalStorageIfSupported();
		if (moduleStore != null) {
			for (int i = 0; i < moduleStore.getLength(); i++) {
				String key = moduleStore.key(i);
				String value = moduleStore.getItem(key);
				if(!value.contains("{")){ //CHECKS TO SEE THAT IT IS ONLY ADDING MODULES AND NOT CONFIGURATIONS ****
					Module loadedModule = loadFromLocalStorage(value);
					moduleStore.removeItem(key);
					moduleLog.addModule(loadedModule);
					addTable();
				}
			}
		}	
	}
	/**
	 * Method that gets the UI panel.
	 * @return VerticalPanel the UI panel for logging modules
	 */
	public ScrollPanel LoggingModulesPanel() {
		return tableScroll;
	}
	/**
	 * Method that builds table of logged modules.
	 */
	public void addTable() {
		moduleTable.setBorderWidth(2);
		moduleTable.setText(0, 0, "Module ID");
		moduleTable.setText(0, 1, "Module Type");
		moduleTable.setText(0, 2, "Module Status");
		moduleTable.setText(0, 3, "Module Orientation");
		moduleTable.setText(0, 4, "Module X Coordinate");
		moduleTable.setText(0, 5, "Module Y Coordinate");
		if(moduleLog != null){
		int size = moduleLog.getSize();
		if (moduleLog.getModule(0) != null) {
			for (int index = 0; index < size; index++) {
				Module currentMod = moduleLog.getModule(index);
				moduleTable.setText(index + 1, 0,
						Integer.toString(currentMod.getId()));
				moduleTable.setText(index + 1, 1,
						currentMod.getModType().toString());
				moduleTable.setText(index + 1, 2,
						currentMod.getStatus().toString());
				moduleTable.setText(index + 1, 3,
						Integer.toString(currentMod.getOrientation()));
				moduleTable.setText(index + 1, 4,
						Double.toString(currentMod.getXCoord()));
				moduleTable.setText(index + 1, 5,
						Double.toString(currentMod.getYCoord()));
			}
		}
		}
	}
	
	//Returns an initialized module based on the information from the key-value found in local storage, otherwise returns null
	private Module loadFromLocalStorage(String moduleInfo) {
		Module returnModule = new Module();
		String[] elephantList = moduleInfo.split(",");
		String id = elephantList[0];
		String status = elephantList[1];
		String turns = elephantList[2];
		String x = elephantList[3];
		String y = elephantList[4];
		returnModule.setId(Integer.valueOf(id).intValue());
		returnModule.setOrientation(Integer.valueOf(turns).intValue());
		returnModule.setStatus(status);
		returnModule.setXCoord(Double.parseDouble(x));
		returnModule.setYCoord(Double.valueOf(y));
		return returnModule;
	}
	/**
	 * Method that is used to get moudleLog from LoggingModules class
	 * @return ModuleLog returns the current moduleLog object
	 */
	public ModuleLog getModLog() {
		return moduleLog;
	}
	/**
	 * Method that takes a ConfigUI object to set mapDisplay.
	 * @param map the desired map to be displayed
	 */
	public void attachMap(ConfigUI map) {
		mapDisplay = map;
	}
	
	public void attachHabitat(HabitatDisplay habitat) {
		habitatDisplay = habitat;
		
	}
}
