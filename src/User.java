import processing.core.PApplet;

public class User extends View{
    public UserState userState;


    public User (PApplet pApplet) {
        super(pApplet);
        userState.direction = Constants.DOWN;
    }

    @Override
    public void onUpdate() {
        position.x = userState.x;
        position.y = userState.y;
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
