/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.models;

import com.exavalu.services.ApiService;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
public class Api {

    private static HttpURLConnection connection;
    int userId;
    int id;
    String title;
    String completed;
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String isCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public String apiCall() throws Exception {
        System.out.println(this.url);
        String result = "FAILURE";
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body().length());
        System.out.println("Data:" + response.body());
        try {
            JSONArray jsonArray = new JSONArray(response.body());
            for (int i = 0; i < jsonArray.length(); i++) {
                Api api = new Api();
                JSONObject obj = jsonArray.getJSONObject(i);
                api.setId(obj.getInt("id"));
                System.out.println(api.getId());
                api.setUserId(obj.getInt("userId"));
                api.setTitle(obj.getString("title"));
                api.setCompleted(String.valueOf(obj.getBoolean("completed")));
                api.setUrl(this.url);
                boolean response1 = ApiService.getInstance().postData(api);
                if (response1) {
                    result = "SUCCESS";
                }
            }
        } catch (JSONException je) {
            JSONObject jsonObject = new JSONObject(response.body());
            Api api = new Api();
            api.setId(jsonObject.getInt("id"));
            System.out.println(api.getId());
            api.setUserId(jsonObject.getInt("userId"));
            api.setTitle(jsonObject.getString("title"));
            System.out.println(api.getTitle());
            api.setCompleted(String.valueOf(jsonObject.getBoolean("completed")));
            api.setUrl(this.url);
            boolean response1 = ApiService.getInstance().postData(api);
            if (response1) {
                    result = "SUCCESS";
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
