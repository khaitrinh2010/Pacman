package colouredballs.state;

import colouredballs.model.Ball;
import javafx.scene.paint.Paint;

public class BlueState implements State{
    private Ball ball;
    public BlueState(Ball ball){
        this.ball = ball;
    }
    @Override
    public void handleCollision() {
        ball.setColour(Paint.valueOf("BLACK"));
        ball.setState(ball.getBlackState());
    }
}
