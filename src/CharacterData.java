import processing.core.PApplet;

public class CharacterData extends View implements Constants {
    public String direction;


    public CharacterData (PApplet pApplet, int x, int y) {
        super(pApplet, x, y);
        direction = DOWN;
    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void render() {
        pApplet.fill(255, 0, 0);
        pApplet.rect(position.x, position.y, 10, 10);
    }

    @Override
    public void onCollision(View view) {

    }

    @Override
    public boolean isCollision(float mouseX, float mouseY) {
        return false;
    }
}
