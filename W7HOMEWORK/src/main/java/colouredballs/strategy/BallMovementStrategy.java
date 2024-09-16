package colouredballs.strategy;

import colouredballs.model.Ball;
import colouredballs.model.BallPit;

import java.util.List;

public interface BallMovementStrategy {
    public void move(Ball ball, BallPit ballPit, double frameDuration);
}
