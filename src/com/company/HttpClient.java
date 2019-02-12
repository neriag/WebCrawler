package com.company;

import java.io.*;
import java.net.*;


public class HttpClient implements IHttpClient {

    private static HttpClient client;

    private HttpClient() {
    }

    static synchronized HttpClient getInstance() {
        if (client == null) {
            client = new HttpClient();
        }
        return client;
    }

    @Override
    public String get(String urlToRead) throws Exception{
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }
}
