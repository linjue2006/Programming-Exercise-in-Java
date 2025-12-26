package com.fop.api;

import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class HuggingFaceClient {

    // ⚠️ 最简单写法：直接写死 token（跑通后建议换成环境变量）
    private static final String TOKEN = "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";

    private static final HttpClient CLIENT = HttpClient.newBuilder()
        .proxy(ProxySelector.of(new InetSocketAddress("127.0.0.1", 7890)))
        .connectTimeout(Duration.ofSeconds(10))
        .build();


    public static String inferSentimentLabel(String text) throws Exception {
        String model = "distilbert/distilbert-base-uncased-finetuned-sst-2-english";
        String url = "https://router.huggingface.co/hf-inference/models/" + model;


        String payload = "{\"inputs\":\"" + jsonEscape(text) + "\"}";

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(30))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + TOKEN)
                .POST(HttpRequest.BodyPublishers.ofString(payload, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> resp = CLIENT.send(req, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        if (resp.statusCode() / 100 != 2) {
            throw new RuntimeException("HF HTTP " + resp.statusCode() + ": " + resp.body());
        }

        // 粗暴解析：从返回 JSON 里抓第一个 label
        return extractJsonString(resp.body(), "label");
    }

    private static String extractJsonString(String json, String field) {
        String key = "\"" + field + "\"";
        int k = json.indexOf(key);
        if (k < 0) return null;
        int colon = json.indexOf(':', k + key.length());
        if (colon < 0) return null;
        int q1 = json.indexOf('"', colon + 1);
        if (q1 < 0) return null;
        int q2 = json.indexOf('"', q1 + 1);
        if (q2 < 0) return null;
        return json.substring(q1 + 1, q2);
    }

    private static String jsonEscape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\r", "")
                .replace("\n", "\\n");
    }
}

