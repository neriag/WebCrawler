package com.company;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class FileSystem {

    private static FileSystem instance;

    private FileSystem() {
    }

    static synchronized FileSystem getInstance() {
        if (instance == null) {
            instance = new FileSystem();
        }
        return instance;
    }

    private String shortenFileName(String fileName){
        final String REGX = "[\\:/*?\"<>|]";
        Pattern pattern = Pattern.compile(REGX);
        Matcher matcher = pattern.matcher(fileName);
        if (matcher.find()) {
            int index = matcher.start();
            return fileName.substring(0, index);
        } else {
            return fileName;
        }
    }


    void writeToFile(String fileName, String text) throws FileNotFoundException {
        // NOTE: there is not specification for collission between two urls
        //       (for examples http://foo.com and http://foo.com/home)
        //        so I decided by myself to override the old one.
        String shortFileName = shortenFileName(fileName);
        try (PrintWriter out = new PrintWriter("./downloads/" + shortFileName)) {
            out.println(text);
        }
    }

}
