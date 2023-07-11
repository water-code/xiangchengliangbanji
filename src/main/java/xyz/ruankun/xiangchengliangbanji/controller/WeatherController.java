package xyz.ruankun.xiangchengliangbanji.controller;

import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin
public class WeatherController {

    @GetMapping
    public String  getCurrentWeather(@RequestParam("lon") String lon, @RequestParam("lat")String lat)  throws IOException {

        URL url = new URL("http://api.caiyunapp.com/v2.6/NMFkcX12TkkmxzQq/" + lat + "," + lon +"/weather?alert=true&dailysteps=1&hourlysteps=24");
        System.out.println(url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } else {
            return "Error: " + responseCode;
        }
    }

}
