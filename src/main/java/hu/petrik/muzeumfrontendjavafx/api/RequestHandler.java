package hu.petrik.muzeumfrontendjavafx.api;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RequestHandler {
    public RequestHandler() {
    }

    public static Response get(String url) throws IOException {
        HttpURLConnection connection = setUpConnection(url);
        return getResponse(connection);
    }

    public static Response post(String url, String body) throws IOException {
        HttpURLConnection connection = setUpConnection(url);
        connection.setRequestMethod("POST");
        addRequestBody(connection, body);
        return getResponse(connection);
    }

    public static Response put(String url, String body) throws IOException {
        HttpURLConnection connection = setUpConnection(url);
        connection.setRequestMethod("PUT");
        addRequestBody(connection, body);
        return getResponse(connection);
    }

    public static Response delete(String url) throws IOException {
        HttpURLConnection connection = setUpConnection(url);
        connection.setRequestMethod("DELETE");
        return getResponse(connection);
    }

    private static HttpURLConnection setUpConnection(String url) throws IOException{
        URL urlObj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        connection.setRequestProperty("Accept", "application/json");
        connection.setConnectTimeout(15000);
        connection.setReadTimeout(15000);
        return connection;
    }

    private static void addRequestBody(HttpURLConnection connection, String body) throws IOException {
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        OutputStream outputStream = connection.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        bw.write(body);
        bw.flush();
        bw.close();
    }

    private static Response getResponse(HttpURLConnection connection) throws IOException {
        InputStream inputStream;
        int responseCode = connection.getResponseCode();
        if (responseCode < 400) {
            inputStream = connection.getInputStream();
        }else {
            inputStream = connection.getErrorStream();
        }

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String sor = br.readLine();
        while(sor != null) {
            sb.append(sor);
            sor = br.readLine();
        }
        br.close();
        inputStream.close();
        connection.disconnect();

        return new Response(responseCode, sb.toString());
    }






}
