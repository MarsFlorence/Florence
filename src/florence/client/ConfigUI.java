package florence.client;

//import florence.map.Mapper;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ConfigUI {
	
	final Grid mapGrid = new Grid(99, 99);
	
	
	public ConfigUI(Module[] inputMods, int modCount){
		Mapper theMap = new Mapper(inputMods, modCount);
		Module[][] extractor = theMap.getMap();
		Module current;
		
		for(int x = 0; x < 99; x++){
			for(int y = 0; y < 99; y++){
				current = extractor[x][y];
				if(current != null){
					Panel newPicture = null;
					newPicture.add(current.imageLocate());
					mapGrid.setWidget(x, y, newPicture);
				}
			}
		}
		mapGrid.setVisible(true);
	}
	
	public Grid makeConfig(){
		return mapGrid;
	}
}
