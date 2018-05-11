import processing.core.PApplet;
import java.io.IOException;

public class Window extends PApplet implements Constants{
    public static Session session;
    private CharacterData characterData;

    public Window() throws IOException {
        session = new Session("127.0.0.1", 5555);
        session.start();
        characterData = new CharacterData(this, 100, 100);
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
        background(255);
        characterData.render();
    }

    @Override
    public void keyPressed() {
        if (keyCode >= 37 && keyCode <=40) {
            if(keyCode == 37) characterData.direction = Constants.LEFT;
            else if(keyCode == 38) characterData.direction = Constants.UP;
            if(keyCode == 39) characterData.direction = Constants.RIGHT;
            if(keyCode == 40) characterData.direction = Constants.DOWN;

            try {
                Protocol.MoveProtocol(characterData.direction);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyReleased(){
    }
}
