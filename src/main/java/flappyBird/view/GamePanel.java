package flappyBird.view;

import flappyBird.controller.*;
import flappyBird.controller.WindowUpdater;
import flappyBird.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.sql.ResultSet;
import java.util.Arrays;

public class GamePanel extends JPanel {
    public final static int BIRD_WIDTH = 44;
    public final static int BIRD_HEIGHT = 28;
    public final static int SCALE = 9;
    public final static int PIPE_WIDTH = 80;
    private Image bgImage;
    private BufferedImage birdImage;
    private Image bottomPipeImage;
    private Image topPipeImage;
    private BirdGame birdGame;
    private Pipe nextPipe = null;
    private JLabel scoreLabel;
//    private Rectangle topPipeRectangle;
//    private Rectangle bottomPipeRectangle;
    public static Rectangle birdRectangle;


    public GamePanel(Image bgImage, BufferedImage birdImage, Image topPipeImage, Image bottomPipeImage) {
        this.bgImage = bgImage;
        this.birdImage = birdImage;
        this.topPipeImage = topPipeImage;
        this.bottomPipeImage = bottomPipeImage;
        scoreLabel = new JLabel("Score = 0");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, this);
//        System.out.println("Painting...");
//        System.out.println("flappyBird = " + birdGame.getBird());
        Bird[] birds = birdGame.getBirds();
        for (Bird bird : birds) {
            g.drawImage(birdImage, bird.getBirdPosition().x, bird.getBirdPosition().y,
                    BIRD_WIDTH, BIRD_HEIGHT, this);
        }
//        System.out.println("Updating!");
//        g.drawImage(birdImage, birdGame.getBird().getBirdPosition().x, birdGame.getBird().getBirdPosition().y,
//                BIRD_WIDTH, BIRD_HEIGHT, this);
        g.drawImage(bottomPipeImage, birdGame.getPipe().getxPosition(),
                750 - birdGame.getPipe().getBottomPipe().getHeight(),
                PIPE_WIDTH, birdGame.getPipe().getBottomPipe().getHeight(),
                this);
        g.drawImage(topPipeImage, birdGame.getPipe().getxPosition(), 0,
                PIPE_WIDTH, birdGame.getPipe().getTopPipe().getHeight(),
                this);
        if (nextPipe != null) {
            g.drawImage(bottomPipeImage, nextPipe.getxPosition(), GameWindow.WINDOW_HEIGHT - nextPipe.getBottomPipe().getHeight(),
                    PIPE_WIDTH, nextPipe.getBottomPipe().getHeight(),
                    this);
            g.drawImage(topPipeImage, nextPipe.getxPosition(), 0,
                    PIPE_WIDTH, nextPipe.getTopPipe().getHeight(),
                    this);
        }
        scoreLabel.setText("Score = " + birdGame.getGameScore());
//        if (topPipeRectangle != null) {
//            g.fillRect(topPipeRectangle.x + 80, topPipeRectangle.y, topPipeRectangle.width, topPipeRectangle.height);
//            g.fillRect(bottomPipeRectangle.x + 80, bottomPipeRectangle.y, bottomPipeRectangle.width, bottomPipeRectangle.height);
//        }
//        if (birdRectangle != null) {
//            g.drawRect(birdRectangle.x, birdRectangle.y, birdRectangle.width, birdRectangle.height);
//        }


    }

    public void setFlappyBird(BirdGame birdGame) {
        this.birdGame = birdGame;
        add(scoreLabel);
    }

    public void setNextPipe(Pipe nextPipe) {
        this.nextPipe = nextPipe;
    }

//    public void setTopPipeRectangle(Rectangle topPipeRectangle) {
//        this.topPipeRectangle = topPipeRectangle;
//    }
//
//    public void setBottomPipeRectangle(Rectangle bottomPipeRectangle) {
//        this.bottomPipeRectangle = bottomPipeRectangle;
//    }
}
