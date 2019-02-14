package com.company;

import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {

    private static void crawl(String url, int depth) throws Exception{

        Response res = HttpClient.getInstance().get(url);

        if (!res.contentType().contains("text/html")){
            return;
        }

        String urlWithoutHttp = url.replace("http://", "").replace("https", "");
        FileSystem.getInstance().writeToFile(urlWithoutHttp, res.body());
    }


    public static void main(String[] args) throws Exception {
        crawl("http://www.mocky.io/v2/5185415ba171ea3a00704eed", 1);
        crawl("http://en.wikipedia.org/", 1);
    }
}
