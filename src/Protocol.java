import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;

public class Protocol {
    public static void ToJoinProtocol(String user) throws IOException {
        Window.session.send("{\"type\":\"Join\",\"body\":{\"user\":\"" + user + "\"}}");
    }

    public static void ToMoveProtocol(String direction) throws IOException {
        Window.session.send("{\"type\":\"Move\",\"body\":{\"direction\":\"" + direction + "\"}}");
    }

    public static void ToAttackProtocol(String direction) throws IOException {
        Window.session.send("{\"type\":\"Attack\"}");
    }

    public static void FromServerProtocol(String jsonData) {
        System.out.println("received : " + jsonData);
        JsonParser parser = new JsonParser();
        JsonObject jobj = parser.parse(jsonData).getAsJsonObject();

        String type = jobj.get("type").getAsString();

        switch (type) {
            case "Hit":
                HitProtocol(jobj);
                break;
            case "Kill":
                killProtocol(jobj);
                break;
            case "ItemCreate":
                itemCreateProtocol(jobj);
                break;
            case "ItemConsume":
                itemConsumeProtocol(jobj);
                break;
            case "Map":
                MapProtocol(jobj);
                break;
            case "Update":
                updateProtocol(jobj);
                break;
            default:
                System.out.println("Can't translate the protocol");
        }

    }

    private static void updateProtocol(JsonObject jobj) {
        for (int i = 0; i < jobj.get("users").getAsJsonArray().size(); i++) {
            JsonObject body = jobj.get("users").getAsJsonArray().get(i).getAsJsonObject();
            String user = body.get("user").getAsString();
            if(!Window.users.containsKey(user)) {
                Window.users.put(user, new User());
            }
            Window.users.get(user).x = body.get("x").getAsFloat();
            Window.users.get(user).y = body.get("y").getAsFloat();
            Window.users.get(user).hp = body.get("hp").getAsInt();
            Window.users.get(user).direction = body.get("direction").getAsString();
            Window.users.get(user).score = body.get("score").getAsInt();
            Window.users.get(user).state = body.get("state").getAsString();
        }
    }

    private static void MapProtocol(JsonObject jobj) {

    }

    private static void itemConsumeProtocol(JsonObject jobj) {
        JsonObject body = jobj.get("body").getAsJsonObject();
        String user = body.get("user").getAsString();
        String item = body.get("item").getAsString();
    }

    private static void itemCreateProtocol(JsonObject jobj) {
        JsonObject body = jobj.get("body").getAsJsonObject();
        String item = body.get("item").getAsString();
        float x = body.get("x").getAsFloat();
        float y = body.get("y").getAsFloat();
    }

    private static void killProtocol(JsonObject jobj) {
        JsonObject body = jobj.get("body").getAsJsonObject();
        String from = body.get("from").getAsString();
        String to = body.get("to").getAsString();
    }

    private static void HitProtocol(JsonObject jobj) {
        JsonObject body = jobj.get("body").getAsJsonObject();
        String from = body.get("from").getAsString();
        String to = body.get("to").getAsString();
        int damage = body.get("damage").getAsInt();
    }
}
