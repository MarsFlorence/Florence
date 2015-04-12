package florence.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoggingModules {
	private VerticalPanel panel = new VerticalPanel();
	private TextBox modNum = new TextBox();
	private ListBox modStatus = new ListBox();
	private ListBox modOrientation = new ListBox();
	private TextBox modXCoord = new TextBox();
	private TextBox modYCoord = new TextBox();
	private ModuleLog moduleLog = new ModuleLog();
	private Button logMod = new Button("Log Module", new ClickHandler() {
	      public void onClick(ClickEvent event) {
	    	  Module newMod = new Module();
	    	  newMod.setId(Integer.parseInt(modNum.getValue()));
	    	  newMod.setStatus(modStatus.getValue(modStatus.getSelectedIndex()));
	    	  newMod.setOrientation(Integer.parseInt(modOrientation.getValue(modOrientation.getSelectedIndex())));
	    	  newMod.setXCoord(Double.parseDouble(modXCoord.getValue()));
	    	  newMod.setYCoord(Double.parseDouble(modYCoord.getValue()));
	    	  moduleLog.addModule(newMod);
	      }
	    });
	public LoggingModules(){
		modStatus.addItem("UNDAMAGED");
		modStatus.addItem("DAMAGED");
		modStatus.addItem("UNCERTAIN");
		modOrientation.addItem("0");
		modOrientation.addItem("1");
		modOrientation.addItem("2");
		Label numLabel = new Label("Module ID:");
		Label statusLabel = new Label("Module Status:");
		Label numOrientation = new Label("Module Orientation:");
		Label xCoordLabel = new Label("Module X Coordinate:");
		Label yCoordLabel = new Label("Module Y Coordinate:");
		panel.add(numLabel);
		panel.add(modNum);
		panel.add(statusLabel);
		panel.add(modStatus);
		panel.add(numOrientation);
		panel.add(modOrientation);
		panel.add(xCoordLabel);
		panel.add(modXCoord);
		panel.add(yCoordLabel);
		panel.add(modYCoord);
		panel.add(logMod);
	}
	
	public VerticalPanel LogginModulesPanel(){
		return panel;
	}
}
