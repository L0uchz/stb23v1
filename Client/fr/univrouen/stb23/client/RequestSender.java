package fr.univrouen.stb23.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class RequestSender {

    public class RequestSender {

        public ArrayList<Object> sendGet(String url) throws Exception {

            ArrayList<Object> result = new ArrayList<Object>();

            HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

            httpClient.setRequestMethod("GET");

            int responseCode = httpClient.getResponseCode();

            result.add(responseCode);

            try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()))) {

                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }

                result.add(response.toString());

            }

            return result;
        }

        public ArrayList<Object> sendPost(String url2, String data) throws IOException {

            ArrayList<Object> result = new ArrayList<Object>();
            URL url = new URL(url2);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/xml");
            http.setRequestProperty("Accept", "application/xml");

            if (data.length() != 0) {
                byte[] out = data.getBytes(StandardCharsets.UTF_8);
                OutputStream stream = http.getOutputStream();
                stream.write(out);
            }

            result.add(http.getResponseCode());

            try (BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()))) {

                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }

                result.add(response.toString());
            }
            http.disconnect();
            return result;
        }

        public ArrayList<Object> sendDelete(String url2) throws IOException {

            ArrayList<Object> result = new ArrayList<Object>();
            URL url = new URL(url2);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("DELETE");
            http.setRequestProperty("Accept", "*/*");

            result.add(http.getResponseCode());

            try (BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()))) {

                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }

                result.add(response.toString());
            }
            http.disconnect();
            return result;
        }



    }