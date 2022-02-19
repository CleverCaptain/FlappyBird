package flappyBird.model;

import flappyBird.controller.*;

import java.awt.*;
import java.util.Arrays;

public class BirdGame {
    public final static int START_POS_X = 40;
    public final static int START_POS_Y = 350;
    public final static int NUM_OF_BIRDS = 1;
    private Bird[] flappyBirds;
    private Pipe pipe;
    private int gameScore;
    private GameState overallState;

    public BirdGame(GameState gameState) {
        flappyBirds = new Bird[NUM_OF_BIRDS];
        for (int i = 0; i < flappyBirds.length; i++) {
            flappyBirds[i] = new Bird(START_POS_X, START_POS_Y, gameState);
        }
        pipe = Pipe.createStartingPipe();
        gameScore = 0;
        overallState = gameState;
    }

    public Bird[] getBirds() {
        return flappyBirds;
    }

    public void setBirds(Bird[] newBirds) {
        this.flappyBirds = newBirds;
    }

    public Pipe getPipe() {
        return pipe;
    }

    public void setPipe(Pipe pipe) {
        this.pipe = pipe;
    }

    public int getGameScore() {
        return gameScore;
    }

    public void incrementScore() {
        gameScore++;
    }

    public GameState getOverallState() {
        return overallState;
    }

    public void setOverallState(GameState overallState) {
        this.overallState = overallState;
    }
}
