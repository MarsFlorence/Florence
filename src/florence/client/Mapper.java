package florence.client;

/**
 * Class that is used for storing module locations on map.
 * 
 */
public class Mapper {
	private int dimensions = 100;
	/**
	 * This variable is used as the map's layout.
	 */
	private Module[][] moduleMap = new Module[dimensions][dimensions];
	/**
	 * This default constructor ensures that all map values
	 * are null.
	 */
	public Mapper() {
		for (int x = 0; x < dimensions; x++) {
			for (int y = 0; y < dimensions; y++) {
				moduleMap[x][y] = null;
			}
		}
	}
	/**
	 * Constructs a new Mapper that places modules into map.
	 * 
	 * @param modules the new list of modules to add to map
	 * @param modCount the number of modules in log
	 */
	public Mapper(ModuleLog modules, int modCount) {
		for (int x = 0; x < dimensions; x++) {
			for (int y = 0; y < dimensions; y++) {
				moduleMap[x][y] = null;
			}
		}
		//TODO Implement module storage with array.
		for(Module mod: modules.getModuleLog()){
			moduleMap[(int) mod.getXCoord()][(int) mod.getYCoord()] = mod;
		}
			
	}
	/**
	 * Adds given module to module map.
	 * 
	 * @param newMod the module the user wishes to add
	 */
	public void addModule(Module newMod) {
		int moduleX = (int) newMod.getXCoord();
		int moduleY = (int) newMod.getYCoord();
		moduleMap[moduleX][moduleY] = newMod;
	}
	/**
	 * Finds given module in module map.
	 * 
	 * @param findMod is the module user wishes to find
	 * @return String of coordinates of module
	 */
	private String findModule(Module findMod) {
		String search = "";
		for (int finderX = 0; finderX < dimensions; finderX++) {
			for (int finderY = 0; finderY < dimensions; finderY++) {
				if (findMod.equals(moduleMap[finderX][finderY])) {
					search = finderX + " " + finderY;
					finderY = dimensions;
					finderX = dimensions;
				}
			}
		}
		return search;
	}
	/**
	 * Removes Module from module map.
	 * 
	 * @param removeMod the module the user wishes to remove
	 * @return true if removed, false if not removed
	 */
	public void removeModule(Module removeMod) {
		int xCoord = (int) removeMod.getXCoord();
		int yCoord = (int) removeMod.getYCoord();
		moduleMap[xCoord][yCoord] = null;
	}
	/**
	 * getMap() returns the current module map.
	 * This class is in the <code>client</code> package because reasons.
	 * @return Module[][] This returns stored moduleMap.
	 */
	public final Module[][] getMap() {
		return moduleMap;
	}
	
	public void clearMap(){
		for(int x = 0; x < dimensions; x++){
			for(int y = 0; y < dimensions; y++){
				moduleMap[x][y] = null;
			}
		}
	}

}
