package florence.client;

import com.google.gwt.user.client.ui.Grid;
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
	private Mapper theMap;
	/**
	 * This Constructs the UI with the given module log.
	 * 
	 * @param inputMods the module log object containing modules for map
	 * @param modCount the number of modules in the inputMods
	 */
	public ConfigUI(ModuleLog inputMods, int modCount) {
		theMap = new Mapper(inputMods, modCount);
		Module[][] extractor = theMap.getMap();
		Module current;		
		for (int x = 0; x < gridSize; x++) {
			for (int y = 0; y < gridSize; y++) {
				current = extractor[x][y];
				if (current != null) {
					try {
						mapGrid.setWidget(x, y, current.imageLocate());
						mapGrid.getCellFormatter().setVisible(x, y ,true);
					} catch (NullPointerException ex) {
						
					}
				}
			}
		}
		mapGrid.setVisible(true);
		
	}
	/**
	 * This method updates the map to match the current
	 * table values.
	 */
	public void updateMap(ModuleLog newLog, int newSize) {
		theMap = new Mapper(newLog, newSize);
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
	/**
	 * Method that creates a UI for the Map in a scroll
	 * panel so that entire map can be viewed.
	 * @return ScrollPanel the panel to be displayed to user
	 */
	public ScrollPanel makeMap() {
		ScrollPanel newPanel = new ScrollPanel();
		mapGrid.setVisible(true);
		newPanel.add(mapGrid);
		newPanel.setVisible(true);
		return newPanel;
	}
	
	public void removeFromMap(Module mod){
		@SuppressWarnings("unused")
		boolean trashValue = theMap.removeModule(mod);
	}
}
