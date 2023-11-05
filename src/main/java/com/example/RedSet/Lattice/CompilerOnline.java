package com.example.RedSet.Lattice;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CompilerOnline {
    public static Map<String, String> compile(String code, String input, String lan, String timeLimit) throws IOException {
//        String encodedCode = Base64.getEncoder().encodeToString(code.getBytes());
//        String encodedInput = Base64.getEncoder().encodeToString(input.getBytes());
        String encodedCode = code, encodedInput = input;
        if(lan.equals("cpp")) lan = "54";
        else if(lan.equals("java")) lan = "4";
        else if(lan.equals("python")) lan = "71";
        else if(lan.equals("rust")) lan = "73";
        else if(lan.equals("pascal")) lan = "67";
        else if(lan.equals("lua")) lan = "64";
        else if(lan.equals("javascript")) lan = "63";

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body;
        if(lan.equals("54")) body = RequestBody.create(mediaType, "{\"source_code\":\"" + encodedCode + "\",\"language_id\":\"" + lan + "\",\"stdin\":\"" + encodedInput + "\",\"compiler_options\":\"-O3 --std=c++17 -Wall -Wextra -Wold-style-cast -Wuseless-cast -Wnull-dereference -Wfatal-errors -pedantic -pedantic-errors\",\"cpu_time_limit\":\"" + timeLimit + "\",\"command_line_arguments\":\"\",\"redirect_stderr_to_stdout\":true}");
        else body = RequestBody.create(mediaType, "{\"source_code\":\"" + encodedCode + "\",\"language_id\":\"" + lan + "\",\"stdin\":\"" + encodedInput + "\",\"cpu_time_limit\":\"1\",\"command_line_arguments\":\"\",\"redirect_stderr_to_stdout\":true}");
        try {
            Request request = new Request.Builder()
                    .url("https://ce.judge0.com/submissions?base64_encoded=true&wait=true")
                    .method("POST", body)
                    .addHeader("Host", "ce.judge0.com")
                    .addHeader("Content-Length", "4137")
                    .addHeader("Sec-Ch-Ua", "")
                    .addHeader("Accept", "/")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Sec-Ch-Ua-Mobile", "?0")
                    .addHeader(
                            "User-Agent",
                            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.5845.111 Safari/537.36"
                    )
                    .addHeader("Sec-Ch-Ua-Platform", "\"\"")
                    .addHeader("Origin", "https://ide.judge0.com")
                    .addHeader("Sec-Fetch-Site", "same-site")
                    .addHeader("Sec-Fetch-Mode", "cors")
                    .addHeader("Sec-Fetch-Dest", "empty")
                    .addHeader("Referer", "https://ide.judge0.com/")
                    .addHeader("Accept-Language", "en-US,en;q=0.9")
                    .build();

            Response response = client.newCall(request).execute();
            String jsonResponse = response.body().string();

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
            String output = String.valueOf(jsonObject.get("stdout"));
            String time = String.valueOf(jsonObject.get("time"));
            time = time.substring(1, time.length() - 1);
            String memory = String.valueOf(jsonObject.get("memory"));
            memory = memory.substring(1, memory.length() - 1);

            JsonObject jstatus = (JsonObject) jsonObject.get("status");
            String status = String.valueOf(jstatus.get("description"));
            status = status.substring(1, status.length() - 1);

            if(output != null) output = output.substring(1, output.length() - 3);

            String encodeOut = "";
            int flag = 1;
            String dOut = "";
            for(int i = 0; i< Objects.requireNonNull(output).length(); i++){
                if(output.charAt(i) == '\\'){
                    dOut += encodeDecode.decode(encodeOut);
                    encodeOut = "";
                    i++;
                    flag = 0;
                }
                else encodeOut += output.charAt(i);
            }

            if(flag == 1) dOut = encodeDecode.decode(encodeOut);

            Map<String, String > map = new HashMap<>();
            map.put("stdout", dOut);
            map.put("time", time);
            map.put("memory", memory);
            map.put("status", status);

            return map;
        } catch (IOException | JsonSyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
