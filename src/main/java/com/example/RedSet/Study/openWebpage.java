package com.example.RedSet.Study;

import java.io.IOException;

public class openWebpage {
    public static void open(String url){
        try {
            new ProcessBuilder("chrome", url).start();
        } catch (
                IOException e) {
            try {
                new ProcessBuilder("firefox", url).start();
            } catch (IOException ex) {
                try {
                    new ProcessBuilder("edge", url).start();
                } catch (IOException exc) {
                    throw new RuntimeException(exc);
                }
            }
        }
    }
}
