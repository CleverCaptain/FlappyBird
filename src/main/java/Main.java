
import flappyBird.controller.*;
import flappyBird.model.*;
import flappyBird.view.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        GameWindow flappyBirdWindow = new GameWindow();
        GameState gameState = GameState.PLAYING;
        BirdGame birdGame = new BirdGame(gameState);

        WindowUpdater controller = new WindowUpdater(birdGame, flappyBirdWindow, true);
        controller.setName("Window updater Thread");
        controller.start();
        System.out.println("*******************");
        System.out.println("PRESS SPACE TO JUMP");
        System.out.println("PRESS SPACE TO JUMP");
        System.out.println("PRESS SPACE TO JUMP");
        System.out.println("*******************");

    }
}
