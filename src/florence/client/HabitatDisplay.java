package florence.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HabitatDisplay {
	
	private final int gridSize = 100;
	private final Grid configGrid = new Grid(gridSize, gridSize);
	private VerticalPanel panel = new VerticalPanel();
	private ListBox menu;

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
						configGrid.setWidget(adjustment, x - 1, current.imageLocate());
						configGrid.getCellFormatter().setVisible(adjustment, x - 1, true);
					} catch (NullPointerException ex) {
						
					}
				}
			}
		}
		

		configGrid.setVisible(true);
		RootPanel.get().add(configGrid);
	}
	
	public void updateHabitat(HabitatConfig config) {
		Module current;		
		for (int x = 0; x < gridSize; x++) {
			for (int y = 0; y < gridSize; y++) {
				current = config.getModuleAtCoordinates(x, y);
				if (current != null) {
					int adjustment = gridSize - y;
					try {
						configGrid.setWidget(adjustment, x - 1, current.imageLocate());
						configGrid.getCellFormatter().setVisible(adjustment, x - 1, true);
					} catch (NullPointerException ex) {
						
					}
				}
			}
		}
	}
	
	public ScrollPanel makeHabitatDisplay() {
		ScrollPanel newPanel = new ScrollPanel();
		HorizontalPanel horzPanel = new HorizontalPanel();
		setDropdown();
		configGrid.setVisible(true);
		horzPanel.add(menu);
		horzPanel.add(loadConfig);
		panel.add(horzPanel);
		panel.add(configGrid);
		panel.setVisible(true);
		newPanel.add(panel);
		newPanel.setVisible(true);
		return newPanel;
	}
	
	private void setDropdown(){
		menu = new ListBox();
		
		menu.addItem("min1");
		menu.addItem("min2");
	}
	
	private Button loadConfig = new Button("Load", new ClickHandler() {
		public void onClick(ClickEvent event) {
			
		}
	});
	
}
