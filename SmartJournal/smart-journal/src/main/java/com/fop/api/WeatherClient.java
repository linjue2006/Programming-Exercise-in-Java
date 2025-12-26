package com.fop.api;

import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherClient {

    // 昆明坐标
    private static final double LAT = 25.03;
    private static final double LON = 102.71;
    
    // API 地址
    private static final String API_URL = 
        "https://api.open-meteo.com/v1/forecast?latitude=" + LAT + "&longitude=" + LON + "&current_weather=true";

    // 代理设置 (如果报错 Connection Refused，请注释掉 .proxy 这行)
    private static final HttpClient CLIENT = HttpClient.newBuilder()
            .proxy(ProxySelector.of(new InetSocketAddress("127.0.0.1", 7890)))
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static String getCurrentWeather() {
        try {
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();

            HttpResponse<String> resp = CLIENT.send(req, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

            if (resp.statusCode() != 200) {
                return "HTTP Error " + resp.statusCode();
            }

            // 打印原始内容，方便你确认网络是否通畅
            // System.out.println("[DEBUG] Raw JSON: " + resp.body()); 

            return parseWeatherResponse(resp.body());

        } catch (Exception e) {
            System.err.println("Weather API Failed: " + e.getMessage());
            return "Connection Failed";
        }
    }

    private static String parseWeatherResponse(String json) {
        // 使用正则 (Regex) 提取，专门匹配数字，忽略 "°C" 这种文字
        // 1. 提取 temperature (支持负数和小数)
        double temp = extractDoubleByRegex(json, "\"temperature\":\\s*([-+]?\\d*\\.?\\d+)");
        
        // 2. 提取 weathercode (整数)
        int code = (int) extractDoubleByRegex(json, "\"weathercode\":\\s*(\\d+)");

        return interpretWeatherCode(code) + " (" + temp + "°C)";
    }

    // 正则提取辅助方法
    private static double extractDoubleByRegex(String json, String regex) {
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(json);
            if (matcher.find()) {
                return Double.parseDouble(matcher.group(1)); // 抓取括号里的数字
            }
        } catch (Exception e) {
            // ignore
        }
        return 0.0; // 没抓到就默认 0.0
    }

    private static String interpretWeatherCode(int code) {
        if (code == 0) return "Clear sky";
        if (code <= 3) return "Partly cloudy";
        if (code <= 48) return "Foggy";
        if (code <= 67) return "Rain";
        if (code <= 77) return "Snow";
        if (code <= 99) return "Thunderstorm";
        return "Overcast";
    }
}