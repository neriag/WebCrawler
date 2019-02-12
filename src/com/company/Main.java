package com.company;

public class Main {
    public static void main(String[] args) throws Exception {
        String url = "https://en.wikipedia.org/wiki/Main_Page";//args[0];
        IHttpClient client = HttpClient.getInstance();
        String html = client.get(url);
        System.out.println(html);
    }
}
