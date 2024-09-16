package colouredballs.strategy;

import colouredballs.model.Ball;
import colouredballs.model.BallPit;

import java.util.List;

public class ChaseFurthestStrategy implements BallMovementStrategy{
    @Override
    public void move(Ball ball, BallPit ballPit, double frameDuration) {
        Ball furthestBall = ballPit.getFurthestBall(ball);
        double accelX = ((furthestBall.getxPos() - ball.getxPos()) / distance(ball,furthestBall)) * frameDuration ;
        double accelY = ((furthestBall.getyPos() - ball.getyPos()) / distance(ball,furthestBall)) * frameDuration;
        ball.setxVel(ball.getxVel() + accelX);
        ball.setyVel(ball.getyVel() + accelY);
    }

    public double distance(Ball ball1, Ball ball2){
        return Math.sqrt((ball1.getxPos() - ball2.getxPos())*(ball1.getxPos() - ball2.getxPos()) + (ball1.getyPos() - ball2.getyPos())*(ball1.getyPos() - ball2.getyPos()));
    }


}
