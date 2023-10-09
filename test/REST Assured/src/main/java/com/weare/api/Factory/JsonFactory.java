package com.weare.api.Factory;
import com.google.gson.Gson;

public class JsonFactory {
    private static final Gson gson = new Gson();

    public static <T> String toJson(T object) {
        return gson.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }
}
