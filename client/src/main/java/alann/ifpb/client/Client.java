package alann.ifpb.client;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author alann
 */
public class Client {

    private static String cookie;

    public static void main(String[] args) throws IOException, InterruptedException {

        cookie = "null";
        String name = "Alann-requisicao";
        forRequest(name);
        
        Thread.sleep(4000);
        cookie = "null";
        forRequest(name);

    }

    public static void forRequest(String name) throws IOException {
        for (int k = 0; k < 3; k++) {
            request(name + k);
        }

    }

    public static void request(String name) throws MalformedURLException, IOException {

        URL url = new URL("http://localhost:8080/" + name + "/" + cookie);
        URLConnection conn = url.openConnection();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = buffer.readLine()) != null) {
            cookie = line;
        }

    }
}
