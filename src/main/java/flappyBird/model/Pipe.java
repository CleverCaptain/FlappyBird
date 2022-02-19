package flappyBird.model;

import java.awt.*;
import java.util.Random;

public class Pipe {
    public final static int WINDOW_HEIGHT = 700;
    public final static int MIN_HEIGHT = 80;
    public final static int MAX_HEIGHT = 520;
    public final static int GAP = 90;
    public final static int PIPE_HEIGHT_DIFF = 125;
    private TopPipe topPipe;
    private BottomPipe bottomPipe;
    private int xPosition;

    public Pipe(int topPipeHeight) {
        this.topPipe = new TopPipe(topPipeHeight);
        this.bottomPipe = new BottomPipe(calcBottomPipeHeight(topPipeHeight));
        xPosition = 250;
    }

    public Pipe(int topPipeHeight, int xPosition) {
        this.topPipe = new TopPipe(topPipeHeight);
        this.bottomPipe = new BottomPipe(calcBottomPipeHeight(topPipeHeight));
        this.xPosition = xPosition;
    }

    public static Pipe createStartingPipe() {
        int height = 325;
        return new Pipe(height);
    }

    public Pipe createNextPipe() {
        Random random = new Random();
        int heightChange = random.nextInt(PIPE_HEIGHT_DIFF);

        if (random.nextBoolean()) {
            if (topPipe.getHeight() + heightChange < MAX_HEIGHT) {
                return new Pipe(topPipe.getHeight() + heightChange, xPosition + 250);
            } else if (topPipe.getHeight() - heightChange > MIN_HEIGHT) {
                return new Pipe(topPipe.getHeight() - heightChange, xPosition + 250);
            } else {
                System.out.println("heightChange = " + heightChange);
                System.out.println("topPipe = " + topPipe.getHeight());
                System.out.println("bottomPipe = " + bottomPipe.getHeight());
                System.out.println("WOW! Something went wrong!");
                return null;
            }

        } else {
            if (topPipe.getHeight() - heightChange > MIN_HEIGHT) {
                return new Pipe(topPipe.getHeight() - heightChange, xPosition + 250);
            } else if (topPipe.getHeight() + heightChange < MAX_HEIGHT) {
                return new Pipe(topPipe.getHeight() + heightChange, xPosition + 250);
            } else {
                System.out.println("heightChange = " + heightChange);
                System.out.println("topPipe = " + topPipe.getHeight());
                System.out.println("bottomPipe = " + bottomPipe.getHeight());
                System.out.println("WOW! Something went wrong!");
                return null;
            }
        }
    }

    public int calcBottomPipeHeight(int topPipeHeight) {
        return WINDOW_HEIGHT - topPipeHeight - GAP;
    }

    public TopPipe getTopPipe() {
        return topPipe;
    }

    public void setTopPipe(TopPipe topPipe) {
        this.topPipe = topPipe;
    }

    public BottomPipe getBottomPipe() {
        return bottomPipe;
    }

    public void setBottomPipe(BottomPipe bottomPipe) {
        this.bottomPipe = bottomPipe;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void changeXPosition(int toChangeBy) {
        xPosition -= toChangeBy;
    }
}
