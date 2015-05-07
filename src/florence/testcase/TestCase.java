package florence.testcase;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import florence.client.Module;
import florence.client.Status;

public class TestCase implements EntryPoint {
	
	private String requestedCase = "1";
	private Module[] testCase = new Module[100];
	private int count;
	
	public void onModuleLoad() {
		String url =
				"http://www.d.umn.edu/~abrooks/SomeTests.php?q=" + requestedCase;
		url = URL.encode(url);
				
		count = 0;		
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
						retrieveModules(rt); //METHOD CALL TO DO SOMETHING WITH RESPONSE TEXT
					} else {
						Window.alert("Couldn't retrieve JSON (" + response.getStatusCode()
						+ ")");
					}
				}
			});
		} catch (RequestException e) {
				 Window.alert("RequestException: Couldn't retrieve JSON");
		}
	}
	
	public void changeCase(String newCase){
		requestedCase = newCase;
	}
	
	public void retrieveModules(String rt){
		
		String sAll = rt;
		JSONArray jA =
		(JSONArray)JSONParser.parseLenient(sAll);
		JSONNumber jN;
		 JSONString jS;
		 double c,t,x,y;
		 String s;
		 Status stat;
		for (int i = 0; i < jA.size(); i++) {
			JSONObject jO = (JSONObject)jA.get(i);
			 jN = (JSONNumber) jO.get("code");
			 c = jN.doubleValue();
			 jS = (JSONString) jO.get("status");
			 s = jS.stringValue();
			 if (s == Status.UNDAMAGED.toString().toLowerCase()) {
				 stat = Status.UNDAMAGED;
			 } else if (s == Status.UNCERTAIN.toString().toLowerCase()) {
				 stat = Status.UNCERTAIN;
			 } else {
				 stat = Status.DAMAGED;
			 }
			 jN = (JSONNumber) jO.get("turns");
			 t = jN.doubleValue();
			 jN = (JSONNumber) jO.get("X");
			 x = jN.doubleValue();
			 jN = (JSONNumber) jO.get("Y");
			 y = jN.doubleValue();
			 Module newMod = new Module((int)c,stat,(int)t,x,y);
			 testCase[i] = newMod;
			 count++;
		}
	}
	
	public Module[] getTestCase() {
		return testCase;
	}
	
	public int getCount() {
		return count;
	}
	
	public void update(String rt) {
		 VerticalPanel vp = new VerticalPanel();
		 vp.add(new Label(rt)); //TO VIEW
		 //RootLayoutPanel.get().add(vp);

		 String sAll = rt;
		 JSONArray jA =
		 (JSONArray)JSONParser.parseLenient(sAll);
		 JSONNumber jN;
		 JSONString jS;
		 double d;
		 String s;
		 
		 for (int i = 0; i < jA.size(); i++) {
			 JSONObject jO = (JSONObject)jA.get(i);
			 jN = (JSONNumber) jO.get("code");
			 d = jN.doubleValue();
			 vp.add(new Label(Double.toString(d))); //TO VIEW
			 jS = (JSONString) jO.get("status");
			 s = jS.stringValue();
			 vp.add(new Label(s)); //TO VIEW
			 jN = (JSONNumber) jO.get("turns");
			 d = jN.doubleValue();
			 vp.add(new Label(Double.toString(d))); //TO VIEW
			 jN = (JSONNumber) jO.get("X");
			 d = jN.doubleValue();
			 vp.add(new Label(Double.toString(d))); //TO VIEW
			 jN = (JSONNumber) jO.get("Y");
			 d = jN.doubleValue();
			 vp.add(new Label(Double.toString(d))); //TO VIEW
			 vp.add(new HTML("<hr />"));
			 }
			 RootLayoutPanel.get().add(vp);

	}
}
