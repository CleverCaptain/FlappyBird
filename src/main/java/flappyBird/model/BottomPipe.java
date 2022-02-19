package flappyBird.model;

public class BottomPipe {
    public final static int minHeight = 60;
    public final static int maxHeight = 590;
    private int height;

    public BottomPipe(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
