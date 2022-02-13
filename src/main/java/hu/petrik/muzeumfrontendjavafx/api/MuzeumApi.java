package hu.petrik.muzeumfrontendjavafx.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import hu.petrik.muzeumfrontendjavafx.Festmeny;
import hu.petrik.muzeumfrontendjavafx.Szobrok;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class MuzeumApi {
    private static final String BASE_URL = "http://localhost:8000";
    public static final String SZOBOR_API_URL = BASE_URL + "/api/statues";
    public static final String FESTMENY_API_URL = BASE_URL + "/api/paintings";

    public static List<Szobrok> getSzobrok() throws IOException {
        Response response = RequestHandler.get(SZOBOR_API_URL);
        String json = response.getContent();
        Gson gson = new Gson();
        if (response.getResponseCode() >= 400) {
            System.out.println(json);
            String message = gson.fromJson(json, Error.class).getMsg();
            throw new IOException(message);
        } else {
            Type type = new TypeToken<List<Szobrok>>() {
            }.getType();
            List<Szobrok> szoborList = gson.fromJson(json, type);
            return szoborList;
        }
    }

    public static boolean szoborTorlese(int id) throws IOException {
        return torlesBody(id, SZOBOR_API_URL);
    }



    public static List<Festmeny> getFestmeny() throws IOException {
        Response response = RequestHandler.get(FESTMENY_API_URL);
        String json = response.getContent();
        Gson gson = new Gson();
        if (response.getResponseCode() >= 400) {
            System.out.println(json);
            String message = gson.fromJson(json, Error.class).getMsg();
            throw new IOException(message);
        } else {
            Type type = new TypeToken<List<Festmeny>>() {
            }.getType();
            List<Festmeny> festmenyList = gson.fromJson(json, type);
            return festmenyList;
        }
    }
    public static boolean festmenyTorlese(int id) throws IOException {
        return torlesBody(id, FESTMENY_API_URL);
    }

    private static boolean torlesBody(int id, String szoborApiUrl) throws IOException {
        Response response = RequestHandler.delete(szoborApiUrl +"/" + id);

        Gson gsonTorles = new Gson();
        String json = response.getContent();

        if (response.getResponseCode() >= 400) {
            String message = gsonTorles.fromJson(json, Error.class).getMsg();
            throw new IOException(message);
        }
        return response.getResponseCode() == 204;
    }
}
