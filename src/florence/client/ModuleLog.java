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
		moduleLog.add(new Module(0, Status.DAMAGED, 0, 0.0, 0.0));
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
	 * Method that removes module from array at given index.
	 * @param index the location of module to be removed
	 */
	public void removeModule(int index) {
		moduleLog.remove(index);
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
	public boolean containsModule(int find) {
		boolean found = false;
		int counter = 0;
		do {
			try {
				if (this.getModule(counter).getId() == find) {
					found = true;
				}
			} catch (NullPointerException exp) {
				found = false;
				//TODO Add some way to handle exception.
			}
			counter++;
		} while (counter < this.getSize() && found == false);
		return found;
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

	/**
	 * Array where ModuleLog stores its modules.
	 */
	private ArrayList<Module> moduleLog;
	private Storage moduleStore = null;
}
