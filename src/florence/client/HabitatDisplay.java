package florence.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HabitatDisplay {
	
	private final int gridSize = 100;
	private final Grid configGrid = new Grid(gridSize, gridSize);
	private VerticalPanel panel = new VerticalPanel();
	private ListBox menu= new ListBox();
	
	private Label quality = new Label();
	private TextBox XCoord = new TextBox();
	private TextBox YCoord = new TextBox();
	
	private TextBox configName = new TextBox();
	
	private HabitatConfig habitatConfig;

	public HabitatDisplay(HabitatConfig config){
		
		habitatConfig = config;
		
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
		habitatConfig = config;
		
		Module current;		
		for (int x = 0; x < gridSize - 1; x++) {
			for (int y = 0; y < gridSize - 1; y++) {
				configGrid.setWidget(x, y, null);
			}
		}
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
		quality.setText("Quality: " + Double.toString(habitatConfig.getQualityPercent()));
		setDropdown();
	}
	
	public ScrollPanel makeHabitatDisplay() {
		ScrollPanel newPanel = new ScrollPanel();
		HorizontalPanel horzPanel = new HorizontalPanel();
		setDropdown();
		configGrid.setVisible(true);
		Label xLabel = new Label("New X Coord");
		Label yLabel = new Label("New Y Coord");
		Label configLabel = new Label("Config Name: ");
		horzPanel.add(menu);
		horzPanel.add(loadConfig);
		horzPanel.add(xLabel);
		XCoord.setWidth("40px");
		horzPanel.add(XCoord);
		horzPanel.add(yLabel);
		YCoord.setWidth("40px");
		horzPanel.add(YCoord);
		horzPanel.setSpacing(10);
		horzPanel.add(changeCenter);
		horzPanel.add(configLabel);
		configName.setWidth("80px");
		horzPanel.add(configName);
		horzPanel.add(calculateConfig);
		quality.setText("Quality: " + Double.toString(habitatConfig.getQualityPercent()));
		horzPanel.add(quality);
		panel.add(horzPanel);
		panel.add(configGrid);
		panel.setVisible(true);
		newPanel.add(panel);
		newPanel.setVisible(true);
		return newPanel;
	}
	
	public void setDropdown(){
		menu.clear();
		
		Storage moduleStore = Storage.getLocalStorageIfSupported();
		if (moduleStore != null) {
			for (int i = 0; i < moduleStore.getLength(); i++) {
				String key = moduleStore.key(i);
				String value = moduleStore.getItem(key);
				if(value.contains("{")){
					menu.addItem(key);
				} 
			}
		}
	}

	private Button loadConfig = new Button("Load", new ClickHandler() {
		public void onClick(ClickEvent event) {
			String key = menu.getValue(menu.getSelectedIndex());
			
			HabitatConfig habitat = new HabitatConfig();
			habitat = habitat.loadConfiguration(key);
			habitat.setModuleLog(habitatConfig.getModuleLog());
			
			habitatConfig = habitat;
			quality.setText("Quality: " + Double.toString(habitat.getQualityPercent()));
			updateHabitat(habitat);
		}
	});
	
	private Button changeCenter = new Button("Change Center", new ClickHandler() {
		public void onClick(ClickEvent event) {
			
			HabitatConfig habitat = new HabitatConfig();
			habitat.setCenterX(Integer.parseInt(XCoord.getValue()));
			habitat.setCenterY(Integer.parseInt(YCoord.getValue()));
			habitat.setModuleLog(habitatConfig.getModuleLog());
			habitat.createConfig(habitatConfig.getHabitatKey());
			
			habitatConfig = habitat;
			
			updateHabitat(habitat);
		}
	});
	
	private Button calculateConfig = new Button("Calculate Config",new ClickHandler() {
		public void onClick(ClickEvent event) {
			if(!configName.getValue().isEmpty()){
				HabitatConfig habitat = new HabitatConfig();
				habitat.setModuleLog(habitatConfig.getModuleLog());
				habitat.setCenterX(20);
				habitat.setCenterY(20);
				habitat.createConfig(configName.getValue());
				setDropdown();
				habitatConfig = habitat;
				quality.setText("Quality: " + Double.toString(habitat.getQualityPercent()));
				updateHabitat(habitat);
			}
		}
	});
	
	
	
}
