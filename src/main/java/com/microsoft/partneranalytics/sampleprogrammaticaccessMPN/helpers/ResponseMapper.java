package com.microsoft.partneranalytics.sampleprogrammaticaccessMPN.helpers;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Type;
import java.util.List;

public class ResponseMapper<T> {

    public JsonElement mapResponse(ResponseEntity<String> response, String keyName){
        String res = response.getBody();
        Gson gson = new Gson();
        JsonObject job = new JsonParser().parse(res).getAsJsonObject();
        Type generatedType = new TypeToken<List<T>>(){}.getType();
        JsonArray valArray = job.get(keyName).getAsJsonArray();
        List<T> datasets = gson.fromJson(valArray, generatedType);
        APIOutput<T> datasetsObjResponse = gson.fromJson(job, APIOutput.class);
        datasetsObjResponse.Value = datasets;
        return gson.toJsonTree(datasetsObjResponse, APIOutput.class);
    }

    public String mapFirstResponseField(ResponseEntity<String> response, String keyName, String firstElementKey){
        JsonObject obj = mapResponse(response, keyName).getAsJsonObject();
        JsonArray arr = obj.get("value").getAsJsonArray();
        JsonElement firstElement = arr.get(0);
        return firstElement.getAsJsonObject().get(firstElementKey).getAsString();
    }

}