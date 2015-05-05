package florence.client;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

public class HabitatDisplay {
	
	private final int gridSize = 100;
	private final Grid configGrid = new Grid(gridSize, gridSize);

	public HabitatDisplay(HabitatConfig config){
		
		configGrid.addStyleName("mapGrid");
		
		for (int i = 0; i < configGrid.getRowCount(); i++) {
            for (int j = 0; j < configGrid.getCellCount(i); j++) {
                   configGrid.getCellFormatter().setStyleName(i, j, "tableCell-all");
            }
        }
		
		Module current;
		
		for (int x = 0; x < gridSize; x++) {
			for (int y = 0; y < gridSize; y++) {
				current = config.getModuleAtCoordinates(x, y);
				if (current != null) {
					int adjustment = gridSize - y;
					try {
						configGrid.setWidget(adjustment, x, current.imageLocate());
						configGrid.getCellFormatter().setVisible(adjustment, x, true);
					} catch (NullPointerException ex) {
						
					}
				}
			}
		}
		

		configGrid.setVisible(true);
		RootPanel.get().add(configGrid);
	}
	
	public ScrollPanel makeConfig() {
		ScrollPanel newPanel = new ScrollPanel();
		configGrid.setVisible(true);
		newPanel.add(configGrid);
		newPanel.setVisible(true);
		return newPanel;
	}
	
}