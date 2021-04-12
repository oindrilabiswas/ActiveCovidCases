package getapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;


public class ActiveCovidCases 
{

	public static void main(String[] args) throws IOException, JSONException 
	{
		String url="https://api.covid19india.org/v4/min/data.min.json";
	  	URL obj=new URL(url);
	  	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
		String inputLine;
	    StringBuffer response = new StringBuffer();
	    while ((inputLine = in.readLine()) != null)
	     {
	      	response.append(inputLine);
	     }
	    in.close();
	    JSONObject responseJSON = new JSONObject(response.toString());
	    System.out.println("State			Active cases");
	    System.out.println("------------------------------");
	    for(String stateCode : responseJSON.getNames(responseJSON))
	     {
	    	 JSONObject state=new JSONObject(responseJSON.get(stateCode).toString());
	    	 JSONObject total=new JSONObject(state.get("total").toString());
	    	 int conf=total.getInt("confirmed");
	    	 int dec=total.getInt("deceased");
	    	 int rec=total.getInt("recovered");
		     int ac= (conf-rec-dec);
		     System.out.println(stateCode+"			 "+ac);
	     }
	}
}

	


