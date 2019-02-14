package com.company;

import org.jsoup.Connection.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

    private static void crawl(String url, int depth){
        try {
            tryCrawl(url, depth);
        } catch (URISyntaxException ex){
            System.out.println("URISyntaxException occurred");
        } catch (IOException ex) {
            System.out.println("IOException occurred");
        }
    }

    private static void tryCrawl(String url, int depth) throws URISyntaxException, IOException {

        Response res = HttpClient.getInstance().get(url);

        if (!res.contentType().contains("text/html")) {
            return;
        }

        String urlWithoutProtocol = ResponseParser.getInstance().urlWithoutProtocl(url);
        FileSystem.getInstance().writeToFile(urlWithoutProtocol, res.body());

        if (depth > 1) {
            List<String> links = ResponseParser.getInstance().getAllLinks(res);
            links.forEach(link -> crawl(link, depth -1));
        }
    }


    public static void main(String[] args) {
        crawl("http://www.mocky.io/v2/5185415ba171ea3a00704eed", 2);
        crawl("http://en.wikipedia.org/", 2);
    }
}
