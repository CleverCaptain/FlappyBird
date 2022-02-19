package flappyBird.model;

import java.awt.*;
import java.util.Random;

public class TopPipe {
    public final static int minHeight = 60;
    public final static int maxHeight = 590;
    private int height;

    public TopPipe(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
