package com.example;

import org.json.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.Desktop;
import java.io.BufferedReader;

public class App {
    public static String getData(String ip) {
        String response = "";
        URL url;
        try {

            String a = "https://ipinfo.io" + ip + "/json";
            url = new URL(a);
            URLConnection conn = url.openConnection();

            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                response = response + inputLine;
            }
            br.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return response;
    }

    public static void main(String[] args) throws IOException {
        String location = "";
        String maplink;  
        // get location information
        location = getData("");
        JSONObject obj = new JSONObject(location); 
        //fetching latitude and longitude
        maplink = "https://www.google.com/maps/?q=" + obj.getString("loc");

        try {
            //opening map link in browser
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(maplink);
            desktop.browse(oURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
