package yjt.argparser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static java.lang.reflect.Modifier.STATIC;

/**
 * @author yjt
 */
public class GsonToString {
    private static Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .serializeNulls()
        .excludeFieldsWithModifiers(STATIC)
        .create();

    @Override
    public String toString() {
        return gson.toJson(this);
    }
}
