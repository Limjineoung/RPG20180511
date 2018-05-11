import processing.core.PApplet;

public class Block implements Constants{
    private int x;
    private int y;
    private int state;
    public Block(int x, int y, int state) {
        this.x = x;
        this.y = y;
        this.state = state;
    }

    public void draw(PApplet pApplet) {
        if(state == 0) pApplet.fill(255);
        else if(state == 1) pApplet.fill(0,0,255);
        else pApplet.fill(0, 255, 0);
        pApplet.rect(x, y, BLOCK_SIZE, BLOCK_SIZE);
    }
}
