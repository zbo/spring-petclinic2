package org.springframework.samples.petclinic.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonNull;

/**
 * Created by zbo on 4/9/16.
 */
public class JsonUtil {
    private static Gson gson=new GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .create();

    public static String toJson(Object src) {
        if (src == null) {
            return gson.toJson(JsonNull.INSTANCE);
        }
        return gson.toJson(src);
    }
}
