import processing.core.PApplet;

public class User{
    String user;
    float x;
    float y;
    int hp;
    String direction;
    int score;
    String state;


    public User () {
        direction = Constants.DOWN;
    }

    public void onUpdate() {
    }

    public void render(PApplet pApplet) {
        pApplet.fill(255, 0, 0);
        pApplet.rect(x, y, 10, 10);
    }

    public void onCollision() {

    }

    public boolean isCollision(float x, float y) {
        return false;
    }
}
