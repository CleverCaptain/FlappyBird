package flappyBird.model;

import flappyBird.controller.*;
import flappyBird.view.*;

import java.awt.*;

public class Bird implements Runnable {
    public final static int TERMINAL_VELOCITY = -4;
    public final static int JUMP_VELOCITY = 3;
    private Point birdPosition;
    private int verticalVelocity;
    private GameState gameState;
    private Rectangle topPipeRectangle;
    private Rectangle bottomPipeRectangle;
    private GamePanel gamePanel;

    Bird(int x, int y, GameState gameState) {
        birdPosition = new Point(x, y);
        verticalVelocity = 0;
        this.gameState = gameState;
    }

    public Bird(Point birdPosition) {
        this.birdPosition = birdPosition;
        verticalVelocity = 0;
    }

    public Point getBirdPosition() {
        return birdPosition;
    }

    public void setBirdPosition(Point birdPosition) {
        this.birdPosition = birdPosition;
    }

    @Override
    public void run() {
        while (gameState == GameState.PLAYING) {
//            System.out.println(Thread.currentThread().getName());
            birdPosition.translate(0, -verticalVelocity);
//            GamePanel.birdRectangle = new Rectangle(BirdGame.START_POS_X - 10, birdPosition.y - 3,
//                    GamePanel.BIRD_WIDTH, GamePanel.BIRD_HEIGHT - 3);
            gamePanel.repaint();
            if (topPipeRectangle != null && bottomPipeRectangle != null
                    && (topPipeRectangle.intersects(BirdGame.START_POS_X - 10, birdPosition.y - 3,
                    GamePanel.BIRD_WIDTH, GamePanel.BIRD_HEIGHT - 3)
                    || bottomPipeRectangle.intersects(BirdGame.START_POS_X - 10, birdPosition.y - 3,
                    GamePanel.BIRD_WIDTH, GamePanel.BIRD_HEIGHT - 3))) {
                gameState = GameState.ENDED;
            }
            if (birdPosition.y > 675 || birdPosition.y < -10) {
                gameState = GameState.ENDED;
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//    public void fall(GamePanel gamePanel) {
//        while (gameState == GameState.PLAYING) {
//            birdPosition.translate(0, -verticalVelocity);
//            GamePanel.birdRectangle = new Rectangle(BirdGame.START_POS_X - 10, birdPosition.y - 3,
//                    GamePanel.BIRD_WIDTH, GamePanel.BIRD_HEIGHT - 3);
//            gamePanel.repaint();
//            if (topPipeRectangle != null && bottomPipeRectangle != null
//                    && (topPipeRectangle.intersects(BirdGame.START_POS_X - 10, birdPosition.y - 3, GamePanel.BIRD_WIDTH, GamePanel.BIRD_HEIGHT - 3)
//                    || bottomPipeRectangle.intersects(BirdGame.START_POS_X - 10, birdPosition.y - 3,
//                    GamePanel.BIRD_WIDTH, GamePanel.BIRD_HEIGHT - 3))) {
//                gameState = GameState.ENDED;
//            }
//            if (birdPosition.y > 675) {
//                gameState = GameState.ENDED;
//            }
//            try {
//                Thread.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void changeVelocityBy(int toChangeBy) {
        if (verticalVelocity + toChangeBy > TERMINAL_VELOCITY) {
            verticalVelocity += toChangeBy;
        }
    }

    @Override
    public String toString() {
        return String.format("Current Position = [%d, %d], velocity = %d",
                birdPosition.x, birdPosition.y, verticalVelocity);

    }

    public void jump() {
//        if (Math.abs(verticalVelocity + changeVelocityBy) < 4) {
//            verticalVelocity += changeVelocityBy;
//        } else {
//        }
        verticalVelocity = JUMP_VELOCITY;
    }

    public void setTopPipeRectangle(Rectangle topPipeRectangle) {
        this.topPipeRectangle = topPipeRectangle;
    }

    public void setBottomPipeRectangle(Rectangle bottomPipeRectangle) {
        this.bottomPipeRectangle = bottomPipeRectangle;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public Rectangle getTopPipeRectangle() {
        return topPipeRectangle;
    }

    public Rectangle getBottomPipeRectangle() {
        return bottomPipeRectangle;
    }
}
