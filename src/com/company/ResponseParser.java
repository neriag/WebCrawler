package com.company;

import org.jsoup.Connection.Response;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

class ResponseParser {
    private static ResponseParser instance;

    private ResponseParser() {
    }

    static synchronized ResponseParser getInstance() {
        if (instance == null) {
            instance = new ResponseParser();
        }
        return instance;
    }

    private String resolveUrl(String baseUrl, String relativeUrl) throws URISyntaxException {
        URI uri = new URI(baseUrl);
        return uri.resolve(relativeUrl).toString();
    }

    String urlWithoutProtocl(String url) throws URISyntaxException{
        URI uri = new URI(url);
        return uri.getHost() + uri.getPath();
    }

    List<String> getAllLinks(Response res) throws java.io.IOException, URISyntaxException{
        List<String> result = new ArrayList<>();
        Elements links = res.parse().select("a");
        for (Element link : links) {
            String href = link.attr("href");
            String url = resolveUrl(res.url().toString(), href);
            if (!url.isEmpty() && !url.startsWith("#")) {
                result.add(url);
            }
        }
        return result;
    }
}
