package florence.weather;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class JSONsubvertSOP {
	
	public JSONsubvertSOP() {
		onModuleLoad();
	}
	
	public void onModuleLoad() {
		String proxy = "http://www.d.umn.edu/~and02586/Weather.php?url=";
		String url =
		proxy+"http://api.wunderground.com/api/f9c8dc6c804056c7/conditions/q/55805.json";
		url = URL.encode(url);
		// Send request to server and catch any errors.
		
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
		try {
		  Request request = builder.sendRequest(null, new RequestCallback() {
			  
		public void onError(Request request, Throwable exception) {
			Window.alert("onError: Couldn't retrieve JSON");
		}
		    public void onResponseReceived(Request request, Response response) {
		        if (200 == response.getStatusCode()) {
		            String rt = response.getText();
		            update(rt); //METHOD CALL TO DO SOMETHING WITH RESPONSE TEXT
		        } else {
		                Window.alert("Couldn't retrieve JSON (" + response.getStatusCode() + ")");
		               } 
		    }
		  });
		} catch (RequestException e) {
			Window.alert("RequestException: Couldn't retrieve JSON");
		}
		
	}	
		public void update(String rt) {
			//VerticalPanel vp = new VerticalPanel();
			//vp.add(new Label(rt)); //TO VIEW
			
			String sAll = rt;
			JSONObject jA = 
					(JSONObject)JSONParser.parseLenient(sAll);
			JSONValue jTry = jA.get("current_observation");
			//String sTry = jTry.toString();
			
			JSONObject jB = (JSONObject)JSONParser.parseLenient(jTry.toString());
			JSONValue tempf = jB.get("temp_f");
			JSONValue tempc = jB.get("temp_c");
			JSONValue weather = jB.get("weather");
			JSONValue visibility = jB.get("visibility_km");
			
			String sTemp1 = tempf.toString();
			String sTemp2 = tempc.toString();
			String sWeather = weather.toString();
			String sVisibility = visibility.toString();
			

			vp.add(new Label(sTemp1)); //TO VIEW
			vp.add(new Label(sTemp2)); //TO VIEW
			vp.add(new Label(sWeather)); //TO VIEW
			vp.add(new Label(sVisibility)); //TO VIEW

			vp.add(new Label("Visibility: " + sVisibility)); //TO VIEW
			vp.add(new Label("Temperature: " + sTemp1)); //TO VIEW


			DockPanel dock = new DockPanel();
			dock.add(new Image("images/wundergroundLogo_4c_horz.jpg"),DockPanel.WEST);
			vp.add(dock);
		    RootLayoutPanel.get().add(vp);
		}
		
		private VerticalPanel vp = new VerticalPanel();
		
		public VerticalPanel getVP() {
			return vp;
		}
		
		public void runW() {
			onModuleLoad();
		}
		
	
}
