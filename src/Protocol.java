import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

public class Protocol {
    public static void ToMoveProtocol(String direction) throws IOException {
        Window.session.send("{\"type\":\"Move\",\"body\":{\"direction\":\"" + direction + "\"}}");
    }

    public static void FromUpdateProtocol(String jsonData) {
        Gson gson = new GsonBuilder().create();
        User user;
        ArrayList<User> users = new ArrayList<>();
        ArrayList<String> datas = new ArrayList<>();
        String data = "";

        String[] strs = jsonData.split("type\":|,\"users\":|\\}|\\{");

        if(strs[2].equals("\"Update\"")){
            for(int i = 4; i< strs.length; i++) {
                if (i%2 == 0) {
                    data = "{" + strs[i] + "}";
                    datas.add(data);
                }
            }

            for (int i = 0; i < datas.size(); i++) {
                user = gson.fromJson(datas.get(i), User.class);
                users.add(user);
            }
            for(int i = 0; i < datas.size(); i++) {
                System.out.println(users.get(i).toString());
            }
        }

    }
}
