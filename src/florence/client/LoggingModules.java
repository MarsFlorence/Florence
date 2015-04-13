package florence.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
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
	private FlexTable moduleTable = new FlexTable();
	private Button logMod = new Button("Log Module", new ClickHandler() {
	      public void onClick(ClickEvent event) {
	    	  Module newMod = new Module();
	    	  newMod.setId(Integer.parseInt(modNum.getValue()));
	    	  newMod.setStatus(modStatus.getValue(modStatus.getSelectedIndex()));
	    	  newMod.setOrientation(Integer.parseInt(modOrientation.getValue(modOrientation.getSelectedIndex())));
	    	  newMod.setXCoord(Double.parseDouble(modXCoord.getValue()));
	    	  newMod.setYCoord(Double.parseDouble(modYCoord.getValue()));
	    	  moduleLog.addModule(newMod);
	    	  addTable();
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
	
	public VerticalPanel LoggingModulesPanel(){
		return panel;
	}
	
	public void addTable(){
		moduleTable.setBorderWidth(2);
		moduleTable.setText(0, 0, "Module ID");
		moduleTable.setText(0, 1, "Module Status");
		moduleTable.setText(0, 2, "Module Orientation");
		moduleTable.setText(0, 3, "Module X Coordinate");
		moduleTable.setText(0, 4, "Module Y Coordinate");
		int size = moduleLog.getSize();
		for (int index = 0; index < size; index++){
			Module currentMod = moduleLog.getModule(index);
			moduleTable.setText(index + 1, 0, Integer.toString(currentMod.getId()));
			moduleTable.setText(index + 1, 1, currentMod.getStatus().toString());
			moduleTable.setText(index + 1, 2, Integer.toString(currentMod.getOrientation()));
			moduleTable.setText(index + 1, 3, Double.toString(currentMod.getXCoord()));
			moduleTable.setText(index + 1, 4, Double.toString(currentMod.getYCoord()));
		}
		panel.add(moduleTable);
	}
}