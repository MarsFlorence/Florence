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
		proxy+"http://api.wunderground.com/api/f9c8dc6c804056c7/conditions/astronomy/q/Canada/Winnipeg.json";
		url = URL.encode(url);
		// Send request to server and catch any errors.
		
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
		builder.setHeader("Access-Control-Allow-Origin", "*");
		try {
		  Request request = builder.sendRequest(null, new RequestCallback() {
			  
		public void onError(Request request, Throwable exception) {
			Window.alert("onError: Couldn't retrieve JSON");
		}
		    public void onResponseReceived(Request request, Response response) {
		    	
;		        if (200 == response.getStatusCode()) {
		            String rt = response.getText();
		            update(rt); //METHOD CALL TO DO SOMETHING WITH RESPONSE TEXT
		        } else {
		                Window.alert("Couldn't retrieve JSON (" + response.getStatusCode() + ")");
		               } 
		    }
		  });
		} catch (RequestException e) {
			
		}
		Window.alert("Updated Weather Forecast");
	}	
		public void update(String rt) {
			
			String sAll = rt;
			JSONObject jA = 
					(JSONObject)JSONParser.parseLenient(sAll);
			JSONValue jTry = jA.get("current_observation");
			
			
		//Astronomy start : going into sun_phase
			JSONObject jC = 
					(JSONObject)JSONParser.parseLenient(sAll);
			JSONValue jTry1 = jC.get("sun_phase");
			
			JSONObject jD = (JSONObject)JSONParser.parseLenient(jTry1.toString());
		//Astronomy end
			
			
		//Astronomy : going into sunset
			JSONValue jSunset = jD.get("sunset");
			
			JSONObject jHourObject = 
					(JSONObject)JSONParser.parseLenient(jSunset.toString());
		//Astronomy end
			
			
			
			JSONObject jB = (JSONObject)JSONParser.parseLenient(jTry.toString());
			JSONValue temp = jB.get("temperature_string");
			JSONValue weather = jB.get("weather");
			JSONValue visibility = jB.get("visibility_km");
			
		//Astronomy : time until sunset
			JSONValue hr = jHourObject.get("hour");
			JSONValue min = jHourObject.get("minute");
		//Astronomy end
			
			String sTemp = temp.toString();
			String sWeather = weather.toString();
			String sVisibility = visibility.toString();
			
		//Astronomy 
			String sSunsetHr = hr.toString();
			String sSunsetMin = min.toString();
		//Astronomy end

			
			//vp.add(new Label(sLocation)); //TO VIEW
			vp.add(new Label("Conditions: " + sWeather)); //TO VIEW
			vp.add(new Label("Temperature: " + sTemp)); //TO VIEW
			vp.add(new Label("Visibility: " + sVisibility)); //TO VIEW
			
		//Astronomy start
			vp.add(new Label("Sunset: " + sSunsetHr + " hours and " + sSunsetMin + " minutes")); //TO VIEW
		//Astronomy end

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
