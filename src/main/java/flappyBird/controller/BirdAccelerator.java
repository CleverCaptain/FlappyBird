package flappyBird.controller;

import flappyBird.model.*;

public class BirdAccelerator implements Runnable {
    Bird bird;

    public BirdAccelerator(Bird bird) {
        this.bird = bird;
    }

    @Override
    public void run() {
        while (bird.getGameState() == GameState.PLAYING) {
            bird.changeVelocityBy(-1);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
