package florence.client;



import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


public class Login {
	
	private TextBox input = new TextBox();
	private String password = "Space";
	private String userName = "Mars";
	private Button submit = new Button("Submit", new ClickHandler() {
	      public void onClick(ClickEvent event) {
	    	  if(input.getValue().equals(userName) && secret.getValue().equals(password)){
	    		  Window.alert("You are good.");
	    	  }else{
	    		  input.setText("");
	    		  secret.setText("");
	    		  Window.alert("Incorrect Password or Username");
	    	  }
	      }
	    });
	private PasswordTextBox secret = new PasswordTextBox();
	private VerticalPanel pan = new VerticalPanel();
	
	public Login(){
		pan.add(input);
		pan.setCellHorizontalAlignment(input, HasHorizontalAlignment.ALIGN_CENTER);
		pan.setCellVerticalAlignment(input, HasVerticalAlignment.ALIGN_MIDDLE);
		pan.add(secret);
		pan.setCellHorizontalAlignment(secret, HasHorizontalAlignment.ALIGN_CENTER);
		pan.setCellVerticalAlignment(secret, HasVerticalAlignment.ALIGN_MIDDLE);
		pan.add(submit);
		pan.setCellHorizontalAlignment(submit, HasHorizontalAlignment.ALIGN_CENTER);
		pan.setCellVerticalAlignment(submit, HasVerticalAlignment.ALIGN_MIDDLE);
		pan.setVisible(true);
	}
	
	public VerticalPanel makeLogin(){
		return pan;
	}
	
	
	
	
}
