package flappyBird.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class GameWindow extends JFrame {
    public static final int WINDOW_HEIGHT = 750;
    public static final int WINDOW_WIDTH = 500;

    private BufferedImage backgroundImage;
    private BufferedImage birdImage;
    private BufferedImage topPipeImage;
    private BufferedImage bottomPipeImage;

    private GamePanel gamePanel;


    public GameWindow() throws IOException {
        super("Flappy Bird");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout());


        birdImage = ImageIO.read(new File("flappyBirdBlue.png"));
        backgroundImage = ImageIO.read(new File("flappyBirdBg.jpg"));
        topPipeImage = ImageIO.read(new File("topPipeImg.png"));
        bottomPipeImage = ImageIO.read(new File("bottomPipeImg.png"));


        gamePanel = new GamePanel(backgroundImage, birdImage, topPipeImage, bottomPipeImage);
        setContentPane(gamePanel);

        setVisible(true);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
