package colouredballs.strategy;

import colouredballs.model.Ball;
import colouredballs.model.BallPit;

import java.util.List;

public class ChaseNearestStrategy implements BallMovementStrategy{
    @Override
    public void move(Ball ball, BallPit ballPit, double frameDuration) {
        Ball nearestBall = ballPit.getNearestBall(ball);
        double accelX = ((nearestBall.getxPos() - ball.getxPos()) / distance(ball,nearestBall)) * frameDuration ;
        double accelY = ((nearestBall.getyPos() - ball.getyPos()) / distance(ball,nearestBall)) * frameDuration;
        ball.setxVel(ball.getxVel() + accelX);
        ball.setyVel(ball.getyVel() + accelY);
    }

    public double distance(Ball ball1, Ball ball2){
        return Math.sqrt((ball1.getxPos() - ball2.getxPos())*(ball1.getxPos() - ball2.getxPos()) + (ball1.getyPos() - ball2.getyPos())*(ball1.getyPos() - ball2.getyPos()));
    }
}
