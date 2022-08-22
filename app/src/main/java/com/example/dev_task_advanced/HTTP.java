package com.example.dev_task_advanced;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class HTTP {
    private HttpURLConnection connection;
    private String baseUrl;
    private HashMap<String, String> headers = new HashMap<String, String>();
    private String query;
    private String metode = "GET";

    public HTTP header(HashMap<String, String> h) {
        this.headers = h;
        return this;
    }

    public HTTP query (String q){
        this.query = q;
        return this;
    }

    public HTTP metode(String metode){
        this.metode= metode;
        return this;
    }

    public HTTP baseUrl(String url){
        this.baseUrl = url;
        return this;
    }

    public Response connect (){
        Response content = null;
        try {
            content = new urlConnect().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return content;
    }

    public class urlConnect extends AsyncTask<String, Void, Response> {
        @Override
        protected Response doInBackground(String... string) {

            Response response = new Response();
            StringBuffer responseContent = new StringBuffer();

            BufferedReader readers;

                String line;
                String urlStr = baseUrl;
                if(query != null){
                    urlStr += "?"+query;
                }
            InputStream is;
            try {
                URL url = new URL(urlStr);

                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod(metode);
                    connection.setConnectTimeout(4000);

                for(HashMap.Entry<String, String> m : headers.entrySet()) {
                    connection.addRequestProperty(m.getKey(), m.getValue());
                }
                    response.responseCode = connection.getResponseCode();
                    is = connection.getInputStream();
                } catch (Exception e) {
                    is = connection.getErrorStream();
                return response;
                }

                readers = new BufferedReader(new InputStreamReader(is));

                try{
                    while((line = readers.readLine()) != null){
                        responseContent.append(line);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }

                response.content=responseContent.toString();

            return response;

        }
        @Override
        protected void onPostExecute(Response s) {
            super.onPostExecute(s);
        }
    }

}