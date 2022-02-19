package flappyBird.controller;

import flappyBird.model.*;
import flappyBird.view.*;

import java.awt.*;
import java.util.*;
import java.util.List;

public class PipePositionChanger extends Thread {
    final int horizontalVelocity;
    private List<Pipe> pipeList;
    private GamePanel gamePanel;
    private BirdGame game;

    public PipePositionChanger(BirdGame birdGame, GamePanel gamePanel, int horizontalVelocity) {
        this.game = birdGame;
        this.gamePanel = gamePanel;
        this.horizontalVelocity = horizontalVelocity;
        pipeList = new ArrayList<>();
    }

    @Override
    public void run() {
//        while (pipe.getxPosition() > -10) {
//            pipe.changeXPosition(horizontalVelocity);
//            try {
//                Thread.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        while (!pipeList.isEmpty() && game.getOverallState() == GameState.PLAYING) {
            if (pipeList.get(0).getxPosition() < 30) {
                pipeList.remove(0);
                pipeList.add(pipeList.get(pipeList.size() - 1).createNextPipe());
                game.setPipe(pipeList.get(0));
            }
            if (game.getPipe().getxPosition() == 40) {
                game.incrementScore();
            }
            pipeList.forEach(pipe -> pipe.changeXPosition(horizontalVelocity));
            gamePanel.setNextPipe(pipeList.get(1));
            setPipeRectangle(game.getPipe());
            try {
                Thread.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean addNextPipe(Pipe pipe) {
        return pipeList.add(pipe);
    }

    public void setPipeRectangle(Pipe pipe) {
        Rectangle topPipeRectangle = new Rectangle(pipe.getxPosition(), 0, GamePanel.PIPE_WIDTH, pipe.getTopPipe().getHeight());
        int bottomPipeHeight = pipe.getBottomPipe().getHeight();
        Rectangle bottomPipeRectangle = new Rectangle(pipe.getxPosition(), GameWindow.WINDOW_HEIGHT - bottomPipeHeight, GamePanel.PIPE_WIDTH,
                bottomPipeHeight);
//        gamePanel.setTopPipeRectangle(bottomPipeRectangle);
//        gamePanel.setBottomPipeRectangle(topPipeRectangle);
        Bird[] birds = game.getBirds();
        for (Bird bird : birds) {
            bird.setTopPipeRectangle(topPipeRectangle);
            bird.setBottomPipeRectangle(bottomPipeRectangle);
        }
//        game.getBird().setTopPipeRectangle(topPipeRectangle);
//        game.getBird().setBottomPipeRectangle(bottomPipeRectangle);
    }

}
