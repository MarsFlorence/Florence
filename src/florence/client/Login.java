package florence.client;



import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


public class Login {
	
	private TextBox input = new TextBox();
	private String password = "Space";
	private String userName = "Mars";
	private PasswordTextBox secret = new PasswordTextBox();
	private VerticalPanel pan = new VerticalPanel();
	private Label userLabel = new Label("Username:");
	private Label passwordLabel = new Label("Password:");	
	public Login(final TabLayoutPanel tabPanel){	   
		Label welcomeMessage = new Label("Welcome to the Mars Habitat Control System.");
		welcomeMessage.addStyleName("gwt-Label-Header");
		welcomeMessage.setStylePrimaryName("gwt-Label-Header");
		pan.add(welcomeMessage);
		//Making the username/password textbox the same size
		input.setPixelSize(120, 20);
		secret.setPixelSize(120, 20);		
		pan.setCellHorizontalAlignment(userLabel, HasHorizontalAlignment.ALIGN_LEFT);
		pan.setCellVerticalAlignment(userLabel, HasVerticalAlignment.ALIGN_MIDDLE);	
		pan.setCellHorizontalAlignment(input, HasHorizontalAlignment.ALIGN_RIGHT);
		pan.setCellVerticalAlignment(input, HasVerticalAlignment.ALIGN_MIDDLE);
		//Adding the username
		pan.add(userLabel);
		pan.add(input);		
		pan.setCellHorizontalAlignment(passwordLabel, HasHorizontalAlignment.ALIGN_LEFT);
		pan.setCellVerticalAlignment(passwordLabel, HasVerticalAlignment.ALIGN_MIDDLE);			
		pan.setCellHorizontalAlignment(secret, HasHorizontalAlignment.ALIGN_RIGHT);
		pan.setCellVerticalAlignment(secret, HasVerticalAlignment.ALIGN_MIDDLE);
		
		//Adding the password
		pan.add(passwordLabel);
		pan.add(secret);
		
		//Setting the spacing so the Submit button isn't attached to the password field
		pan.setSpacing(5);
		
		Button submit = new Button("Submit", new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	  if(input.getValue().equals(userName) && secret.getValue().equals(password)){
		    		  
		    		  //This code MUST stay in this onclick handler. It is required so that the tabs aren't created until the user logs in.
		    		  //Please let me know if you plan on changing this code - Jake
		    		    tabPanel.add(new LoggingModules().LoggingModulesPanel(), "Module Logging");
		    		    tabPanel.add(new HTML("Configuration"), "Configuration");
		    		    tabPanel.add(new HTML("Opitimal Routing"), "Optimal Routing");	    		  
		    		  if(tabPanel.getTabWidget(1) != null){
		    			  tabPanel.selectTab(1);
		    		  }else{
			    		  Window.alert("An Error occured");		    			  
		    		  }
		    	  }else{
		    		  input.setText("");
		    		  secret.setText("");
		    		  Window.alert("Incorrect Password or Username");
		    	  }
		      }
		    });		
		
		
		pan.add(submit);
		pan.setCellHorizontalAlignment(submit, HasHorizontalAlignment.ALIGN_LEFT);
		pan.setCellVerticalAlignment(submit, HasVerticalAlignment.ALIGN_MIDDLE);
		pan.setVisible(true);
	}
	public VerticalPanel makeLogin(){
		return pan;
	}
	
	
	
	
}
