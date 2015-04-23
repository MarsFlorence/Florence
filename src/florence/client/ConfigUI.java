package florence.client;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ScrollPanel;
/**
 * Class that is used for setting up module map UI.
 * 
 */
public class ConfigUI {
	/**
	 * The grid size of map.
	 */
	private final int gridSize = 100;
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
					try {
						mapGrid.setWidget(x, y, current.imageLocate());
					} catch (NullPointerException ex) {
						
					}
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
	
	public void updateMap(ModuleLog newLog, int newSize) {
		Mapper theMap = new Mapper(newLog, newSize);
		Module[][] extractor = theMap.getMap();
		Module current;		
		for (int x = 0; x < gridSize; x++) {
			for (int y = 0; y < gridSize; y++) {
				current = extractor[x][y];
				if (current != null) {
					try {
						mapGrid.setWidget(x, y, current.imageLocate());
					} catch (NullPointerException ex) {
						
					}
				}
			}
		}
	}
	
	public ScrollPanel makeMap() {
		ScrollPanel newPanel = new ScrollPanel();
		mapGrid.setVisible(true);
		newPanel.add(mapGrid);
		newPanel.setVisible(true);
		return newPanel;
	}
}
