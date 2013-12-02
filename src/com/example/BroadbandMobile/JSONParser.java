package com.example.BroadbandMobile;


import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class JSONParser
{
	private String _result;
	private BroadbandData _data;

	public JSONParser(){
		super();
	}

	public void loadData(String url) throws Exception{

        BufferedReader bufferedReader = null;
        try
        {
            HttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "android");
            HttpGet request = new HttpGet();
            request.setHeader("Content-Type", "text/plain; charset=utf-8");
            request.setURI(new URI(url));
            HttpResponse response = client.execute(request);
            bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer stringBuffer = new StringBuffer("");
            String line = "";

            String NL = System.getProperty("line.separator");
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line + NL);
                System.out.print(stringBuffer);
            }
            bufferedReader.close();
            this._result = stringBuffer.toString();
            System.out.println(this._result+"page");
            parseJSON(this._result);
        }
        finally
        {
            if (bufferedReader != null)
            {
                try
                {
                    bufferedReader.close();
                }
                catch (IOException e)
                {
                    Log.d("BBB", e.toString());
                }
            }
        }
    }

    private void parseJSON(String data) {
        JSONArray root = new JSONArray(data);
        JSONObject settings = root.getJSONObject(0);
        JSONArray values = root.getJSONArray(1);


    }
	
	public BroadbandData getResult() {
		return this._data;
	}

	public void simulateLoadData() {
        String url = "https://dl.dropboxusercontent.com/u/1739967/data.json";
        try {
            this.loadData(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

