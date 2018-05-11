import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    public static void FromUpdateProtocol(String jsonData) {
        Gson gson = new GsonBuilder().create();
        UserState userState;
        ArrayList<String> datas = new ArrayList<>();

        String data;
        System.out.println(jsonData);
        String[] strs = jsonData.split("type\":|,\"users\":|\\}|\\{");

        if(strs[2].equals("\"Update\"")){
            for(int i = 4; i< strs.length; i++) {
                if (i%2 == 0) {
                    data = "{" + strs[i] + "}";
                    datas.add(data);
                }
            }

            for (int i = 0; i < datas.size(); i++) {
                userState = gson.fromJson(datas.get(i), UserState.class);

                if(Window.users.containsKey(userState.user))
                    Window.users.get(userState.user).userState = userState;
            }
        }
    }

}
