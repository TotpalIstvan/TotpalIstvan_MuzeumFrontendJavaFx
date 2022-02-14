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
            List<Szobrok> szoborLista = gson.fromJson(json, type);
            return szoborLista;
        }
    }

    public static Szobrok szoborHozzadasa(Szobrok uj) throws IOException {
        Gson gAdd = new Gson();
        String szJson = gAdd.toJson(uj);
        Response response = RequestHandler.post(SZOBOR_API_URL, szJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400) {
            String message = gAdd.fromJson(json, Error.class).getMsg();
            throw new IOException(message);
        }
        return gAdd.fromJson(json,Szobrok.class);
    }

    public static Szobrok szoborModositasa(Szobrok editable) throws IOException {
        Gson gEdit = new Gson();
        String szJson = gEdit.toJson(editable);
        Response response = RequestHandler.put(SZOBOR_API_URL, szJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400) {
            String message = gEdit.fromJson(json, Error.class).getMsg();
            throw new IOException(message);
        }
        return gEdit.fromJson(json,Szobrok.class);
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
            List<Festmeny> festmenyLista = gson.fromJson(json, type);
            return festmenyLista;
        }
    }

    public static Festmeny festmenyHozzaadasa(Festmeny ujFestmeny) throws IOException {
        Gson gAdd = new Gson();
        String fJson = gAdd.toJson(ujFestmeny);
        Response response = RequestHandler.post(FESTMENY_API_URL, fJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400) {
            String message = gAdd.fromJson(json, Error.class).getMsg();
            throw new IOException(message);
        }
        return gAdd.fromJson(json, Festmeny.class);
    }

    public static Festmeny festmenyModositasa(Festmeny modositando) throws IOException {
        Gson gEdit = new Gson();
        String fJson = gEdit.toJson(modositando);
        Response response = RequestHandler.put(FESTMENY_API_URL + "/" + modositando.getId(), fJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400) {
            String message = gEdit.fromJson(json, Error.class).getMsg();
            throw new IOException(message);
        }
        return gEdit.fromJson(json, Festmeny.class);
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
