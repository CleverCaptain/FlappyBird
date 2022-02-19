package flappyBird.controller;

import flappyBird.model.*;
import flappyBird.view.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class WindowUpdater extends Thread {
    private BirdGame game;
    private GameWindow gameWindow;
    private boolean isRealPerson;

    public WindowUpdater(BirdGame game, GameWindow gameWindow, boolean isRealPerson) {
        this.game = game;
        this.gameWindow = gameWindow;
        this.isRealPerson = isRealPerson;
    }

    @Override
    public void run() {
        gameWindow.getGamePanel().setFlappyBird(game);
//        Thread fallingThread = new Thread(game.getBird());
//        ExecutorService birdFlyingService = Executors.newCachedThreadPool();
//        ExecutorService birdAcceleratingService = Executors.newCachedThreadPool();
        Bird[] birds = game.getBirds();
        int i = 1;
        for (Bird bird : birds) {
            bird.setGamePanel(gameWindow.getGamePanel());
            Thread birdThread = new Thread(bird);
            birdThread.setName("Bird player Thread " + i++);
            birdThread.start();
        }
//        Thread fallingThread = new Thread(() -> {
//            game.getBird().fall(gameWindow.getGamePanel());
//        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        i = 1;
        for (Bird bird : birds) {
            Thread birdAcceleratingThread = new Thread(new BirdAccelerator(bird));
            birdAcceleratingThread.setName("Bird accelerator thread " + i++);
            birdAcceleratingThread.start();
        }
//        Thread acceleratingThread = new Thread(() -> {
//            while (game.getGameState() == GameState.PLAYING) {
//                game.getBird().changeVelocityBy(-1);
////                gameWindow.getGamePanel().repaint();
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        if (isRealPerson) {
            gameWindow.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent pressEvent) {
                    if (pressEvent.getKeyCode() == KeyEvent.VK_SPACE) {
//                    System.out.println("Space bar pressed.");

                        game.getBirds()[0].jump();
                    } else {
                        System.out.println("Press space key to fly!");
                    }
                }
            });
//            gameWindow.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    game.getBirds()[0].jump();
//                }
//            });
        }
//        if (game.getBirds().getGameState() == GameState.PLAYING) {
//            fallingThread.start();
//            acceleratingThread.start();
//            PipeCollisionDetector pipeCollisionDetector = new PipeCollisionDetector(game);
//            Thread PipeCollisionDetectorThread = new Thread(pipeCollisionDetector);
//            PipeCollisionDetectorThread.start();
//            fallDownDetectorThread.start();
//            PipeCollisionDetectorThread.start();
//        }

        try {
            PipePositionChanger pipeUpdater = new PipePositionChanger(game, gameWindow.getGamePanel(),
                    1);
            pipeUpdater.addNextPipe(game.getPipe());
            pipeUpdater.addNextPipe(game.getPipe().createNextPipe());
//                Pipe nextPipe = game.getPipe().createNextPipe();
//                gameWindow.getGamePanel().setNextPipe(nextPipe);
//                PipePositionChanger pipeUpdater = new PipePositionChanger(game.getPipe(), 1);
//                PipePositionChanger nextPipeUpdater = new PipePositionChanger(nextPipe, 1);
            pipeUpdater.setName("Pipe mover Thread");
            pipeUpdater.start();
//                nextPipeUpdater.start();
            pipeUpdater.join();
//                game.setPipe(nextPipe);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
