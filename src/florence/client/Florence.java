package florence.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import florence.shared.FieldVerifier;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Florence implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
	    // Create a three-item tab panel, with the tab area 1.5em tall.
	    final TabLayoutPanel p = new TabLayoutPanel(1.5, Unit.EM);
	    
	    
	    VerticalPanel log = new Login(p).makeLogin();
	    
	    p.add(log, "Login");
	    //The other tab layouts panels are added 
	    //when the "submit" button is clicked with 
	    //a correct username and password
	    
	    
	    //This is all commented out for now,
	    //but this code could be useful in the future.
	    //This code won't work for right now. - Jake
//	    final Widget loginTab = p.getTabWidget(0);
//	    final Widget moduleLoggingTab = p.getTabWidget(1);
//	    final Widget configurationTab = p.getTabWidget(2);
//	    final Widget optimalRoutingTab = p.getTabWidget(3);	 




	    
	    // Attach the LayoutPanel to the RootLayoutPanel.
	    //The latter will listen for resize events on 
	    //the window to ensure that its children are 
	    //informed of possible size changes.
	    RootLayoutPanel rp = RootLayoutPanel.get();
	    rp.add(p);
	  }
	/**
	 * Method that constructs login tab.
	 */
	public void makeLogin() {
		final Button sendButton = new Button("Login");
		final TextBox idField = new TextBox();
		final PasswordTextBox passwordField = new PasswordTextBox();
		idField.setText("Username");
		passwordField.setText("Password");
		final Label errorLabel = new Label();
		// We can add style names to widgets
		sendButton.addStyleName("sendButton");
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("idContainer").add(idField);
		RootPanel.get("passwordContainer").add(passwordField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);
		// Focus the cursor on the name field when the app loads
		idField.setFocus(true);
		passwordField.setFocus(true);
		idField.selectAll();
		passwordField.setFocus(true);
		/**
		 * Create a handler for the sendButton and nameField.
		 */
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 * @param event the click event
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 * @param event the click event on key
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField 
			 * to the server and wait for a response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = idField.getText();
				String passwordToServer = passwordField.getText();
				if (!FieldVerifier.isValidName(textToServer) 
						&& !FieldVerifier.isValidName(passwordToServer)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}

				// Then, we send the input to the server.
				//sendButton.setEnabled(false);
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		idField.addKeyUpHandler(handler);
	}
}
