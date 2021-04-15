package com.paylater.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

public class JsonUtil {
    public static String objectToJson(Object value) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(value);
    }

    public static String getStringValue(String json, String key) {
        JSONObject jsonObj = new JSONObject(json);
        return jsonObj.getString(key);
    }

    public static Double getDoubleValue(String json, String key) {
        JSONObject jsonObj = new JSONObject(json);
        return jsonObj.getDouble(key);
    }
}
