package com.simsim;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class SimSimService {

    public String removeElements(String jsonString) {
        JsonElement jsonElement = new Gson().fromJson(jsonString, JsonElement.class);
        removeRedundantElementsJson(jsonElement);
        if (jsonElement.isJsonArray() && jsonElement.getAsJsonArray().size() == 0) {
            return new Gson().toJson("");
        }
        if (jsonElement.isJsonNull()) {
            return new Gson().toJson("");
        }
        return new Gson().toJson(jsonElement);
    }

    public void removeRedundantElementsJson(JsonElement jsonElement) {
        if (jsonElement.isJsonNull()) {
            return;
        }
        if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            ArrayList<Integer> indexToBeRemoved = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonElement eachJsonElement = jsonArray.get(i);
                if (canJsonElementBeDeleted(eachJsonElement)) {
                    indexToBeRemoved.add(i);
                } else {
                    removeRedundantElementsJson(eachJsonElement);
                }
            }
            int removedElementCount = 0;
            for (Integer integerIndex : indexToBeRemoved) {
                jsonArray.remove(integerIndex - removedElementCount);
                removedElementCount++;
            }
            indexToBeRemoved = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonElement eachJsonElement = jsonArray.get(i);
                if (canJsonElementBeDeleted(eachJsonElement)) {
                    indexToBeRemoved.add(i);
                }
            }
            removedElementCount = 0;
            for (Integer integerIndex : indexToBeRemoved) {
                jsonArray.remove(integerIndex - removedElementCount);
                removedElementCount++;
            }
        }
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> jsonObjectEntries = jsonObject.entrySet();
            ArrayList<Map.Entry<String, JsonElement>> objectsToBeRemoved = new ArrayList<>();
            for (Map.Entry<String, JsonElement> elementEntry : jsonObjectEntries) {
                if (elementEntry == null) {
                    objectsToBeRemoved.add(elementEntry);
                } else {
                    String key = elementEntry.getKey();
                    if (key == null || key.equals("")) {
                        objectsToBeRemoved.add(elementEntry);
                    } else {
                        if (canJsonElementBeDeleted(elementEntry.getValue())) {
                            objectsToBeRemoved.add(elementEntry);
                        } else {
                            removeRedundantElementsJson(elementEntry.getValue());
                        }
                    }
                }
            }
            for (Map.Entry<String, JsonElement> object : objectsToBeRemoved) {
                jsonObjectEntries.remove(object);
            }
        }
    }

    private boolean canJsonElementBeDeleted(JsonElement jsonElement) {
        if (jsonElement.isJsonNull()) {
            return true;
        }
        if (jsonElement.isJsonPrimitive()) {
            JsonPrimitive jsonPrimitive = jsonElement.getAsJsonPrimitive();
            if (jsonPrimitive.isBoolean() && !jsonPrimitive.getAsBoolean()) {
                return true;
            }
            if (jsonPrimitive.isNumber() &&
                    (jsonPrimitive.getAsInt() == 0 || jsonPrimitive.getAsFloat() == 0.0
                            || jsonPrimitive.getAsLong() == 0l)) {
                return true;
            }
            return jsonPrimitive.isString() && jsonPrimitive.getAsString().equals("");
        }
        if (jsonElement.isJsonArray() && (jsonElement.getAsJsonArray() == null || jsonElement.getAsJsonArray().size() == 0)) {
            return true;
        }
        if (jsonElement.isJsonObject() && (jsonElement.getAsJsonObject() == null || jsonElement.getAsJsonObject().size() == 0)) {
            return true;
        }
        return false;
    }
}
