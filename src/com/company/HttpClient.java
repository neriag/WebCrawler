package com.company;

import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

class HttpClient{

    private static HttpClient instance;

    private HttpClient() {
    }

    static synchronized HttpClient getInstance() {
        if (instance == null) {
            instance = new HttpClient();
        }
        return instance;
    }

    Response get(String url) throws IOException{
        Connection conn = Jsoup.connect(url);
        conn.ignoreContentType(true);
        return conn.execute();
    }
}
