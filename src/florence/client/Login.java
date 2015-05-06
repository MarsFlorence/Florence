package florence.client;



import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import florence.weather.JSONsubvertSOP;

/**
 * Class that creates login tab and creates new tabs after
 * user name and password are entered correctly.
 */
public class Login {
	/**
	 * Input box for user name.
	 */
	private TextBox input = new TextBox();
	/**
	 * The set password.
	 */
	private String password = "Space";
	/**
	 * The set user name.
	 */
	private String userName = "Mars";
	/**
	 * Input box for password.
	 */
	private PasswordTextBox secret = new PasswordTextBox();
	/**
	 * Panel where Labels and text boxes will be placed as well as button.
	 */
	private VerticalPanel pan = new VerticalPanel();
	/**
	 * Label used to label the text box.
	 */
	private Label userLabel = new Label("Username:");
	/**
	 * Label used to label the password text box.
	 */
	private Label passwordLabel = new Label("Password:");
	/**
	 * Constructor that takes in a panel where it adds
	 * new tabs to on correct user name and password entry.
	 * @param tabPanel the tab panel where new tabs will be added
	 */
	public Login(final TabLayoutPanel tabPanel) {	   
		Label welcomeMessage = new Label(
				"Welcome to the Mars Habitat Configuration System.");
		welcomeMessage.addStyleName("gwt-Label-Header");
		welcomeMessage.setStylePrimaryName("gwt-Label-Header");
		pan.add(welcomeMessage);
		//Making the username/password textbox the same size
		input.setPixelSize(120, 20);
		secret.setPixelSize(120, 20);		
		pan.setCellHorizontalAlignment(
				userLabel, HasHorizontalAlignment.ALIGN_LEFT);
		pan.setCellVerticalAlignment(
				userLabel, HasVerticalAlignment.ALIGN_MIDDLE);	
		pan.setCellHorizontalAlignment(
				input, HasHorizontalAlignment.ALIGN_RIGHT);
		pan.setCellVerticalAlignment(
				input, HasVerticalAlignment.ALIGN_MIDDLE);
		//Adding the username
		pan.add(userLabel);
		pan.add(input);		
		pan.setCellHorizontalAlignment(
				passwordLabel, HasHorizontalAlignment.ALIGN_LEFT);
		pan.setCellVerticalAlignment(
				passwordLabel, HasVerticalAlignment.ALIGN_MIDDLE);			
		pan.setCellHorizontalAlignment(
				secret, HasHorizontalAlignment.ALIGN_RIGHT);
		pan.setCellVerticalAlignment(secret, HasVerticalAlignment.ALIGN_MIDDLE);
		
		//Adding the password
		pan.add(passwordLabel);
		pan.add(secret);
		
		//Setting the spacing so the Submit button isn't 
		//attached to the password field
		pan.setSpacing(5);
		
		Button submit = new Button("Submit", new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	  if (input.getValue().equals(userName) 
		    			  && secret.getValue().equals(password)) {
		    		  
		    		  setOldDate();
		    		  if(outdated()){
		    			  Window.alert("10 days have elapsed since the milometer device has been calibrated.");
		    		  }
		    		  
		    		  //This code MUST stay in this onclick handler.
		    		  //It is required so that the tabs aren't
		    		  //created until the user logs in.
		    		  //Please let me know if you plan on
		    		  //changing this code - Jake
		    		  LoggingModules theLog = new LoggingModules();
		    		  ConfigUI map = new ConfigUI(theLog.getModLog(), theLog.getModLog().getSize());
		    		  theLog.attachMap(map);
		    		  
		    		  HabitatDisplay habitat = new HabitatDisplay(new HabitatConfig(theLog.getModLog()));
		    		  theLog.attachHabitat(habitat);
		    		  
		    		  JSONsubvertSOP weather = new JSONsubvertSOP();
		    		  
		    		    tabPanel.add(theLog.LoggingModulesPanel(),
		    		    		"Module Logging");
		    		    tabPanel.add(habitat.makeHabitatDisplay(),
		    		    		"Configuration");
		    		    tabPanel.add(map.makeMap(),
		    		    		"Optimal Routing");
		    		    tabPanel.add(weather.getVP(),
		    		    		"Weather");	
		    		  if (tabPanel.getTabWidget(1) != null) {
		    			  tabPanel.selectTab(1);
		    		  } else {
			    		  Window.alert(
			    				  "An Error occured");		    			  
		    		  }
		    	  } else {
		    		  input.setText("");
		    		  secret.setText("");
		    		  Window.alert("Incorrect Password or Username");
		    	  }
		      }
		    });		
		
		
		pan.add(submit);
		pan.setCellHorizontalAlignment(
				submit, HasHorizontalAlignment.ALIGN_LEFT);
		pan.setCellVerticalAlignment(submit, HasVerticalAlignment.ALIGN_MIDDLE);
		ListBox selectLang = new ListBox();
		pan.setVisible(true);
	}
	/**
	 * Method that returns the UI login panel.
	 * @return VerticalPanel the login screen
	 */
	public VerticalPanel makeLogin() {
		return pan;
	}
	/**
	 * Original date of Mars landing or last rover maintenance.
	 */
	private Date oldDate;
	/**
	 * Method that gets current date.
	 * @return Date the current day's date
	 */
	public Date today() {
		Date currentDate = new Date();
		return currentDate;
	}
	/**
	 * Method that sets the new Old date.
	 */
	@SuppressWarnings("deprecation")
	public void setOldDate() {
		oldDate = new Date(0000, 0, 0, 0, 0, 0);
	}
	/**
	 * Method that determines if calibration should be performed.
	 * @return true calibration needed, false no action required
	 */
	public boolean outdated() {
		long dateDifference = new Date().getTime() - oldDate.getTime();
		double days = dateDifference / (1000 * 60 * 60 * 24);
		if (days > 10) {
			return true;
		}
		return false;
	}
	
}
