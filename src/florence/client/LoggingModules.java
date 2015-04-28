package florence.client;

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
	
	/**
	 * The moduleStore object to save to html5
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
	 * Field for deleting modules
	 */
	private TextBox deleteModId = new TextBox();
	/**
	 * The configuration conditions checker.
	 */
	private MinMaxConditions modConfigs = new MinMaxConditions();
	/**
	 * When triggered logs user's input after checking for errors.
	 */
	private Button logMod = new Button("Log Module", new ClickHandler() {
	      public void onClick(ClickEvent event) {
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
	    	  	if ((x < 99 && y < 99 && x > 0 && y > 0) && allOkay) {
	    	  		newMod.setXCoord(x);
	    	  		newMod.setYCoord(y);
	    	  	} else {
	    	  		allOkay = false;
	    	  	}
	    	  	if (allOkay) {
	    	  		moduleLog.addModule(newMod);
	    	  		addTable();
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
	      
	      
	    });
	
		private Button deleteMod = new Button("Delete", new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	  moduleTable.removeRow(moduleLog.getIndex(Integer.parseInt(deleteModId.getText())));
		    	  moduleLog.deleteAndRemoveModule(Integer.parseInt(deleteModId.getText()), moduleStore);
		    	  addTable();
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
				Module loadedModule = loadFromLocalStorage(value);
    	  		moduleStore.removeItem(key);
    	  		moduleLog.addModule(loadedModule);
    	  		addTable();
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
		//panel.add(moduleTable);
	}
	
	//Returns an initialized module based on the information from the key-value found in local storage, otherwise returns null
	private Module loadFromLocalStorage(String moduleInfo){
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
	
	public ModuleLog getModLog(){
		return moduleLog;
	}
}
