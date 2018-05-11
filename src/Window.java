import processing.core.PApplet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Window extends PApplet implements Constants {
    public static Session session;
    public static HashMap<String,User> users = new HashMap<>();
    public static ArrayList<String> usersNameList = new ArrayList<>();
    public static Map map;

    public Window() throws IOException {
        session = new Session("192.168.11.21", 5000);
        session.start();
        session.setOnSessionListener(this);
        Protocol.ToJoinProtocol("jinyeoung");
        map = new Map();
        }

    @Override
    public void exit() {
        System.out.println("exit");
        session.cancel();
        super.exit();
    }

    @Override
    public void setup() {
        background(255);

    }

    @Override
    public void settings() {
        size(640, 480);
    }

    @Override
    public void draw() {
        map.draw(this);
        for (int i = 0; i < usersNameList.size(); i++) {
            users.get(usersNameList.get(i)).onUpdate();
            users.get(usersNameList.get(i)).render(this);
        }
    }

    @Override
    public void keyPressed() {
        if (keyCode >= 37 && keyCode <=40) {
            String direction;
            if(keyCode == 37) direction = Constants.LEFT;
            else if(keyCode == 38) direction = Constants.UP;
            else if(keyCode == 39) direction = Constants.RIGHT;
            else direction = Constants.DOWN;

            try {
                Protocol.ToMoveProtocol(direction);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            System.out.println(keyCode);
    }

    @Override
    public void keyReleased(){
    }

}
