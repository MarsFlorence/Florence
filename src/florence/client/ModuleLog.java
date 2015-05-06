package florence.client;

import java.util.ArrayList;

import com.google.gwt.storage.client.Storage;
/**
 * Class that deals with storing all modules in an array.
 */
public class ModuleLog {
	/**
	 * Constructor that builds new array for module storage.
	 */
	public ModuleLog() {
		moduleLog = new ArrayList<Module>();
	}
	public void clearModules() {
		moduleLog.clear();
	}
	/**
	 * Method that adds module to moduleLog array.
	 * @param mod the module the be added to moduleLog
	 */
	public void addModule(Module mod) {
  		saveToLocalStorage(mod);
		moduleLog.add(mod);
	}
	/**
	 * Method that returns the index corresponding to the module id
	 * @param modId
	 * @return index
	 */
	public int getIndex(int modId){
		int index = -1;
		for(int i = 0; i < moduleLog.size(); i++){
			if(moduleLog.get(i).getId() == modId){
				index = i;
			}
		}
		return index;
	}
	/**
	 * Method that removes module from array at given index.
	 * @param index the location of module to be removed
	 */
	public void removeModule(int index) {
		moduleLog.remove(index);
	}
	
	public void deleteAndRemoveModule(int modId, Storage store){
		removeModule(getIndex(modId));
		deleteFromLocalStorage(modId, store);
	}
	/**
	 * Method used to get Module at given index.
	 * @param index the index of desired module
	 * @return Module the module at index
	 */
	public Module getModule(int index) {
		try {
			return moduleLog.get(index);
		} catch (IndexOutOfBoundsException exp) {
			//TODO Maybe add some way to deal with exception
			return null;
		}
	}
	public Module getModuleFromId(int modId){
		return getModule(getIndex(modId));
	}
	/**
	 * Method used to get current moduleLog.
	 * @return ArrayList<Module> the array of modules
	 */
	public ArrayList<Module> getModuleLog() {
		return moduleLog;
	}
	/**
	 * Method that gets current size of array.
	 * @return int the size of moduleLog
	 */
	public int getSize() {
		return moduleLog.size();
	}/**
	 * Method that checks if given ID is in moduleLog.
	 * @param find the ID user is looking for in array
	 * @return true if ID is in array, false if not in array
	 */
	public boolean containsModule(int id) {
		for (Module mod : moduleLog) {
			if (id == mod.getId()) {
				return true;
			}
		}
		return false;
	}

	private void saveToLocalStorage(Module newMod){
  		moduleStore = Storage.getLocalStorageIfSupported();
  		if (moduleStore != null) {
  			//In the form: ID,Status,Orientation,Xcoord,Ycoord
  			String moduleInfo = newMod.getId() + "," + newMod.getStatus().toString()
  					+ "," + newMod.getOrientation() + "," + newMod.getXCoord() + "," + newMod.getYCoord();
  			moduleStore.setItem(String.valueOf(newMod.getId()), moduleInfo);
  		}			
	}
	
	private void deleteFromLocalStorage(int modId, Storage store){
		store.removeItem(String.valueOf(modId));
	}

	/**
	 * Array where ModuleLog stores its modules.
	 */
	private ArrayList<Module> moduleLog;
	private Storage moduleStore = null;
}
