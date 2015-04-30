package florence.weather;


import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Display {
	public void onModuleLoad() {
		String url =
		"http://api.wunderground.com/api/f9c8dc6c804056c7/conditions/q/55805.json";
		url = URL.encode(url);
		// Send request to server and catch any errors.
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
		try {
		  Request request = builder.sendRequest(null, new RequestCallback() {
		public void onError(Request request, Throwable exception) { Window.alert("onError: Couldn't retrieve JSON");
		}
		    public void onResponseReceived(Request request, Response response) {
		        if (200 == response.getStatusCode()) {
		            String rt = response.getText();
		            update(rt); //METHOD CALL TO DO SOMETHING WITH RESPONSE TEXT
		        } else {
		Window.alert("Couldn't retrieve JSON (" + response.getStatusCode() + ")");
		} }
		  });
		} catch (RequestException e) {
		}
		Window.alert("RequestException: Couldn't retrieve JSON");
	}	
		public void update(String rt) { VerticalPanel vp = new VerticalPanel(); vp.add(new Label(rt)); //TO VIEW RootLayoutPanel.get().add(vp);
		}
		
	
}
