import processing.core.PApplet;
import java.io.IOException;

public class Window extends PApplet implements Constants{
    private Session session;
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

    public void keyPressed(){
        try {
            session.send("{\"type\":\"stop\",\"body\":{\"user\":\"mj\"}}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void keyReleased(){
    }
}
