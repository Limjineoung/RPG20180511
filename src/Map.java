import processing.core.PApplet;

import java.util.ArrayList;

public class Map implements Constants{
    Block[] map;
    public Map() {
        map = new Block[1024];
    }

    public void updateMap(int[] mapData) {
        for(int i = 0; i < mapData.length; i++) {
            this.map[i] = new Block(i%BLOCK_COUNT_Y*BLOCK_SIZE,
                    i/BLOCK_COUNT_Y*BLOCK_SIZE, mapData[i]);
        }
    }

    public void draw(PApplet pApplet) {
        for (Block block : map) {
            block.draw(pApplet);
        }
    }
}
