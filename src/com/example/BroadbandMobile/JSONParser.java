package com.example.BroadbandMobile;


import android.os.AsyncTask;
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
import java.net.URL;
import java.net.URLConnection;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class JSONParser
{
	private String _result;
	private BroadbandData _data;
    private boolean _isReady = false;

	public JSONParser(){
		super();
        this._isReady = false;
	}

    public boolean getIsReady() {
        return _isReady;
    }

    public void setIsReady(boolean _isReady) {
        this._isReady = _isReady;
    }

    public void loadData(String url) throws Exception{
        this._isReady = false;
        new DownloadHttpTask(this).execute(url);
    }

    public void parseJSON(String data) {
        JSONArray root = new JSONArray(data);
        JSONObject settings = root.getJSONObject(0).getJSONObject("settings");
        JSONObject obvalues = root.getJSONObject(1);
        JSONArray values = obvalues.getJSONArray("data");
        this._data = new BroadbandData();

        String ytype = settings.getString("ytype");
        String xtype = settings.getString("xtype");

        this._data.setXtype(xtype);
        this._data.setYtype(ytype);

        for(int i = 0; i < values.length(); i++) {
            BroadbandPoint point = new BroadbandPoint();
            point.setX((i+1));
            point.setY(values.getDouble(i));
            this._data.addPoint(point);
        }
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

    private class DownloadHttpTask extends AsyncTask<String, Void, String>
    {
        JSONParser _jp;
        public DownloadHttpTask(JSONParser jp)
        {
            this._jp = jp;
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub

            StringBuilder sb = new StringBuilder();

            URL url;
            for (int i = 0; i < params.length; i++) {
                try {

                    url = new URL(params[i]);
                    URLConnection conn = url.openConnection();

                    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line  = "";
                    StringBuffer buffer = new StringBuffer();
                    String NL = System.getProperty("line.separator");
                    while ( (line = rd.readLine()) != null )
                    {
                        buffer.append(line);
                    }
                    String str = buffer.toString();
                    sb.append(buffer.toString());
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            //super.onPostExecute(result);
            this._jp.parseJSON(result);
            this._jp.setIsReady(true);
        }
    }


}

