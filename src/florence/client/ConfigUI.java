package florence.client;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Panel;
/**
 * Class that is used for setting up module map UI.
 * 
 */
public class ConfigUI {
	/**
	 * The grid size of map.
	 */
	private final int gridSize = 99;
	/**
	 * Sets up an empty Grid for images to be displayed.
	 */
	private final Grid mapGrid = new Grid(gridSize, gridSize);
	/**
	 * This Constructs the UI with the given module log.
	 * 
	 * @param inputMods the module log object containing modules for map
	 * @param modCount the number of modules in the inputMods
	 */
	public ConfigUI(ModuleLog inputMods, int modCount) {
		Mapper theMap = new Mapper(inputMods, modCount);
		Module[][] extractor = theMap.getMap();
		Module current;		
		for (int x = 0; x < gridSize; x++) {
			for (int y = 0; y < gridSize; y++) {
				current = extractor[x][y];
				if (current != null) {
					Panel newPicture = null;
					newPicture.add(current.imageLocate());
					mapGrid.setWidget(x, y, newPicture);
				}
			}
		}
		mapGrid.setVisible(true);
	}
	/**
	 * This method returns the Map grid.
	 * @return returns the mapGrid
	 */
	public final Grid makeConfig() {
		return mapGrid;
	}
}
