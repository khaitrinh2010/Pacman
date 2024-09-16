package colouredballs.model;

import colouredballs.strategy.ChaseFurthestStrategy;
import colouredballs.strategy.ChaseNearestStrategy;
import colouredballs.strategy.NoChaseStrategy;
import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;

public class BallPit {
    private final double height;
    private final double width;
    private final double g;
    private final List<Ball> balls = new ArrayList<>();
    private long tickCount = 0;
    private double frameDuration;

    public BallPit(double width, double height, double frameDuration) {
        this.height = height;
        this.width = width;

        this.frameDuration = frameDuration;
        g = 1.0 * frameDuration;
        Ball ball1 = new Ball(100, 100, 20, Paint.valueOf("RED"));
        ball1.setStrategy(new ChaseFurthestStrategy());
        Ball ball2 = new Ball(200, 200, 20, Paint.valueOf("BLACK"));
        ball2.setStrategy(new ChaseNearestStrategy());
        Ball ball3 = new Ball(300, 300, 20, Paint.valueOf("BLUE"));
        ball3.setStrategy(new NoChaseStrategy());
        balls.add(ball1);
        balls.add(ball2);
        balls.add(ball3);
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }


    private double calculateEuclideanDistance(Ball ball1, Ball ball2){
        return Math.sqrt(Math.pow(ball1.getxPos() - ball2.getxPos(), 2) +  Math.pow(ball1.getyPos() - ball2.getyPos(), 2));
    }

    public Ball getFurthestBall(Ball sourceBall){
        double maxCurrentDistance = 0.0;
        Ball res = null;
        for(Ball ball: getBalls()){
            if(ball != sourceBall) {
                double currentDistance = calculateEuclideanDistance(sourceBall, ball);
                if (currentDistance > maxCurrentDistance) {
                    maxCurrentDistance = currentDistance;
                    res = ball;
                }
            }
        }
        return res;
    }

    public Ball getNearestBall(Ball sourceBall){
        double minCurrentDistance = 10000000;
        Ball res = null;
        for(Ball ball: getBalls()){
            if(ball != sourceBall) {
                double currentDistance = calculateEuclideanDistance(sourceBall, ball);
                if (currentDistance < minCurrentDistance) {
                    minCurrentDistance = currentDistance;
                    res = ball;
                }
            }
        }
        return res;
    }
    public void tick() {
        tickCount++;

        for(Ball ball: balls) {
            ball.tick();
            ball.think(ball, this, frameDuration);
            // Handle the edges (balls don't get a choice here)
            if (ball.getxPos() + ball.getRadius() > width) {
                ball.setxPos(width - ball.getRadius());
                ball.setxVel(ball.getxVel() * -1);
            }
            if (ball.getxPos() - ball.getRadius() < 0) {
                ball.setxPos(0 + ball.getRadius());
                ball.setxVel(ball.getxVel() * -1);
            }
            if (ball.getyPos() + ball.getRadius() > height) {
                ball.setyPos(height - ball.getRadius());
                ball.setyVel(ball.getyVel() * -1);
            }
            if (ball.getyPos() - ball.getRadius() < 0) {
                ball.setyPos(0 + ball.getRadius());
                ball.setyVel(ball.getyVel() * -1);
            }

            // Apply gravity if we're not on the ground (balls still don't get a choice)
            if (ball.getyPos() + ball.getRadius() < height) {
                ball.setyVel(ball.getyVel() + g);
            }

            for(Ball ballB: balls) {
                if (checkCollision(ball, ballB)) {
                    handleCollision(ball, ballB);
                }
            }
        }
    }

    public List<Ball> getBalls() {
        return balls;
    }

    private boolean checkCollision(Ball ballA, Ball ballB) {
        if (ballA == ballB) {
            return false;
        }

        return Math.abs(ballA.getxPos() - ballB.getxPos()) < ballA.getRadius() + ballB.getRadius() &&
                Math.abs(ballA.getyPos() - ballB.getyPos()) < ballA.getRadius() + ballB.getRadius();
    }

    private void handleCollision(Ball ballA, Ball ballB) {

        //Properties of two colliding balls
        Point2D posA = new Point2D(ballA.getxPos(), ballA.getyPos());
        Point2D posB = new Point2D(ballB.getxPos(), ballB.getyPos());
        Point2D velA = new Point2D(ballA.getxVel(), ballA.getyVel());
        Point2D velB = new Point2D(ballB.getxVel(), ballB.getyVel());

        //calculate the axis of collision
        Point2D collisionVector = posB.subtract(posA);
        collisionVector = collisionVector.normalize();

        //the proportion of each balls velocity along the axis of collision
        double vA = collisionVector.dotProduct(velA);
        double vB = collisionVector.dotProduct(velB);

        //if balls are moving away from each other do nothing
        if (vA <= 0 && vB >= 0) {
            return;
        }

        // We're working with equal mass balls today
        //double mR = massB/massA;
        double mR = 1;

        //The velocity of each ball after a collision can be found by solving the quadratic equation
        //given by equating momentum energy and energy before and after the collision and finding the
        //velocities that satisfy this
        //-(mR+1)x^2 2*(mR*vB+vA)x -((mR-1)*vB^2+2*vA*vB)=0
        //first we find the discriminant
        double a = -(mR + 1);
        double b = 2 * (mR * vB + vA);
        double c = -((mR - 1) * vB * vB + 2 * vA * vB);
        double discriminant = Math.sqrt(b * b - 4 * a * c);
        double root = (-b + discriminant)/(2 * a);

        //only one of the roots is the solution, the other pertains to the current velocities
        if (root - vB < 0.01) {
            root = (-b - discriminant)/(2 * a);
        }

        //The resulting changes in velocity for ball A and B
        Point2D deltaVA = collisionVector.multiply(mR * (vB - root));
        Point2D deltaVB = collisionVector.multiply(root - vB);

        ballA.setxVel(ballA.getxVel() + deltaVA.getX());
        ballA.setyVel(ballA.getyVel() + deltaVA.getY());
        ballB.setxVel(ballB.getxVel() + deltaVB.getX());
        ballB.setyVel(ballB.getyVel() + deltaVB.getY());
        ballA.handleCollision();
        ballB.handleCollision();
    }

    public double getFrameDuration() {
        return this.frameDuration;
    }
}
