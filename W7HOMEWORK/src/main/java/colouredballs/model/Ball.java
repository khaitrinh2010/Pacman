package colouredballs.model;

import colouredballs.state.BlackState;
import colouredballs.state.BlueState;
import colouredballs.state.RedState;
import colouredballs.state.State;
import colouredballs.strategy.BallMovementStrategy;
import javafx.scene.paint.Paint;

import java.util.List;
import java.util.Random;

public class Ball {
    private double xPos;
    private double yPos;
    private double radius;
    private double xVel;
    private double yVel;
    private Paint colour;
    private BallMovementStrategy strategy;

    private RedState redState;
    private BlueState blueState;
    private BlackState blackState;
    private State currentState;

    Ball(double startX, double startY, double startRadius, Paint colour) {
        redState = new RedState(this);
        blackState = new BlackState(this);
        blueState = new BlueState(this);
        currentState = redState;
        this.xPos = startX;
        this.yPos = startY;
        this.radius = startRadius;
        this.colour = colour;
        xVel = new Random().nextInt(5);
        yVel = new Random().nextInt(5);
    }

    void tick() {
        xPos += xVel;
        yPos += yVel;
    }

    public void setxVel(double xVel) {
        this.xVel = xVel;
    }

    public void setyVel(double yVel) {
        this.yVel = yVel;
    }

    public double getRadius() {
        return radius;
    }

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public Paint getColour() {
        return colour;
    }

    public double getxVel() {
        return xVel;
    }

    public double getyVel() {
        return yVel;
    }

    void setxPos(double xPos) {
        this.xPos = xPos;
    }

    void setyPos(double yPos) {
        this.yPos = yPos;
    }

    void think(Ball ball, BallPit ballPit, double durationFrame) {
        // Here is where the strategy should have some effect.
        // You can add parameters to the think method so the ball knows something about its
        // world to make decisions with, or you can inject things upon construction for it to query
        strategy.move(ball, ballPit, durationFrame);
    }
    public void setStrategy(BallMovementStrategy newStrat){
        strategy = newStrat;
    }
    public void setState(State state){
        this.currentState = state;
    }
    public void setColour(Paint colour){
        this.colour = colour;
    }
    public void handleCollision(){
        currentState.handleCollision();
    }
    public State getRedSTate(){
        return this.redState;
    }
    public State getBlueState(){
        return this.blueState;
    }
    public State getBlackState(){
        return this.blackState;
    }
}
